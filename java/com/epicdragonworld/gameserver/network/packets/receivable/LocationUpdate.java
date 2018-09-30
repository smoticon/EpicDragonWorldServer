package com.epicdragonworld.gameserver.network.packets.receivable;

import com.epicdragonworld.gameserver.managers.WorldManager;
import com.epicdragonworld.gameserver.model.actor.Player;
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
		final float heading = (float) packet.readDouble(); // TODO: Client WriteFloat
		final int animState = packet.readShort();
		final int waterState = packet.readShort();
		
		// Update player location.
		final Player player = client.getActiveChar();
		if (player != null)
		{
			player.getLocation().setX(posX);
			player.getLocation().setY(posY);
			player.getLocation().setZ(posZ);
			player.getLocation().setHeading(heading);
			// Broadcast movement.
			for (Player nearby : WorldManager.getVisiblePlayers(player))
			{
				nearby.channelSend(new MoveToLocation(player, heading, animState, waterState));
			}
		}
	}
}
