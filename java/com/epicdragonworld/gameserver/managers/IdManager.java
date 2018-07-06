package com.epicdragonworld.gameserver.managers;

import java.util.BitSet;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Logger;

import com.epicdragonworld.util.PrimeFinder;

public class IdManager
{
	private final Logger LOGGER = Logger.getLogger(getClass().getName());
	
	private static final int FIRST_OID = 0x10000000;
	private static final int LAST_OID = 0x7FFFFFFF;
	private static final int FREE_OBJECT_ID_SIZE = LAST_OID - FIRST_OID;
	
	private BitSet _freeIds;
	private AtomicInteger _freeIdCount;
	private AtomicInteger _nextFreeId;
	
	protected class BitSetCapacityCheck implements Runnable
	{
		@Override
		public void run()
		{
			synchronized (IdManager.this)
			{
				if (reachingBitSetCapacity())
				{
					increaseBitSetCapacity();
				}
			}
		}
	}
	
	protected IdManager()
	{
		synchronized (IdManager.class)
		{
			ThreadPoolManager.scheduleAtFixedRate(new BitSetCapacityCheck(), 30000, 30000);
			initialize();
		}
		LOGGER.info(getClass().getSimpleName() + ": " + _freeIds.size() + " ids available.");
	}
	
	public void initialize()
	{
		try
		{
			_freeIds = new BitSet(PrimeFinder.nextPrime(100000));
			_freeIds.clear();
			_freeIdCount = new AtomicInteger(FREE_OBJECT_ID_SIZE);
			_nextFreeId = new AtomicInteger(_freeIds.nextClearBit(0));
		}
		catch (Exception e)
		{
			LOGGER.severe(getClass().getSimpleName() + ": Could not be initialized properly: " + e.getMessage());
		}
	}
	
	public synchronized void releaseId(int objectID)
	{
		if ((objectID - FIRST_OID) > -1)
		{
			_freeIds.clear(objectID - FIRST_OID);
			_freeIdCount.incrementAndGet();
		}
		else
		{
			LOGGER.warning(getClass().getSimpleName() + ": Release objectID " + objectID + " failed (< " + FIRST_OID + ")");
		}
	}
	
	public synchronized int getNextId()
	{
		final int newID = _nextFreeId.get();
		_freeIds.set(newID);
		_freeIdCount.decrementAndGet();
		
		final int nextFree = _freeIds.nextClearBit(newID) < 0 ? _freeIds.nextClearBit(0) : _freeIds.nextClearBit(newID);
		
		if (nextFree < 0)
		{
			if (_freeIds.size() >= FREE_OBJECT_ID_SIZE)
			{
				throw new NullPointerException("Ran out of valid Ids.");
			}
			increaseBitSetCapacity();
		}
		
		_nextFreeId.set(nextFree);
		
		return newID + FIRST_OID;
	}
	
	public synchronized int size()
	{
		return _freeIdCount.get();
	}
	
	protected synchronized int usedIdCount()
	{
		return size() - FIRST_OID;
	}
	
	protected synchronized boolean reachingBitSetCapacity()
	{
		return PrimeFinder.nextPrime((usedIdCount() * 11) / 10) > _freeIds.size();
	}
	
	protected synchronized void increaseBitSetCapacity()
	{
		final BitSet newBitSet = new BitSet(PrimeFinder.nextPrime((usedIdCount() * 11) / 10));
		newBitSet.or(_freeIds);
		_freeIds = newBitSet;
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
