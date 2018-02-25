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

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import com.epicdragonworld.gameserver.model.GameObject;

/**
 * @author Pantelis Andrianakis
 */
public class WorldManager
{
	private final List<GameObject> GAME_OBJECTS = new CopyOnWriteArrayList<>();
	
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
	
	public static WorldManager getInstance()
	{
		return SingletonHolder.INSTANCE;
	}
	
	private static class SingletonHolder
	{
		protected static final WorldManager INSTANCE = new WorldManager();
	}
}
