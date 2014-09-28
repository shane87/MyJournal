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
	private static final String ENTRY_ID = "com.shane87.MyJournal.ENTRY_ID";
	private static final int EDIT_ENTRY_REQUEST = 0;
	private static final int ENTRY_CREATED = 1;
	//Journal object that abstracts all of the journal functions, including
	//journal formatting and db access
	private static Journal mJournal;
	private final static String TAG = "MyJournal.MainActivity";
	private static ListView mListView;
	private static JournalAdapter mJournalAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);        
        setContentView(R.layout.activity_main);
        
        //Instantiate the journal object
        Log.v(TAG, "Contnent View Set, Instantiating Journal object");
        mJournal = new Journal(getApplicationContext());
        
        //Get a pointer to the main list view that will show existing entries
        mListView = (ListView) findViewById(R.id.entriesListView);
        //Attach the journal adapter to the list view, passing the adapter the
        //current entries list from the main journal object
        mJournalAdapter = new JournalAdapter(getApplicationContext(), mJournal.getEntriesList());
        mListView.setAdapter(mJournalAdapter);
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
    
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
    	if (requestCode == EDIT_ENTRY_REQUEST)
    	{
    		if (resultCode == ENTRY_CREATED)
    		{
    			mJournalAdapter = new JournalAdapter(getApplicationContext(), mJournal.getEntriesList());
    			mListView.setAdapter(mJournalAdapter);
    		}
    	}
    }
    
    //Method to handle clicks on the new entry button
    public void newEntry(View view) 
    {
    	//Create an intent to launch the edit entry activity
    	Intent intent = new Intent(this, EditEntryActivity.class);
    	//Put an extra on the intent to specify a new entry (id = 0)
    	intent.putExtra(ENTRY_ID, 0);
    	//Launch the intent
    	startActivityForResult(intent, EDIT_ENTRY_REQUEST);
    }
    
    public static Journal getJournal()
    {
    	return mJournal;
    }
    
    public static String getEntryIdIntentTag()
    {
    	return ENTRY_ID;
    }
    
    public static int getEntryCreatedResult()
    {
    	return ENTRY_CREATED;
    }
}
