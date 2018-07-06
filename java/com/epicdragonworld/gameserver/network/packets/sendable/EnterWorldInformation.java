package com.epicdragonworld.gameserver.network.packets.sendable;

import com.epicdragonworld.gameserver.model.actor.instance.PlayerInstance;
import com.epicdragonworld.gameserver.network.SendablePacket;

/**
 * @author Pantelis Andrianakis
 */
public class EnterWorldInformation extends SendablePacket
{
	public EnterWorldInformation(PlayerInstance player)
	{
		// Packet id.
		writeShort(5);
		// TODO: Send player information.
	}
}
