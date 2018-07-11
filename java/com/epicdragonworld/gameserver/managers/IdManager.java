package com.epicdragonworld.gameserver.managers;

/**
 * @author Pantelis Andrianakis
 */
public class IdManager
{
	private static volatile long _lastId = 0;
	
	public static synchronized long getNextId()
	{
		return _lastId++;
	}
}
