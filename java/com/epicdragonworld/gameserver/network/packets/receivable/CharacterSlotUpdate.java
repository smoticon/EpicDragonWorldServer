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
