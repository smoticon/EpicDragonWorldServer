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
public class CharacterSelectUpdate
{
	private static final Logger LOGGER = Logger.getLogger(CharacterSelectUpdate.class.getName());
	
	private static final String CHARACTER_SELECTED_RESET_QUERY = "UPDATE characters SET selected=0 WHERE account=?";
	private static final String CHARACTER_SELECTED_UPDATE_QUERY = "UPDATE characters SET selected=1 WHERE account=? AND slot=?";
	
	public CharacterSelectUpdate(GameClient client, ReceivablePacket packet)
	{
		// Read data.
		final int slot = packet.readByte();
		
		// Make existing characters selected value false.
		try (Connection con = DatabaseManager.getInstance().getConnection();
			PreparedStatement ps = con.prepareStatement(CHARACTER_SELECTED_RESET_QUERY))
		{
			ps.setString(1, client.getAccountName());
			ps.execute();
		}
		catch (Exception e)
		{
			LOGGER.warning(e.getMessage());
		}
		
		// Set character selected.
		try (Connection con = DatabaseManager.getInstance().getConnection();
			PreparedStatement ps = con.prepareStatement(CHARACTER_SELECTED_UPDATE_QUERY))
		{
			ps.setString(1, client.getAccountName());
			ps.setInt(2, slot);
			ps.execute();
		}
		catch (Exception e)
		{
			LOGGER.warning(e.getMessage());
		}
	}
}
