/*
 * Copyright 2014 hypd09
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package hypd.dohnuts;

import android.app.Activity;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class BasicRandomQuoteActivity extends Activity {

    private ImageView quoteImageView;
    private TextView quoteTextView;
    private Drawable defaultDrawable;
    private Database db;
    private Picasso picasso;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic);

        quoteImageView = (ImageView) findViewById(R.id.background_image);
        quoteTextView = (TextView) findViewById(R.id.quote);
        picasso = Picasso.with(this);
        defaultDrawable = getResources().getDrawable(R.drawable.default_image);
        db = new Database(this);
        new FetchRandomQuote().execute();
    }

    void displayQuote(Quote quote) {
        if (quote.hasLink) {
            picasso.load(quote.link)
                    .placeholder(defaultDrawable)
                    .error(defaultDrawable)
                    .into(quoteImageView);
        }
        quoteTextView.setText(quote.quote);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.basic_random_quote, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_random) {
            new FetchRandomQuote().execute();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        db.close();
    }

    private class FetchRandomQuote extends AsyncTask<Void, Void, Quote> {
        @Override
        protected Quote doInBackground(Void... voids) {
            Cursor cursor = db.getQuote();
            String link = cursor.getString(cursor.getColumnIndex("link"));
            String quote = cursor.getString(cursor.getColumnIndex("quote"));
            cursor.close();
            return new Quote(link.length() > 4, quote, Uri.parse(link));
        }

        @Override
        protected void onPostExecute(Quote quote) {
            super.onPostExecute(quote);
            displayQuote(quote);
        }
    }
}
