package com.slate.common;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Timestamp {
	private static final SimpleDateFormat TIME_FORMAT = new SimpleDateFormat("yyyyMMddHHmmss");
	private static String TIMESTAMP;
	
	public Timestamp()
	{		
		Calendar CALENDAR = Calendar.getInstance();
		TIMESTAMP = TIME_FORMAT.format(CALENDAR.getTime());
	}

	public void refresh()
	{
		Calendar CALENDAR = Calendar.getInstance();
		TIMESTAMP = TIME_FORMAT.format(CALENDAR.getTime());
	}
	
	public String getTimestamp()
	{
		return TIMESTAMP;
	}
}
