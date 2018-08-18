package com.epicdragonworld.gameserver.network;

import com.epicdragonworld.gameserver.network.packets.receivable.AccountAuthenticationRequest;
import com.epicdragonworld.gameserver.network.packets.receivable.CharacterCreationRequest;
import com.epicdragonworld.gameserver.network.packets.receivable.CharacterDeletionRequest;
import com.epicdragonworld.gameserver.network.packets.receivable.CharacterSelectUpdate;
import com.epicdragonworld.gameserver.network.packets.receivable.CharacterSelectionInfoRequest;
import com.epicdragonworld.gameserver.network.packets.receivable.CharacterSlotUpdate;
import com.epicdragonworld.gameserver.network.packets.receivable.ChatRequest;
import com.epicdragonworld.gameserver.network.packets.receivable.EnterWorldRequest;
import com.epicdragonworld.gameserver.network.packets.receivable.LocationUpdate;
import com.epicdragonworld.gameserver.network.packets.receivable.ObjectInfoRequest;

/**
 * @author Pantelis Andrianakis
 */
public class RecievablePacketManager
{
	public static void handle(GameClient client, ReceivablePacket packet)
	{
		switch (packet.readShort()) // Packet id.
		{
			case 1:
			{
				new AccountAuthenticationRequest(client, packet);
				break;
			}
			case 2:
			{
				new CharacterSelectionInfoRequest(client, packet);
				break;
			}
			case 3:
			{
				new CharacterCreationRequest(client, packet);
				break;
			}
			case 4:
			{
				new CharacterDeletionRequest(client, packet);
				break;
			}
			case 5:
			{
				new CharacterSlotUpdate(client, packet);
				break;
			}
			case 6:
			{
				new CharacterSelectUpdate(client, packet);
				break;
			}
			case 7:
			{
				new EnterWorldRequest(client, packet);
				break;
			}
			case 8:
			{
				new LocationUpdate(client, packet);
				break;
			}
			case 9:
			{
				new ObjectInfoRequest(client, packet);
				break;
			}
			case 10:
			{
				new ChatRequest(client, packet);
				break;
			}
		}
	}
}
