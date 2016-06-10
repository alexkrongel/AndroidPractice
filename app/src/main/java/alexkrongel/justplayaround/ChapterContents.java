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


// Build passage objects - same as BCCarouselLayoutItemLoaderHandler
public class ChapterContents extends DefaultHandler {
    private int start;
    private int end;
    private String filename;

    private int index;
    private String word;

    private XMLReader xmlReader;
    private DefaultHandler parent;


    public ArrayList<String> sentences = new ArrayList<String>();
    public ArrayList<Note> notes = new ArrayList<Note>();

    public ChapterContents(Context context, String filename) {//constructor for author within author - contents are confusing
        try {
            SAXParserFactory parserFactory = SAXParserFactory.newInstance();
            SAXParser parser = parserFactory.newSAXParser();
            XMLReader reader = parser.getXMLReader();
            try {
                InputStream is = Helper.inputStreamForFile(context, filename + ".xml");
                parser.parse(is, new ChapterContents.DelphinLoader(reader));
                is.close();
            } catch (Exception e) {
            }
        } catch (Exception e) {
        }
    }

    class DelphinLoader extends DefaultHandler {
        XMLReader xmlReader;
        private StringBuilder stringBuilder;

        public DelphinLoader(XMLReader reader) {
            stringBuilder = new StringBuilder();
            xmlReader = reader;
        }

        @Override
        public void startElement(String uri, String localName, String name, Attributes attributes) throws SAXException {
            stringBuilder.setLength(0);
            if (name.equalsIgnoreCase("note")) {
                index = Integer.parseInt(attributes.getValue(0));
                word = attributes.getValue(1);
            }
        }

        @Override
        public void endElement(String s, String s1, String name) throws SAXException { //we only care about end element - this is where we grab actual text info
            if (name.equalsIgnoreCase("sentence")) {
                sentences.add(stringBuilder.toString());
            }
            if (name.equalsIgnoreCase("note")) {
                Note note = new Note(index, word, stringBuilder.toString());
                notes.add(note);
            }
        }

        @Override
        public void characters(char[] ac, int i, int j) throws SAXException {
            stringBuilder.append(ac, i, j);
        }
    }
}