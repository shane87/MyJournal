package com.shane87.myjournal;

public class JournalEntry {
	
	private static int eId;
	private static String eTitle;
	private static String eEntry;
	private static String eDate;
	private static String eTime;
	
	JournalEntry(int id, String title, String entry, String date, String time)
	{
		eId = id;
		eTitle = title;
		eEntry = entry;
		eDate = date;
		eTime = time;
	}
	
	public void setId(int id)
	{
		eId = id;
	}
	
	public void setTitle(String title)
	{
		eTitle = title;
	}
	
	public void setEntry(String entry)
	{
		eEntry = entry;
	}
	
	public void setDate(String date)
	{
		eDate = date;
	}
	
	public void setTime(String time)
	{
		eTime = time;
	}
	
	public int getId()
	{
		return eId;
	}
	
	public String getTitle()
	{
		return eTitle;
	}
	
	public String getEntry()
	{
		return eEntry;
	}
	
	public String getDate()
	{
		return eDate;
	}
	
	public String getTime()
	{
		return eTime;
	}

}
