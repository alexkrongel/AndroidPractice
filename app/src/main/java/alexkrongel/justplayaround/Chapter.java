package alexkrongel.justplayaround;

import android.content.Context;
import android.util.Log;

/**
 * Created by alexandrakrongel on 6/9/16.
 */

public class Chapter {
    public String filename;
    private ChapterContents chapterContents;
    private boolean contentsAreLoaded;

    public Chapter (String filename) {
    this.filename = filename;
    }

    public ChapterContents getChapterContents(Context context) {
        if (!contentsAreLoaded) {
            chapterContents = new ChapterContents(context, filename);//pass to saxparser in Passage contents?
        }

        return chapterContents;
    }

}
