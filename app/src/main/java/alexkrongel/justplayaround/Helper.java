package alexkrongel.justplayaround;

import android.content.Context;

import java.io.InputStream;

/**
 * Created by alexandrakrongel on 6/9/16.
 */

public class Helper {
    public static InputStream inputStreamForFile(Context context, String name) {
        try {
            return context.getAssets().open(name);
        } catch (Exception e) {
            return null;
        }
    }
}
