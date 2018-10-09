package com.epicdragonworld.log;

import java.util.logging.Filter;
import java.util.logging.LogRecord;

/**
 * @author Pantelis Andrianakis
 */
public class ChatFilter implements Filter
{
	@Override
	public boolean isLoggable(LogRecord record)
	{
		return "chat".equals(record.getLoggerName());
	}
}
