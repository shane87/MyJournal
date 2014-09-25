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
	private JournalDbHelper mDbHelper;
	private Context appContext;
	private ArrayList<JournalEntry> entries;
	
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
		values.put(JournalDb.getColumnNameEntryId(), newId);
		values.put(JournalDb.getColumnNameTitle(), title);
		values.put(JournalDb.getColumnNameEntry(), entry);
		values.put(JournalDb.getColumnNameDate(), date);
		values.put(JournalDb.getColumnNameTime(), time);
		
		long newRowId = db.insert(JournalDb.getTableName(), null, values);
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
		String[] projection = {JournalDb.getColumnNameUniqueId(), JournalDb.getColumnNameEntryId(),
				JournalDb.getColumnNameTitle(), JournalDb.getColumnNameEntry(),
				JournalDb.getColumnNameDate(), JournalDb.getColumnNameTime()};
		Log.v(TAG, "Projection defined!");
		String sortorder = JournalDb.getColumnNameEntryId() + " DESC";
		Log.v(TAG, "Sort order defined!");
		SQLiteDatabase db = mDbHelper.getReadableDatabase();
		Cursor cursor = db.query(JournalDb.getTableName(), projection, null, null, null, null, sortorder, "10");
		Log.v(TAG, "DB queried!");
		
		if (cursor.getCount() == 0)
		{
			Log.v(TAG, "DB empty!");
			return;
		}
		
		cursor.moveToFirst();
		
		int idIndex = cursor.getColumnIndex(JournalDb.getColumnNameEntryId());
		int titleIndex = cursor.getColumnIndex(JournalDb.getColumnNameTitle());
		int entryIndex = cursor.getColumnIndex(JournalDb.getColumnNameEntry());
		int dateIndex = cursor.getColumnIndex(JournalDb.getColumnNameDate());
		int timeIndex = cursor.getColumnIndex(JournalDb.getColumnNameTime());
		
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
		String[] projection = { JournalDb.getColumnNameUniqueId(), JournalDb.getColumnNameEntryId() };
		String sortorder = JournalDb.getColumnNameEntryId() + " DESC";
		SQLiteDatabase db = mDbHelper.getWritableDatabase();
		
		Cursor cursor = db.query(JournalDb.getTableName(), projection, null, null, null, null, sortorder, "10");
		
		if (cursor.getCount() == 0)
			return 1;
		
		cursor.moveToFirst();
		
		return cursor.getInt(cursor.getColumnIndex(JournalDb.getColumnNameEntryId())) + 1;
	}
}