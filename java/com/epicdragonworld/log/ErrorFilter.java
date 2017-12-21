package com.epicdragonworld.log;

import java.util.logging.Filter;
import java.util.logging.LogRecord;

/**
 * @author Pantelis Andrianakis
 */
public class ErrorFilter implements Filter
{
	@Override
	public boolean isLoggable(LogRecord record)
	{
		return record.getThrown() != null;
	}
}
