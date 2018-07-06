package com.epicdragonworld.gameserver.network.packets.receivable;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.Logger;

import com.epicdragonworld.Config;
import com.epicdragonworld.gameserver.managers.DatabaseManager;
import com.epicdragonworld.gameserver.network.GameClient;
import com.epicdragonworld.gameserver.network.ReceivablePacket;
import com.epicdragonworld.gameserver.network.packets.sendable.CharacterCreationResult;
import com.epicdragonworld.util.Util;

/**
 * @author Pantelis Andrianakis
 */
public class CharacterCreationRequest
{
	private static final Logger LOGGER = Logger.getLogger(CharacterCreationRequest.class.getName());
	
	private static final String ACCOUNT_CHARACTER_QUERY = "SELECT * FROM characters WHERE account=?";
	private static final String NAME_EXISTS_QUERY = "SELECT * FROM characters WHERE name=?";
	private static final String CHARACTER_SELECTED_RESET_QUERY = "UPDATE characters SET selected=0 WHERE account=?";
	private static final String CHARACTER_CREATE_QUERY = "INSERT INTO characters (account, name, slot, selected, class_id, location_name, x, y, z, heading, experience, hp, mp, item_head, item_chest, item_gloves, item_legs, item_boots, item_right_hand, item_left_hand) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	
	private static final int INVALID_NAME = 0;
	private static final int NAME_IS_TOO_SHORT = 1;
	private static final int NAME_ALREADY_EXISTS = 2;
	private static final int CANNOT_CREATE_ADDITIONAL_CHARACTERS = 3;
	private static final int SUCCESS = 100;
	
	public CharacterCreationRequest(GameClient client, ReceivablePacket packet)
	{
		// Make sure player has authenticated.
		if ((client.getAccountName() == null) || (client.getAccountName().length() == 0))
		{
			return;
		}
		
		// Read data.
		String characterName = packet.readString();
		final int classId = packet.readByte();
		
		// Replace illegal characters.
		for (char c : Util.ILLEGAL_CHARACTERS)
		{
			characterName = characterName.replace(c, '\'');
		}
		
		// Name character checks.
		if (characterName.contains("'"))
		{
			client.channelSend(new CharacterCreationResult(INVALID_NAME));
			return;
		}
		if ((characterName.length() < 2) || (characterName.length() > 12)) // 12 should not happen, checking it here in case of client cheat.
		{
			client.channelSend(new CharacterCreationResult(NAME_IS_TOO_SHORT));
			return;
		}
		
		// Account character count database check.
		int characterCount = 0;
		int lastCharacterSlot = 0;
		try (Connection con = DatabaseManager.getInstance().getConnection();
			PreparedStatement ps = con.prepareStatement(ACCOUNT_CHARACTER_QUERY))
		{
			ps.setString(1, client.getAccountName());
			try (ResultSet rset = ps.executeQuery())
			{
				while (rset.next())
				{
					characterCount++;
					final int slot = rset.getInt("slot");
					if (slot > lastCharacterSlot)
					{
						lastCharacterSlot = slot;
					}
				}
			}
		}
		catch (Exception e)
		{
			LOGGER.warning(e.getMessage());
		}
		if (characterCount >= Config.ACCOUNT_MAX_CHARACTERS)
		{
			client.channelSend(new CharacterCreationResult(CANNOT_CREATE_ADDITIONAL_CHARACTERS));
			return;
		}
		
		// Check database if name exists.
		boolean characterExists = false;
		try (Connection con = DatabaseManager.getInstance().getConnection();
			PreparedStatement ps = con.prepareStatement(NAME_EXISTS_QUERY))
		{
			ps.setString(1, characterName);
			try (ResultSet rset = ps.executeQuery())
			{
				while (rset.next())
				{
					characterExists = true;
				}
			}
		}
		catch (Exception e)
		{
			LOGGER.warning(e.getMessage());
		}
		if (characterExists)
		{
			client.channelSend(new CharacterCreationResult(NAME_ALREADY_EXISTS));
			return;
		}
		
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
		
		// Create character.
		try (Connection con = DatabaseManager.getInstance().getConnection();
			PreparedStatement ps = con.prepareStatement(CHARACTER_CREATE_QUERY))
		{
			ps.setString(1, client.getAccountName());
			ps.setString(2, characterName);
			ps.setInt(3, lastCharacterSlot + 1);
			ps.setInt(4, 1); // Selected character.
			ps.setInt(5, classId);
			ps.setString(6, "Start Location");
			ps.setFloat(7, 9.824759f); // TODO: Starting Location.
			ps.setFloat(8, -9.33f); // TODO: Starting Location.
			ps.setFloat(9, 0.2593288f); // TODO: Starting Location.
			ps.setInt(10, 0); // TODO: Starting Location.
			ps.setLong(11, 0); // TODO: Starting level experience.
			ps.setLong(12, 1); // TODO: Character stats HP.
			ps.setLong(13, 1); // TODO: Character stats MP.
			ps.setInt(14, 0); // TODO: Starting items.
			ps.setInt(15, 0); // TODO: Starting items.
			ps.setInt(16, 0); // TODO: Starting items.
			ps.setInt(17, 0); // TODO: Starting items.
			ps.setInt(18, 0); // TODO: Starting items.
			ps.setInt(19, 0); // TODO: Starting items.
			ps.setInt(20, 0); // TODO: Starting items.
			ps.execute();
		}
		catch (Exception e)
		{
			LOGGER.warning(e.getMessage());
		}
		
		// Send success result.
		client.channelSend(new CharacterCreationResult(SUCCESS));
	}
}
