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
package com.epicdragonworld.gameserver.model.actor.instance;

import com.epicdragonworld.gameserver.model.actor.Creature;
import com.epicdragonworld.gameserver.network.GameClient;
import com.epicdragonworld.gameserver.network.SendablePacket;

/**
 * @author Pantelis Andrianakis
 */
public class PlayerInstance extends Creature
{
	private final GameClient _client;
	private final String _name;
	
	public PlayerInstance(GameClient client, String name)
	{
		_client = client;
		_name = name;
		
		// Load information from database.
		// TODO: Get XYZ
		getLocation().setX(9.824759f);
		getLocation().setY(-9.33f);
		getLocation().setZ(0.2593288f);
	}
	
	public GameClient getClient()
	{
		return _client;
	}
	
	public String getName()
	{
		return _name;
	}
	
	@Override
	public boolean isPlayer()
	{
		return true;
	}
	
	public void channelSend(SendablePacket packet)
	{
		_client.channelSend(packet);
	}
}
