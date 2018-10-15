package com.epicdragonworld.gameserver.network.packets.receivable;

import com.epicdragonworld.gameserver.managers.WorldManager;
import com.epicdragonworld.gameserver.model.Location;
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
		final int waterState = packet.readByte();
		
		// Update player location.
		final Player player = client.getActiveChar();
		final Location location = player.getLocation();
		location.setX(posX);
		location.setY(posY);
		location.setZ(posZ);
		location.setHeading(heading);
		
		// Broadcast movement.
		for (Player nearby : WorldManager.getVisiblePlayers(player))
		{
			nearby.channelSend(new MoveToLocation(player, heading, animState, waterState));
		}
	}
}
