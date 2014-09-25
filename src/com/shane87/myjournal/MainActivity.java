package com.shane87.myjournal;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;


public class MainActivity extends ActionBarActivity {
	
	//String to specify the extras added to the intent that launches the
	//entry editor. Carries the entry id for the entry we are editing, or
	//0 for a new entry
	public static final String ENTRY_ID = "com.shane87.MyJournal.ENTRY_ID";
	//Journal object that abstracts all of the journal functions, including
	//journal formatting and db access
	private static Journal mJournal;
	private final static String TAG = "MyJournal.MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);        
        setContentView(R.layout.activity_main);
        
        //Instantiate the journal object
        Log.v(TAG, "Contnent View Set, Instantiating Journal object");
        mJournal = new Journal(getApplicationContext());
        
        //Get a pointer to the main list view that will show existing entries
        ListView mListView = (ListView) findViewById(R.id.entriesListView);
        //Attach the journal adapter to the list view, passing the adapter the
        //current entries list from the main journal object
        mListView.setAdapter(new JournalAdapter(getApplicationContext(), mJournal.getEntriesList()));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    
    //Method to handle clicks on the new entry button
    public void newEntry(View view) 
    {
    	//Create an intent to launch the edit entry activity
    	Intent intent = new Intent(this, EditEntryActivity.class);
    	//Put an extra on the intent to specify a new entry (id = 0)
    	intent.putExtra(ENTRY_ID, 0);
    	//Launch the intent
    	startActivity(intent);
    }
    
    public static Journal getJournal()
    {
    	return mJournal;
    }
}
