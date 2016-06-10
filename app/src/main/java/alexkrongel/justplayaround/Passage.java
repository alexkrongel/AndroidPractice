package alexkrongel.justplayaround;

import android.content.Context;
import android.util.Log;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;

import java.io.InputStream;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

/**
 * Created by alexandrakrongel on 6/9/16.
 */

public class Passage {
    public int start;
    public int end;
    public String filename;

    private PassageContents passageContents;
    public boolean contentsAreLoaded;

    public Passage(String filename, int start, int end) {
        this.filename = filename;
        this.start = start;
        this.end = end;
    }

    public PassageContents getPassageContents(Context context) {
        if (!contentsAreLoaded) {
            passageContents = new PassageContents(context, filename);//pass to saxparser in Passage contents?
        }

        return passageContents;
    }
}