package com.epicdragonworld.gameserver.network.packets.receivable;

import com.epicdragonworld.gameserver.network.GameClient;
import com.epicdragonworld.gameserver.network.ReceivablePacket;
import com.epicdragonworld.gameserver.network.packets.sendable.CharacterSelectionInfoResult;

/**
 * @author Pantelis Andrianakis
 */
public class CharacterSelectionInfoRequest
{
	public CharacterSelectionInfoRequest(GameClient client, ReceivablePacket packet)
	{
		// Read data.
		final String accountName = packet.readString().toLowerCase();
		
		// If account has logged send the information.
		if (client.getAccountName().equals(accountName))
		{
			client.channelSend(new CharacterSelectionInfoResult(accountName));
		}
	}
}
