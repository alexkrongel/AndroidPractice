package alexkrongel.justplayaround;

import android.content.Context;
import android.util.Log;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;

import java.io.InputStream;
import java.util.ArrayList;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

/**
 * Created by alexandrakrongel on 6/8/16.
 */

public class Note {
    private int index;
    private String word;
    private String detail;

    public Note(int index, String word, String detail) {
        this.index = index;
        this.word = word;
        this.detail = detail;
    }


    //to process word - look for if it contains a comma or a hyphen
    //if it is a hyphen, break up the string at the (split at the delimiters) and get the individual ints, the first is start and the last is end
    //for comma, get the first and last value in the series with similar logic and then assign start and end

}
