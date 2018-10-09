package com.epicdragonworld.gameserver.model.holders;

import com.epicdragonworld.gameserver.model.Location;

/**
 * @author Pantelis Andrianakis
 */
public class SpawnHolder
{
	private final Location _location;
	private final int _respawnTime;
	
	public SpawnHolder(Location location, int respawnTime)
	{
		_location = location;
		_respawnTime = respawnTime;
	}
	
	public Location getLocation()
	{
		return _location;
	}
	
	public int getRespawnTime()
	{
		return _respawnTime;
	}
}
