package com.shane87.myjournal;

import android.provider.BaseColumns;

public final class JournalContract {
	
	public JournalContract(){}
	
	public static abstract class JournalDb implements BaseColumns {
		//Database definitions, i.e. table name and column names
		public static final String TABLE_NAME = "myjournal";
		public static final String COLUMN_NAME_ENTRY_ID = "entryid";
		public static final String COLUMN_NAME_TITLE = "title";
		public static final String COLUMN_NAME_DATE = "date";
		public static final String COLUMN_NAME_TIME = "time";
		public static final String COLUMN_NAME_ENTRY = "entry";
		
		//Database actions
		public static final String TEXT_TYPE = " TEXT";
		public static final String INT_TYPE = " INTEGER";
		public static final String COMMA_SEP = ",";
		public static final String SQL_CREATE_ENTRIES = 
				"CREATE TABLE " + TABLE_NAME + " (" + _ID +
				" INTEGER PRIMARY KEY," + COLUMN_NAME_ENTRY_ID +
				INT_TYPE + COMMA_SEP + COLUMN_NAME_TITLE +
				TEXT_TYPE + COMMA_SEP + COLUMN_NAME_DATE +
				TEXT_TYPE + COMMA_SEP + COLUMN_NAME_TIME +
				TEXT_TYPE + COMMA_SEP + COLUMN_NAME_ENTRY +
				TEXT_TYPE + ");";
	}

}
