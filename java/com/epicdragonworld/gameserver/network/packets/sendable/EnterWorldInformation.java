package com.epicdragonworld.gameserver.network.packets.sendable;

import com.epicdragonworld.gameserver.model.actor.Player;
import com.epicdragonworld.gameserver.network.SendablePacket;

/**
 * @author Pantelis Andrianakis
 */
public class EnterWorldInformation extends SendablePacket
{
	public EnterWorldInformation(Player player)
	{
		// Packet id.
		writeShort(5);
		// TODO: Send player information.
	}
}
