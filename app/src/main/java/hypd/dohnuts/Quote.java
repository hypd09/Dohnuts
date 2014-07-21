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

import android.net.Uri;

public class Quote {
    public final String quote;
    public final Uri link;
    public boolean hasLink = false;

    public Quote(boolean hasLink, String quote, Uri link) {
        this.hasLink = hasLink;
        this.quote = quote;
        this.link = link;
    }
}
