package com.shane87.myjournal;

import java.util.ArrayList;

import com.shane87.myjournal.JournalContract.JournalDb;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

public class Journal{
	
	private final static String TAG = "MyJournal.Journal";
	JournalDbHelper mDbHelper;
	Context appContext;
	ArrayList<JournalEntry> entries;
	
	Journal(Context context)
	{
		Log.v(TAG, "Begining constructor");
		appContext = context;
		Log.v(TAG, "Saved context");
		mDbHelper = new JournalDbHelper(context);
		Log.v(TAG, "JournalDbHelper object instantiated!");
		entries = new ArrayList<JournalEntry>(20);
		Log.v(TAG, "entries ArrayList created!");
		getEntries();
		Log.v(TAG, "getEntries returned!");
	}
	
	public void createNewEntry(String title, String entry, String date, String time)
	{
		int newId = getNewId();
		SQLiteDatabase db = mDbHelper.getWritableDatabase();
		entries.add(0, new JournalEntry(newId, title, entry, date, time));
		
		ContentValues values = new ContentValues();
		values.put(JournalDb.COLUMN_NAME_ENTRY_ID, newId);
		values.put(JournalDb.COLUMN_NAME_TITLE, title);
		values.put(JournalDb.COLUMN_NAME_ENTRY, entry);
		values.put(JournalDb.COLUMN_NAME_DATE, date);
		values.put(JournalDb.COLUMN_NAME_TIME, time);
		
		long newRowId = db.insert(JournalDb.TABLE_NAME, null, values);
		if (newRowId == -1)
		{
			entries.remove(0);
			Toast toast = Toast.makeText(appContext, R.string.entryCreateErrorToast, Toast.LENGTH_LONG);
			toast.show();
		}
	}
	
	public ArrayList<JournalEntry> getEntriesList()
	{
		return entries;
	}
	
	private void getEntries()
	{
		Log.v(TAG, "Starting getEntries()!");
		String[] projection = {JournalDb._ID, JournalDb.COLUMN_NAME_ENTRY_ID,
				JournalDb.COLUMN_NAME_TITLE, JournalDb.COLUMN_NAME_ENTRY,
				JournalDb.COLUMN_NAME_DATE, JournalDb.COLUMN_NAME_TIME};
		Log.v(TAG, "Projection defined!");
		String sortorder = JournalDb.COLUMN_NAME_ENTRY_ID + " DESC";
		Log.v(TAG, "Sort order defined!");
		SQLiteDatabase db = mDbHelper.getReadableDatabase();
		Cursor cursor = db.query(JournalDb.TABLE_NAME, projection, null, null, null, null, sortorder, "10");
		Log.v(TAG, "DB queried!");
		
		if (cursor.getCount() == 0)
		{
			Log.v(TAG, "DB empty!");
			return;
		}
		
		cursor.moveToFirst();
		
		int idIndex = cursor.getColumnIndex(JournalDb.COLUMN_NAME_ENTRY_ID);
		int titleIndex = cursor.getColumnIndex(JournalDb.COLUMN_NAME_TITLE);
		int entryIndex = cursor.getColumnIndex(JournalDb.COLUMN_NAME_ENTRY);
		int dateIndex = cursor.getColumnIndex(JournalDb.COLUMN_NAME_DATE);
		int timeIndex = cursor.getColumnIndex(JournalDb.COLUMN_NAME_TIME);
		
		for (int i = 0; i < 20; i++)
		{
			entries.add(i, new JournalEntry(
					cursor.getInt(idIndex),
					cursor.getString(titleIndex),
					cursor.getString(entryIndex),
					cursor.getString(dateIndex),
					cursor.getString(timeIndex)));
			if (cursor.isLast())
				break;
			cursor.moveToNext();
		}
	}
	
	private int getNewId()
	{
		String[] projection = { JournalDb._ID, JournalDb.COLUMN_NAME_ENTRY_ID };
		String sortorder = JournalDb.COLUMN_NAME_ENTRY_ID + " DESC";
		SQLiteDatabase db = mDbHelper.getWritableDatabase();
		
		Cursor cursor = db.query(JournalDb.TABLE_NAME, projection, null, null, null, null, sortorder, "10");
		
		if (cursor.getCount() == 0)
			return 1;
		
		cursor.moveToFirst();
		
		return cursor.getInt(cursor.getColumnIndex(JournalDb.COLUMN_NAME_ENTRY_ID)) + 1;
	}
}