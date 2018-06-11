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

import com.epicdragonworld.gameserver.managers.WorldManager;
import com.epicdragonworld.gameserver.model.WorldObject;
import com.epicdragonworld.gameserver.model.actor.instance.PlayerInstance;
import com.epicdragonworld.gameserver.network.GameClient;
import com.epicdragonworld.gameserver.network.ReceivablePacket;
import com.epicdragonworld.gameserver.network.packets.sendable.EnterWorldInformation;
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
		// Add object to the world.
		WorldManager.getInstance().addObject(player);
		// Assign this player to client.
		client.setActiveChar(player);
		// Send active player information to client.
		client.channelSend(new EnterWorldInformation(player));
		// Send and receive visible object information.
		final PlayerInformation playerInfo = new PlayerInformation(player);
		for (WorldObject object : WorldManager.getInstance().getVisibleObjects(player))
		{
			if (object.isPlayer())
			{
				// Send the information to the current player.
				final PlayerInstance otherPlayer = (PlayerInstance) object;
				client.channelSend(new PlayerInformation(otherPlayer));
				// Send information to the other player as well.
				otherPlayer.channelSend(playerInfo);
			}
			// TODO: Other objects.
		}
	}
}
