package com.shane87.myjournal;

import java.util.Date;

import android.support.v7.app.ActionBarActivity;
import android.text.format.DateFormat;
import android.text.format.Time;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class EditEntryActivity extends ActionBarActivity {
	
	//Global var for holding the id of the entry we are working with
	//Defaults to 0 for a new entry
	int CUR_ENTRY_ID = 0;
	//Date format object to format dates
	java.text.DateFormat dFormat;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit_entry);
		
		//Ensure that the app icon in the action bar works as an "Up" button
		getActionBar().setDisplayHomeAsUpEnabled(true);
		//Get a format object to format the date string for the date text view
		new DateFormat();
		dFormat = DateFormat.getMediumDateFormat(getApplicationContext());
		
		//Get the intent from the main activity, and pull the extra data
		//passed from main activity; default to 0 if no extra
		Intent intent = getIntent();
		int id = intent.getIntExtra(MainActivity.ENTRY_ID, 0);
		
		//Check to see if we are working with a new id (id == 0)
		if (id == 0)
		{
			//New entry, so change activity title to reflect that
			setTitle(R.string.editEntryActivityNew);
			//Get the current date and format it using the date format
			//instantiated in onCreate()
			String date = dFormat.format(new Date());
			
			//Get the text view for the date
			TextView dTextView = (TextView) findViewById(R.id.dateTextView);
			//Put the appropriately formatted date string into the date text view
			dTextView.setText(date);
			
		}
		//In this case we are working with an existing entry
		else
			//TO-DO: Implement existing entry editing
			CUR_ENTRY_ID = id;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.edit_entry, menu);
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
	
	//Method for saving the entry when save entry is pressed
	public void saveEntry(View view)
	{
		//Pointers to the text objects holding the text for the entry
		EditText titleEditText = (EditText) findViewById(R.id.entryTitleEditText);
		EditText entryEditText = (EditText) findViewById(R.id.editEntryEditText);
		TextView dateTextView = (TextView) findViewById(R.id.dateTextView);
		//Time object for getting current time.
		//NOTICE: may be moving to using only time objects, instead of a date object
		//        and a time object, as time objects also hold dates
		Time time = new Time();
		
		//Check to see if the title and/or entry fields are blank
		//If so, pop a toast error
		if (titleEditText.getText().toString().isEmpty())
		{
			Toast toast = Toast.makeText(getApplicationContext(), R.string.entryEmptyTitleError, Toast.LENGTH_SHORT);
			toast.show();
		}
		else if (entryEditText.getText().toString().isEmpty())
		{
			Toast toast = Toast.makeText(getApplicationContext(), R.string.entryEmptyEntryError, Toast.LENGTH_SHORT);
			toast.show();
		}
		//If both fields are NOT empty, continue with saving the data.
		else
		{
			//Get the current time
			time.setToNow();
			//Create a new entry using the Journal object from the main activity
			MainActivity.getJournal().createNewEntry(
					//Title string from title text box
					titleEditText.getText().toString(),
					//Entry string
					entryEditText.getText().toString(),
					//Date string
					dateTextView.getText().toString(),
					//Get the time portion of the string returned by the time object
					time.toString().substring(9));
			
			this.finish();
		}
	}
}
