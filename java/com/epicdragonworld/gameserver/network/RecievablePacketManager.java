/*
 * This file is part of the Epic Dragon World project.
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package com.epicdragonworld.gameserver.network;

import com.epicdragonworld.gameserver.network.packets.receivable.AccountAuthenticationRequest;
import com.epicdragonworld.gameserver.network.packets.receivable.CharacterCreationRequest;
import com.epicdragonworld.gameserver.network.packets.receivable.CharacterDeletionRequest;
import com.epicdragonworld.gameserver.network.packets.receivable.CharacterSelectUpdate;
import com.epicdragonworld.gameserver.network.packets.receivable.CharacterSelectionInfoRequest;
import com.epicdragonworld.gameserver.network.packets.receivable.CharacterSlotUpdate;

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
		}
	}
}
