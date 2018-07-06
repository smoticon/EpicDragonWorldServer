package com.epicdragonworld.gameserver.network.packets.receivable;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import com.epicdragonworld.gameserver.managers.DatabaseManager;
import com.epicdragonworld.gameserver.network.GameClient;
import com.epicdragonworld.gameserver.network.ReceivablePacket;
import com.epicdragonworld.gameserver.network.packets.sendable.CharacterDeletionResult;

/**
 * @author Pantelis Andrianakis
 */
public class CharacterDeletionRequest
{
	private static final Logger LOGGER = Logger.getLogger(CharacterDeletionRequest.class.getName());
	
	private static final String CHARACTER_DELETION_QUERY = "DELETE FROM characters WHERE account=? AND slot=?";
	private static final String ACCOUNT_CHARACTER_QUERY = "SELECT * FROM characters WHERE account=? ORDER BY slot ASC";
	private static final String CHARACTER_SLOT_UPDATE_QUERY = "UPDATE characters SET slot=?, selected=? WHERE name=?";
	
	public CharacterDeletionRequest(GameClient client, ReceivablePacket packet)
	{
		// Read data.
		final byte slot = (byte) packet.readByte();
		
		// Delete character.
		try (Connection con = DatabaseManager.getInstance().getConnection();
			PreparedStatement ps = con.prepareStatement(CHARACTER_DELETION_QUERY))
		{
			ps.setString(1, client.getAccountName());
			ps.setByte(2, slot);
			ps.execute();
		}
		catch (Exception e)
		{
			LOGGER.warning(e.getMessage());
		}
		
		// Get remaining character names.
		final List<String> characterNames = new ArrayList<>();
		try (Connection con = DatabaseManager.getInstance().getConnection();
			PreparedStatement ps = con.prepareStatement(ACCOUNT_CHARACTER_QUERY))
		{
			ps.setString(1, client.getAccountName());
			try (ResultSet rset = ps.executeQuery())
			{
				while (rset.next())
				{
					characterNames.add(rset.getString("name"));
				}
			}
		}
		catch (Exception e)
		{
			LOGGER.warning(e.getMessage());
		}
		
		// Order remaining character slots.
		byte counter = 0;
		for (String characterName : characterNames)
		{
			counter++;
			try (Connection con = DatabaseManager.getInstance().getConnection();
				PreparedStatement ps = con.prepareStatement(CHARACTER_SLOT_UPDATE_QUERY))
			{
				ps.setByte(1, counter);
				ps.setInt(2, ((counter == 1) && (slot == 1)) || (counter == (slot - 1)) ? 1 : 0);
				ps.setString(3, characterName);
				ps.execute();
			}
			catch (Exception e)
			{
				LOGGER.warning(e.getMessage());
			}
		}
		
		// Notify the client that character was deleted.
		client.channelSend(new CharacterDeletionResult());
	}
}
