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
package com.epicdragonworld.gameserver.network.packets.receivable;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.logging.Logger;

import com.epicdragonworld.gameserver.managers.DatabaseManager;
import com.epicdragonworld.gameserver.network.GameClient;
import com.epicdragonworld.gameserver.network.ReceivablePacket;

/**
 * @author Pantelis Andrianakis
 */
public class CharacterSlotUpdate
{
	private static final Logger LOGGER = Logger.getLogger(CharacterSlotUpdate.class.getName());
	
	private static final String CHARACTER_SLOT_UPDATE_QUERY = "UPDATE characters SET slot=? WHERE account=? AND slot=?";
	
	public CharacterSlotUpdate(GameClient client, ReceivablePacket packet)
	{
		// Read data.
		final byte oldSlot = (byte) packet.readByte();
		final byte newSlot = (byte) packet.readByte();
		
		// Database queries.
		try (Connection con = DatabaseManager.getInstance().getConnection();
			PreparedStatement ps = con.prepareStatement(CHARACTER_SLOT_UPDATE_QUERY))
		{
			ps.setInt(1, 0);
			ps.setString(2, client.getAccountName());
			ps.setInt(3, oldSlot);
			ps.execute();
		}
		catch (Exception e)
		{
			LOGGER.warning(e.getMessage());
		}
		
		try (Connection con = DatabaseManager.getInstance().getConnection();
			PreparedStatement ps = con.prepareStatement(CHARACTER_SLOT_UPDATE_QUERY))
		{
			ps.setInt(1, oldSlot);
			ps.setString(2, client.getAccountName());
			ps.setInt(3, newSlot);
			ps.execute();
		}
		catch (Exception e)
		{
			LOGGER.warning(e.getMessage());
		}
		
		try (Connection con = DatabaseManager.getInstance().getConnection();
			PreparedStatement ps = con.prepareStatement(CHARACTER_SLOT_UPDATE_QUERY))
		{
			ps.setInt(1, newSlot);
			ps.setString(2, client.getAccountName());
			ps.setInt(3, 0);
			ps.execute();
		}
		catch (Exception e)
		{
			LOGGER.warning(e.getMessage());
		}
	}
}
