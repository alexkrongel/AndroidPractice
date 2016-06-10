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
 * Created by alexandrakrongel on 6/9/16.
 */

public class Author {
    public String shortName;
    public String longName;
    public String life;

    public ArrayList<Work> works = new ArrayList<Work>();

    public Author(Context context, String filename) {//constructor for author within author - contents are confusing
        try {
            SAXParserFactory parserFactory = SAXParserFactory.newInstance();
            SAXParser parser = parserFactory.newSAXParser();
            XMLReader reader = parser.getXMLReader();

            try {
                InputStream is = Helper.inputStreamForFile(context, filename + ".xml");
                parser.parse(is, new DelphinLoader(reader));
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
        public void startElement(String uri, String localName, String name, Attributes attributes) throws SAXException { //when we pass an xml over to another class to handle it, we do it in the start element method
            stringBuilder.setLength(0);
            Log.d("AK", "Inside author:" + name);
            if (name.equalsIgnoreCase("work")) {
                Work work = new Work(xmlReader, this);
                works.add(work);
            }
        }

        @Override
        public void endElement(String s, String s1, String name) throws SAXException { //we only care about end element - this is where we grab actual text info
            if (name.equalsIgnoreCase("shortName")) {
                Author.this.shortName = stringBuilder.toString();
            } else if (name.equalsIgnoreCase("longName")) {
                Author.this.longName = stringBuilder.toString();
            } else if (name.equalsIgnoreCase("life")) {
                Author.this.life = stringBuilder.toString();
            }
        }

        @Override
        public void characters(char[] ac, int i, int j) throws SAXException {
            stringBuilder.append(ac, i, j);
        }
    }
}