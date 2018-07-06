package com.epicdragonworld.gameserver.network.packets.receivable;

import com.epicdragonworld.gameserver.managers.WorldManager;
import com.epicdragonworld.gameserver.model.WorldObject;
import com.epicdragonworld.gameserver.model.actor.instance.PlayerInstance;
import com.epicdragonworld.gameserver.network.GameClient;
import com.epicdragonworld.gameserver.network.ReceivablePacket;
import com.epicdragonworld.gameserver.network.packets.sendable.MoveToLocation;

/**
 * @author Pantelis Andrianakis
 */
public class LocationUpdate
{
	public LocationUpdate(GameClient client, ReceivablePacket packet)
	{
		// Read data.
		final float posX = (float) packet.readDouble(); // TODO: Client WriteFloat
		final float posY = (float) packet.readDouble(); // TODO: Client WriteFloat
		final float posZ = (float) packet.readDouble(); // TODO: Client WriteFloat
		
		// Update player location.
		final PlayerInstance player = client.getActiveChar();
		if (player != null)
		{
			player.getLocation().setX(posX);
			player.getLocation().setY(posY);
			player.getLocation().setZ(posZ);
			
			// Broadcast movement.
			for (WorldObject object : WorldManager.getInstance().getVisibleObjects(player))
			{
				if (object.isPlayer())
				{
					((PlayerInstance) object).channelSend(new MoveToLocation(player));
				}
				// TODO: Other objects.
			}
		}
	}
}
