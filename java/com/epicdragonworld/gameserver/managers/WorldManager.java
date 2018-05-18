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
package com.epicdragonworld.gameserver.managers;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import com.epicdragonworld.gameserver.model.GameObject;

/**
 * @author Pantelis Andrianakis
 */
public class WorldManager
{
	private final List<GameObject> GAME_OBJECTS = new CopyOnWriteArrayList<>();
	private final int VISIBILITY_RANGE = 3000;
	
	public WorldManager()
	{
	}
	
	public synchronized void add(GameObject object)
	{
		if (!GAME_OBJECTS.contains(object))
		{
			GAME_OBJECTS.add(object);
		}
	}
	
	public void remove(GameObject object)
	{
		GAME_OBJECTS.remove(object);
	}
	
	public List<GameObject> getVisibleObjects(GameObject object)
	{
		final List<GameObject> result = new ArrayList<>();
		for (GameObject obj : GAME_OBJECTS)
		{
			if (obj == object)
			{
				continue;
			}
			if (object.calculateDistance(obj) < VISIBILITY_RANGE)
			{
				result.add(obj);
			}
		}
		return result;
	}
	
	public static WorldManager getInstance()
	{
		return SingletonHolder.INSTANCE;
	}
	
	private static class SingletonHolder
	{
		protected static final WorldManager INSTANCE = new WorldManager();
	}
}
