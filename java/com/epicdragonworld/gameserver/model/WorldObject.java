/*
 * This file is part of the Epic Dragon World project.
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
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
