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
package com.epicdragonworld.gameserver.network.packets.sendable;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import com.epicdragonworld.gameserver.managers.DatabaseManager;
import com.epicdragonworld.gameserver.model.holders.CharacterDataHolder;
import com.epicdragonworld.gameserver.network.SendablePacket;

/**
 * @author Pantelis Andrianakis
 */
public class CharacterSelectionInfoResult extends SendablePacket
{
	private static final Logger LOGGER = Logger.getLogger(CharacterSelectionInfoResult.class.getName());
	
	private static final String PLAYER_QUERY = "SELECT * FROM players WHERE account=?";
	
	public CharacterSelectionInfoResult(String accountName)
	{
		// Local data.
		final List<CharacterDataHolder> characterList = new ArrayList<>();
		
		// Get data from database.
		try (Connection con = DatabaseManager.getInstance().getConnection();
			PreparedStatement ps = con.prepareStatement(PLAYER_QUERY))
		{
			ps.setString(1, accountName);
			try (ResultSet rset = ps.executeQuery())
			{
				while (rset.next())
				{
					final CharacterDataHolder characterData = new CharacterDataHolder();
					characterData.setSlot(rset.getByte("slot"));
					characterData.setName(rset.getString("name"));
					characterData.setClassId(rset.getByte("class_id"));
					characterData.setX(rset.getLong("x"));
					characterData.setY(rset.getLong("y"));
					characterData.setZ(rset.getLong("z"));
					characterData.setHeading(rset.getInt("heading"));
					characterData.setExperience(rset.getLong("experience"));
					characterData.setHp(rset.getLong("hp"));
					characterData.setMp(rset.getLong("mp"));
					characterData.setAccessLevel(rset.getByte("access_level"));
					characterData.setItemHead(rset.getInt("item_head"));
					characterData.setItemChest(rset.getInt("item_chest"));
					characterData.setItemGloves(rset.getInt("item_gloves"));
					characterData.setItemLegs(rset.getInt("item_legs"));
					characterData.setItemBoots(rset.getInt("item_boots"));
					characterData.setItemRightHand(rset.getInt("item_right_hand"));
					characterData.setItemLeftHand(rset.getInt("item_left_hand"));
					characterList.add(characterData);
				}
			}
		}
		catch (Exception e)
		{
			LOGGER.warning(e.getMessage());
		}
		
		// Send the data.
		writeShort(2); // Packet id.
		writeByte(characterList.size());
		for (CharacterDataHolder characterData : characterList)
		{
			writeByte(characterData.getSlot());
			writeString(characterData.getName());
			writeByte(characterData.getClassId());
			writeLong(characterData.getX());
			writeLong(characterData.getY());
			writeLong(characterData.getZ());
			writeInt(characterData.getHeading());
			writeLong(characterData.getExperience());
			writeLong(characterData.getHp());
			writeLong(characterData.getMp());
			writeByte(characterData.getAccessLevel());
			writeInt(characterData.getItemHead());
			writeInt(characterData.getItemChest());
			writeInt(characterData.getItemGloves());
			writeInt(characterData.getItemLegs());
			writeInt(characterData.getItemBoots());
			writeInt(characterData.getItemRightHand());
			writeInt(characterData.getItemLeftHand());
		}
	}
}
