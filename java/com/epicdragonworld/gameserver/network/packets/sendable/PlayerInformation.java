package com.epicdragonworld.gameserver.network.packets.sendable;

import com.epicdragonworld.gameserver.model.actor.instance.PlayerInstance;
import com.epicdragonworld.gameserver.network.SendablePacket;

/**
 * @author Pantelis Andrianakis
 */
public class PlayerInformation extends SendablePacket
{
	public PlayerInformation(PlayerInstance player)
	{
		// Packet id.
		writeShort(6);
		// Player information.
		writeLong(player.getObjectId());
		writeShort(player.getClassId());
		writeString(player.getName());
		writeFloat(player.getLocation().getX());
		writeFloat(player.getLocation().getY());
		writeFloat(player.getLocation().getZ());
		writeInt(player.getLocation().getHeading());
	}
}
