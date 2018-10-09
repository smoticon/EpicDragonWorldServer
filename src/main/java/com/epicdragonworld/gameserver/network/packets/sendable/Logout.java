package com.epicdragonworld.gameserver.network.packets.sendable;

import com.epicdragonworld.gameserver.managers.WorldManager;
import com.epicdragonworld.gameserver.network.SendablePacket;

/**
 * @author Pantelis Andrianakis
 */
public class Logout extends SendablePacket
{
	public Logout(String accountName)
	{
		// Send the data.
		writeShort(8); // Packet id.
		
		// Extreme case precaution in case of client crash or forced close.
		WorldManager.removeClientByAccountName(accountName);
	}
}
