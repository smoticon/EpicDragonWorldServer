package com.epicdragonworld.gameserver.network.packets.receivable;

import com.epicdragonworld.gameserver.managers.WorldManager;
import com.epicdragonworld.gameserver.model.WorldObject;
import com.epicdragonworld.gameserver.model.actor.instance.PlayerInstance;
import com.epicdragonworld.gameserver.network.GameClient;
import com.epicdragonworld.gameserver.network.ReceivablePacket;
import com.epicdragonworld.gameserver.network.packets.sendable.PlayerInformation;

/**
 * @author Pantelis Andrianakis
 */
public class ObjectInfoRequest
{
	public ObjectInfoRequest(GameClient client, ReceivablePacket packet)
	{
		// Read data.
		final long objectId = packet.readLong();
		
		// Get the acting player.
		final PlayerInstance player = client.getActiveChar();
		// Send the information.
		for (WorldObject object : WorldManager.getInstance().getVisibleObjects(player))
		{
			if (object.getObjectId() == objectId)
			{
				if (object.isPlayer())
				{
					// Send the information to the current player.
					final PlayerInstance otherPlayer = (PlayerInstance) object;
					client.channelSend(new PlayerInformation(otherPlayer));
					// Send information to the other player as well.
					final PlayerInformation playerInfo = new PlayerInformation(player);
					otherPlayer.channelSend(playerInfo);
				}
				// TODO: Other objects.
				break;
			}
		}
	}
}
