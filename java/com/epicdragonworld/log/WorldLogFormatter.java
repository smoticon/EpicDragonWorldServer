package com.epicdragonworld.log;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Formatter;
import java.util.logging.LogRecord;

/**
 * @author Pantelis Andrianakis
 */
public class WorldLogFormatter extends Formatter
{
	private final static SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd MMM H:mm:ss");
	
	@Override
	public String format(LogRecord record)
	{
		return "[" + DATE_FORMAT.format(new Date(record.getMillis())) + "] " + record.getMessage() + System.lineSeparator();
	}
}
