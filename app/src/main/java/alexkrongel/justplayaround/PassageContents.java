package alexkrongel.justplayaround;

import android.util.Log;
import android.content.Context;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;

import java.io.InputStream;
import java.util.ArrayList;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.w3c.dom.Node;

/**
 * Created by alexandrakrongel on 6/9/16.
 */

public class PassageContents {
    public ArrayList<String> lines = new ArrayList<String>();
    public ArrayList<String> summaries = new ArrayList<String>();
    public ArrayList<Note> notes = new ArrayList<Note>();

    private int index;
    private String word;
    private String detail;

    Node currNode;

    public PassageContents(Context context, String filename) {//constructor for author within author - contents are confusing
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
        public void startElement(String uri, String localName, String name, Attributes attributes) throws SAXException {
            stringBuilder.setLength(0);
            if (name.equalsIgnoreCase("note")) {
                index = Integer.parseInt(attributes.getValue(0));
                word = attributes.getValue(1);
            }
            if (name.equalsIgnoreCase("summary")) {
                index = Integer.parseInt(attributes.getValue(0));
            }
        }

        @Override
        public void endElement(String s, String s1, String name) throws SAXException { //we only care about end element - this is where we grab actual text info
            if (name.equalsIgnoreCase("line")) {
                lines.add(stringBuilder.toString());
            }
            if (name.equalsIgnoreCase("note")) {
                Note note = new Note(index, word, stringBuilder.toString());
                notes.add(note);
            }
            if (name.equalsIgnoreCase("summary")) {
                SummaryChunk summaryChunk = new SummaryChunk(index, stringBuilder.toString());
            }
        }

        @Override
        public void characters(char[] ac, int i, int j) throws SAXException {
            stringBuilder.append(ac, i, j);
        }
    }
}