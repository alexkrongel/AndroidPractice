package alexkrongel.justplayaround;

import android.util.Log;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;

/**
 * Created by alexandrakrongel on 6/9/16.
 */

public class Book {
    public String title;
    public String heading;
    public String introduction;
    public String workTitle;

    public ArrayList<Chapter> chapters = new ArrayList<Chapter>(); //empty if poetry
    public ArrayList<Passage> passages = new ArrayList<Passage>(); //empty if prose

    public Book(XMLReader xmlReader, DefaultHandler parent) {
        final Book.DelphinLoader handler = new Book.DelphinLoader(xmlReader, parent);
        xmlReader.setContentHandler(handler);
    }

    class DelphinLoader extends DefaultHandler {
        XMLReader xmlReader;
        DefaultHandler parent;
        private StringBuilder stringBuilder;
        public int start;
        public int end;

        public DelphinLoader(XMLReader reader, DefaultHandler parent) {
            stringBuilder = new StringBuilder();
            xmlReader = reader;
            this.parent = parent;
        }

        @Override
        public void startElement(String uri, String localName, String name, Attributes attributes) throws SAXException {
            stringBuilder.setLength(0);
            if (name.equalsIgnoreCase("passage")) {
                start = Integer.parseInt(attributes.getValue(0));
                end = Integer.parseInt(attributes.getValue(1));
            }
        }

        @Override
        public void endElement(String s, String s1, String name) throws SAXException { //we only care about end element - this is where we grab actual text info
            if (name.equalsIgnoreCase("title")) {
                Book.this.title = stringBuilder.toString();
            } else if (name.equalsIgnoreCase("heading")) {
                Book.this.heading = stringBuilder.toString();
            } else if (name.equalsIgnoreCase("introduction")) {
                Book.this.introduction = stringBuilder.toString();
            } else if (name.equalsIgnoreCase("workTitle")) {
                Book.this.workTitle = stringBuilder.toString();
            } else if (name.equalsIgnoreCase("chapter")) {
                Chapter chapter = new Chapter(stringBuilder.toString());
                chapters.add(chapter);
            } else if (name.equalsIgnoreCase("passage")) {
                Passage passage = new Passage(stringBuilder.toString(), start, end);
                passages.add(passage);
            }
        }

        @Override
        public void characters(char[] ac, int i, int j) throws SAXException {
            stringBuilder.append(ac, i, j);
        }
    }
}