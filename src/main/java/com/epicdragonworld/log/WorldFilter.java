package com.epicdragonworld.log;

import java.util.logging.Filter;
import java.util.logging.LogRecord;

/**
 * @author Pantelis Andrianakis
 */
public class WorldFilter implements Filter
{
	@Override
	public boolean isLoggable(LogRecord record)
	{
		return "world".equals(record.getLoggerName());
	}
}
