package com.shane87.myjournal;

import android.provider.BaseColumns;

public final class JournalContract {
	
	public JournalContract(){}
	
	public static abstract class JournalDb implements BaseColumns {
		//Database definitions, i.e. table name and column names
		private static final String TABLE_NAME = "myjournal";
		private static final String COLUMN_NAME_ENTRY_ID = "entryid";
		private static final String COLUMN_NAME_TITLE = "title";
		private static final String COLUMN_NAME_DATE = "date";
		private static final String COLUMN_NAME_TIME = "time";
		private static final String COLUMN_NAME_ENTRY = "entry";
		
		//Database actions
		private static final String TEXT_TYPE = " TEXT";
		private static final String INT_TYPE = " INTEGER";
		private static final String COMMA_SEP = ",";
		private static final String SQL_CREATE_ENTRIES = 
				"CREATE TABLE " + TABLE_NAME + " (" + _ID +
				" INTEGER PRIMARY KEY," + COLUMN_NAME_ENTRY_ID +
				INT_TYPE + COMMA_SEP + COLUMN_NAME_TITLE +
				TEXT_TYPE + COMMA_SEP + COLUMN_NAME_DATE +
				TEXT_TYPE + COMMA_SEP + COLUMN_NAME_TIME +
				TEXT_TYPE + COMMA_SEP + COLUMN_NAME_ENTRY +
				TEXT_TYPE + ");";
		
		public static String getTableName()
		{
			return TABLE_NAME;
		}
		
		public static String getColumnNameUniqueId()
		{
			return _ID;
		}
		
		public static String getColumnNameEntryId()
		{
			return COLUMN_NAME_ENTRY_ID;
		}
		
		public static String getColumnNameTitle()
		{
			return COLUMN_NAME_TITLE;
		}
		
		public static String getColumnNameDate()
		{
			return COLUMN_NAME_DATE;
		}
		
		public static String getColumnNameTime()
		{
			return COLUMN_NAME_TIME;
		}
		
		public static String getColumnNameEntry()
		{
			return COLUMN_NAME_ENTRY;
		}
		
		public static String getSqlCreateEntries()
		{
			return SQL_CREATE_ENTRIES;
		}
	}

}
