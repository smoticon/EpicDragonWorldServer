package com.epicdragonworld.gameserver.network.packets.sendable;

import com.epicdragonworld.gameserver.model.WorldObject;
import com.epicdragonworld.gameserver.network.SendablePacket;

/**
 * @author Pantelis Andrianakis
 */
public class MoveToLocation extends SendablePacket
{
	public MoveToLocation(WorldObject object, float angleY, int animState, int waterState)
	{
		// Send the data.
		writeShort(9); // Packet id.
		writeLong(object.getObjectId());
		writeFloat(object.getLocation().getX());
		writeFloat(object.getLocation().getY());
		writeFloat(object.getLocation().getZ());
		writeFloat(angleY);
		writeShort(animState);
		writeShort(waterState);
	}
}
