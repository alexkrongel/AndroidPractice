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

enum Genre {
    Poetry, Prose
}

public class Work {
    public String title;
    public Genre genre;

    public ArrayList<Book> books = new ArrayList<Book>();


    public Work(XMLReader xmlReader, DefaultHandler parent) {
        final DelphinLoader handler = new DelphinLoader(xmlReader, parent);
        xmlReader.setContentHandler(handler);
    }

    class DelphinLoader extends DefaultHandler {
        XMLReader xmlReader;
        DefaultHandler parent;
        private StringBuilder stringBuilder;

        public DelphinLoader(XMLReader reader, DefaultHandler parent) {
            stringBuilder = new StringBuilder();
            xmlReader = reader;
            this.parent = parent;
        }

        @Override
        public void startElement(String uri, String localName, String name, Attributes attributes) throws SAXException {
            stringBuilder.setLength(0);
            Book book = new Book(xmlReader, this);
            books.add(book);
        }

        @Override
        public void endElement(String s, String s1, String name) throws SAXException { //we only care about end element - this is where we grab actual text info
            if (name.equalsIgnoreCase("title")) {
                Work.this.title = stringBuilder.toString();
            } else if (name.equalsIgnoreCase("genre")) {
                if (name.equalsIgnoreCase("poetry")) {
                    Work.this.genre = Genre.Poetry;
                } else if (name.equalsIgnoreCase("prose")) {
                    Work.this.genre = Genre.Prose;
                }
            } else if (name.equalsIgnoreCase("book")) {
                xmlReader.setContentHandler(parent);
                parent.endElement(s, s1, name);
            }
        }

        @Override
        public void characters(char[] ac, int i, int j) throws SAXException {
            stringBuilder.append(ac, i, j);
        }
    }

}
