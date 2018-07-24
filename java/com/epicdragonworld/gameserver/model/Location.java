package com.epicdragonworld.gameserver.model;

/**
 * @author Pantelis Andrianakis
 */
public class Location
{
	float _x;
	float _y;
	float _z;
	int _heading;
	
	public Location(float x, float y, float z)
	{
		this(x, y, z, 0);
	}
	
	public Location(float x, float y, float z, int heading)
	{
		_x = x;
		_y = y;
		_z = z;
		_heading = heading;
	}
	
	public float getX()
	{
		return _x;
	}
	
	public void setX(float x)
	{
		_x = x;
	}
	
	public float getY()
	{
		return _y;
	}
	
	public void setY(float y)
	{
		_y = y;
	}
	
	public float getZ()
	{
		return _z;
	}
	
	public void setZ(float z)
	{
		_z = z;
	}
	
	public int getHeading()
	{
		return _heading;
	}
	
	public void setHeading(int heading)
	{
		_heading = heading;
	}
}
