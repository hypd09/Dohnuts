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
