package alexkrongel.justplayaround;


import android.app.Application;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;

import java.io.InputStream;
import java.util.ArrayList;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;


/**
 * Created by alexandrakrongel on 6/10/16.
 */

public class SharedApplication extends Application {
    private ArrayList<Author> authors = new ArrayList<Author>();

    @Override
    public void onCreate() {
        super.onCreate();
        loadXML("authors.xml");
        //  Log.d("AK", authors.get(1).works.get(0).books.get(0).chapters.get(0).getChapterContents(this).sentences.get(0));
        //    Log.d("AK", "authors count:" + authors.size());
        //    Log.d("AK", "authors 0 work 0:" + authors.get(0).works.get(0).title);
    }

    public void loadXML(String filename) {
        try {
            SAXParserFactory parserFactory = SAXParserFactory.newInstance();
            SAXParser parser = parserFactory.newSAXParser();
            XMLReader reader = parser.getXMLReader();

            try {
                InputStream is = Helper.inputStreamForFile(this, filename);
                parser.parse(is, new DelphinLoader(reader));
                //Log.d(main, "AK: done parsing");
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
            //  Log.d(main, "Start-element:" + name);
        }


        @Override
        public void endElement(String s, String s1, String name) throws SAXException { //we only care about end element - this is where we grab actual text info
            if (name.equalsIgnoreCase("author")) {
                Author author = new Author(SharedApplication.this, stringBuilder.toString());
                authors.add(author);
                //   Log.d(main, "AK Main Activity End-element" + name);
            }
        }

        @Override
        public void characters(char[] ac, int i, int j) throws SAXException {
            stringBuilder.append(ac, i, j);
        }
    }

        public ArrayList<Author> getAuthors() {
            return authors;
        }
}