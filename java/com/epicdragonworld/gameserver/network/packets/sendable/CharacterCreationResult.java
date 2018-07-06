package com.epicdragonworld.gameserver.network.packets.sendable;

import com.epicdragonworld.gameserver.network.SendablePacket;

/**
 * @author Pantelis Andrianakis
 */
public class CharacterCreationResult extends SendablePacket
{
	public CharacterCreationResult(int result)
	{
		// Send the data.
		writeShort(3); // Packet id.
		writeByte(result);
	}
}
