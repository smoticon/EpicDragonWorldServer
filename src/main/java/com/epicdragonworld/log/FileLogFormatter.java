package com.epicdragonworld.log;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Formatter;
import java.util.logging.LogRecord;

import com.epicdragonworld.Config;

/**
 * @author Pantelis Andrianakis
 */
public class FileLogFormatter extends Formatter
{
	private static final String TAB = "\t";
	private final SimpleDateFormat dateFmt = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss,SSS");
	
	@Override
	public String format(LogRecord record)
	{
		return dateFmt.format(new Date(record.getMillis())) + TAB + record.getLevel().getName() + TAB + String.valueOf(record.getThreadID()) + TAB + record.getLoggerName() + TAB + record.getMessage() + Config.EOL;
	}
}
