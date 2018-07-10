package com.epicdragonworld.log;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Formatter;
import java.util.logging.LogRecord;

import com.epicdragonworld.Config;

/**
 * @author Pantelis Andrianakis
 */
public class ConsoleLogFormatter extends Formatter
{
	private final SimpleDateFormat dateFmt = new SimpleDateFormat("dd/MM HH:mm:ss");
	
	@Override
	public String format(LogRecord record)
	{
		final StringBuilder output = new StringBuilder(500);
		output.append("[" + dateFmt.format(new Date(record.getMillis())) + "] " + record.getMessage() + Config.EOL);
		
		if (record.getThrown() != null)
		{
			try
			{
				final StringWriter sw = new StringWriter();
				record.getThrown().printStackTrace(new PrintWriter(sw));
				output.append(sw.toString() + Config.EOL);
			}
			catch (Exception ex)
			{
			}
		}
		return output.toString();
	}
}
