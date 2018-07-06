package com.epicdragonworld.gameserver.model;

import com.epicdragonworld.gameserver.managers.IdManager;

/**
 * @author Pantelis Andrianakis
 */
public class WorldObject
{
	private final int _objectId;
	private Location _location = new Location(0, -1000, 0, 0);
	
	public WorldObject()
	{
		// All object ids are generated upon object creation.
		_objectId = IdManager.getInstance().getNextId();
	}
	
	public int getObjectId()
	{
		return _objectId;
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
	
	public boolean isPlayer()
	{
		return false;
	}
	
	public void deleteMe()
	{
		// Release unused id.
		IdManager.getInstance().releaseId(_objectId);
	}
}
