package com.epicdragonworld.gameserver.model;

import com.epicdragonworld.gameserver.managers.IdManager;
import com.epicdragonworld.gameserver.model.actor.Creature;
import com.epicdragonworld.gameserver.model.actor.Player;

/**
 * @author Pantelis Andrianakis
 */
public class WorldObject
{
	private final long _objectId = IdManager.getNextId();
	private final long _spawnTime = System.currentTimeMillis();
	private Location _location = new Location(0, -1000, 0, 0);
	
	public long getObjectId()
	{
		return _objectId;
	}
	
	public long getSpawnTime()
	{
		return _spawnTime;
	}
	
	public Location getLocation()
	{
		return _location;
	}
	
	public void setLocation(Location location)
	{
		_location = location;
	}
	
	/**
	 * Calculates distance between this GameObject and given x, y , z.
	 * @param x the X coordinate
	 * @param y the Y coordinate
	 * @param z the Z coordinate
	 * @return distance between object and given x, y, z.
	 */
	public double calculateDistance(float x, float y, float z)
	{
		return Math.pow(x - _location.getX(), 2) + Math.pow(y - _location.getY(), 2) + Math.pow(z - _location.getZ(), 2);
	}
	
	/**
	 * Calculates distance between this GameObject and another GameObject.
	 * @param object GameObject
	 * @return distance between object and given x, y, z.
	 */
	public double calculateDistance(WorldObject object)
	{
		return calculateDistance(object.getLocation().getX(), object.getLocation().getY(), object.getLocation().getZ());
	}
	
	/**
	 * Verify if object is instance of Creature.
	 * @return {@code true} if object is instance of Creature, {@code false} otherwise.
	 */
	public boolean isCreature()
	{
		return false;
	}
	
	/**
	 * @return {@link Creature} instance if current object is such, {@code null} otherwise.
	 */
	public Creature asCreature()
	{
		return null;
	}
	
	/**
	 * Verify if object is instance of Player.
	 * @return {@code true} if object is instance of Player, {@code false} otherwise.
	 */
	public boolean isPlayer()
	{
		return false;
	}
	
	/**
	 * @return {@link Player} instance if current object is such, {@code null} otherwise.
	 */
	public Player asPlayer()
	{
		return null;
	}
}
