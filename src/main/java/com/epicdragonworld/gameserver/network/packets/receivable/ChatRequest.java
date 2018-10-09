package com.epicdragonworld.gameserver.network.packets.receivable;

import com.epicdragonworld.gameserver.managers.ChatManager;
import com.epicdragonworld.gameserver.model.actor.Player;
import com.epicdragonworld.gameserver.network.GameClient;
import com.epicdragonworld.gameserver.network.ReceivablePacket;

/**
 * @author Pantelis Andrianakis
 */
public class ChatRequest
{
	public ChatRequest(GameClient client, ReceivablePacket packet)
	{
		// Read data.
		final String message = packet.readString();
		
		// Handle message.
		final Player sender = client.getActiveChar();
		if (sender != null)
		{
			ChatManager.handleChat(sender, message);
		}
	}
}
