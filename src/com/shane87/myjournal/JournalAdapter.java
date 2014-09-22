package com.shane87.myjournal;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class JournalAdapter extends BaseAdapter 
{
	private Context appContext;
	private ArrayList<JournalEntry> entries;
	private LayoutInflater inflater;
	
	public JournalAdapter(Context context, ArrayList<JournalEntry> e)
	{
		appContext = context;
		entries = e;
		inflater = LayoutInflater.from(appContext);
	}
	
	static class JournalViewHolder
	{
		TextView titleView;
		TextView dateView;
	}
	
	@Override
	public int getCount()
	{
		return entries.size();
	}
	
	@Override
	public Object getItem(int position)
	{
		return entries.get(position);
	}
	
	@Override
	public long getItemId(int position)
	{
		return position;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent)
	{
		JournalViewHolder viewHolder;
		
		if (convertView == null)
		{
			convertView = inflater.inflate(R.layout.entry_layout, parent, false);
			viewHolder = new JournalViewHolder();
			viewHolder.titleView = (TextView) convertView.findViewById(R.id.titleTextView);
			viewHolder.dateView = (TextView) convertView.findViewById(R.id.dateTextView);
			convertView.setTag(viewHolder);
		}
		else
		{
			viewHolder = (JournalViewHolder) convertView.getTag();
		}
		
		JournalEntry entry = entries.get(position);
		
		viewHolder.titleView.setText(entry.getTitle());
		viewHolder.dateView.setText(entry.getDate());
		
		return convertView;
	}
}
