package com.epicdragonworld.log;

import java.util.logging.LogManager;

/**
 * @author Pantelis Andrianakis
 */
public class ServerLogManager extends LogManager
{
	public ServerLogManager()
	{
	}
	
	@Override
	public void reset()
	{
		// do nothing
	}
	
	public void doReset()
	{
		super.reset();
	}
}
