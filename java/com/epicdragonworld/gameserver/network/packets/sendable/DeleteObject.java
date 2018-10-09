package com.epicdragonworld.gameserver.network.packets.sendable;

import com.epicdragonworld.gameserver.model.WorldObject;
import com.epicdragonworld.gameserver.network.SendablePacket;

/**
 * @author Pantelis Andrianakis
 */
public class DeleteObject extends SendablePacket
{
	public DeleteObject(WorldObject object)
	{
		// Send the data.
		writeShort(7); // Packet id.
		writeLong(object.getObjectId()); // ID of object to delete.
	}
}
