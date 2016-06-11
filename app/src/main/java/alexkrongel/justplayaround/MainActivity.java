package alexkrongel.justplayaround;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Context;
import android.util.Log;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupClickListener;
import android.widget.ExpandableListView.OnGroupCollapseListener;
import android.widget.ExpandableListView.OnGroupExpandListener;
import android.widget.Toast;
//import android.widget.ExpandableListAdapter;

public class MainActivity extends Activity {

    protected SharedApplication app;
    private final String main = "MAINACTIVITY";

    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;


    ArrayList<Author> auth = new ArrayList<Author>();
    String x;
    List<String> listLinegroups; //listDataHeader
    HashMap<String, List<String>> listLineSummaries; //listDataChild

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        app = (SharedApplication)getApplication(); //data is accessed here in a shared class

        // get the listview
        expListView = (ExpandableListView) findViewById(R.id.lvExp);

        // preparing list data
        prepareListData();

        listAdapter = new ExpandableListAdapter(this, listLinegroups, listLineSummaries);
       // Log.d("AK", "Is the list adapter null" + listAdapter.toString());

        // setting list adapter
        expListView.setAdapter(listAdapter);

    }


    private void prepareListData() {
        //  Log.d("AK", authors.get(1).works.get(0).books.get(0).chapters.get(0).getChapterContents(this).sentences.get(0));


        //right now adding a line as a header for testing
        auth = app.getAuthors();
        x = auth.get(0).works.get(0).books.get(0).passages.get(0).getPassageContents(this).lines.get(0);

        //in the end we want groups of lines instead of the individual lines here and then a hash map of summaries mapped to lines
        //listLinegroups = new ArrayList<String>();
        listLinegroups = auth.get(0).works.get(0).books.get(0).passages.get(0).getPassageContents(this).lines;
        listLineSummaries = new HashMap<String, List<String>>();

        //map the line groups to summaries here for display

        listLinegroups.add(x);

/*
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();

        // Adding child data
        listDataHeader.add("Top 250");
        listDataHeader.add("Now Showing");
        listDataHeader.add("Coming Soon..");

        // Adding child data
        List<String> top250 = new ArrayList<String>();
        top250.add("The Shawshank Redemption");
        top250.add("The Godfather");
        top250.add("The Godfather: Part II");
        top250.add("Pulp Fiction");
        top250.add("The Good, the Bad and the Ugly");
        top250.add("The Dark Knight");
        top250.add("12 Angry Men");

        List<String> nowShowing = new ArrayList<String>();
        nowShowing.add("The Conjuring");
        nowShowing.add("Despicable Me 2");
        nowShowing.add("Turbo");
        nowShowing.add("Grown Ups 2");
        nowShowing.add("Red 2");
        nowShowing.add("The Wolverine");

        List<String> comingSoon = new ArrayList<String>();
        comingSoon.add("2 Guns");
        comingSoon.add("The Smurfs 2");
        comingSoon.add("The Spectacular Now");
        comingSoon.add("The Canyons");
        comingSoon.add("Europa Report");

        listDataChild.put(listDataHeader.get(0), top250); // Header, Child data
        listDataChild.put(listDataHeader.get(1), nowShowing);
        listDataChild.put(listDataHeader.get(2), comingSoon); */
    }

}