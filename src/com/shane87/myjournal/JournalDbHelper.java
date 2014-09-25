package com.shane87.myjournal;

import com.shane87.myjournal.JournalContract.JournalDb;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class JournalDbHelper extends SQLiteOpenHelper{
	
	public static final int DATABASE_VERSION = 1;
	public static final String DATABASE_NAME = "JournalEntries.db";
	
	public JournalDbHelper(Context context)
	{
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}
	
	public void onCreate(SQLiteDatabase db)
	{
		db.execSQL(JournalDb.getSqlCreateEntries());
	}
	
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
	{
		
	}

}
