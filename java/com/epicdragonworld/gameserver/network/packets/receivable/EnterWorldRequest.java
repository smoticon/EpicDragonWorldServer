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
package com.epicdragonworld.gameserver.network.packets.receivable;

import java.util.List;

import com.epicdragonworld.gameserver.managers.WorldManager;
import com.epicdragonworld.gameserver.model.GameObject;
import com.epicdragonworld.gameserver.model.actor.instance.PlayerInstance;
import com.epicdragonworld.gameserver.network.GameClient;
import com.epicdragonworld.gameserver.network.ReceivablePacket;
import com.epicdragonworld.gameserver.network.packets.sendable.PlayerInformation;

/**
 * @author Pantelis Andrianakis
 */
public class EnterWorldRequest
{
	public EnterWorldRequest(GameClient client, ReceivablePacket packet)
	{
		// Read data.
		final String characterName = packet.readString();
		
		// Create a new PlayerInstance.
		final PlayerInstance player = new PlayerInstance(client, characterName);
		// Assign this player to client.
		client.setActiveChar(player);
		
		// TODO: Send active player information to client.
		// client.channelSend(new EnterWorldInformation(player));
		
		// Send other visible object information.
		final List<GameObject> visibleObjects = WorldManager.getInstance().getVisibleObjects(player);
		for (GameObject object : visibleObjects)
		{
			if (object.isPlayer())
			{
				client.channelSend(new PlayerInformation((PlayerInstance) object));
			}
		}
	}
}
