package com.epicdragonworld.gameserver.network.packets.receivable;

import com.epicdragonworld.gameserver.managers.WorldManager;
import com.epicdragonworld.gameserver.model.WorldObject;
import com.epicdragonworld.gameserver.model.actor.Player;
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
		final Player player = new Player(client, characterName);
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
				final Player otherPlayer = (Player) object;
				client.channelSend(new PlayerInformation(otherPlayer));
				// Send information to the other player as well.
				otherPlayer.channelSend(playerInfo);
			}
			// TODO: Other objects.
		}
	}
}
