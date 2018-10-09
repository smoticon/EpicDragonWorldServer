package com.epicdragonworld.gameserver.network.packets.sendable;

import com.epicdragonworld.gameserver.network.SendablePacket;

/**
 * @author Pantelis Andrianakis
 */
public class CharacterDeletionResult extends SendablePacket
{
	public CharacterDeletionResult()
	{
		// Send the data.
		writeShort(4); // Packet id.
	}
}
