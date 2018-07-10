package com.epicdragonworld.gameserver.managers;

public class IdManager
{
	private static long _lastId = System.currentTimeMillis();
	
	public IdManager()
	{
	}
	
	public synchronized long getNextId()
	{
		while (System.currentTimeMillis() == _lastId)
		{
			// Wait.
		}
		_lastId = System.currentTimeMillis();
		return _lastId;
	}
	
	public static IdManager getInstance()
	{
		return SingletonHolder.INSTANCE;
	}
	
	private static class SingletonHolder
	{
		protected static final IdManager INSTANCE = new IdManager();
	}
}
