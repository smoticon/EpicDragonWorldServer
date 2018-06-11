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
package com.epicdragonworld.gameserver.model.actor.instance;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.Logger;

import com.epicdragonworld.gameserver.managers.DatabaseManager;
import com.epicdragonworld.gameserver.model.actor.Creature;
import com.epicdragonworld.gameserver.network.GameClient;
import com.epicdragonworld.gameserver.network.SendablePacket;

/**
 * @author Pantelis Andrianakis
 */
public class PlayerInstance extends Creature
{
	private static final Logger LOGGER = Logger.getLogger(PlayerInstance.class.getName());
	private static final String RESTORE_CHARACTER = "SELECT * FROM characters WHERE name=?";
	
	private final GameClient _client;
	private final String _name;
	private int _classId = 0;
	
	public PlayerInstance(GameClient client, String name)
	{
		_client = client;
		_name = name;
		
		// Load information from database.
		try (Connection con = DatabaseManager.getInstance().getConnection();
			PreparedStatement ps = con.prepareStatement(RESTORE_CHARACTER))
		{
			ps.setString(1, name);
			try (ResultSet rset = ps.executeQuery())
			{
				while (rset.next())
				{
					_classId = rset.getInt("class_id");
					getLocation().setX(rset.getFloat("x"));
					getLocation().setY(rset.getFloat("y"));
					getLocation().setZ(rset.getFloat("z"));
				}
			}
		}
		catch (Exception e)
		{
			LOGGER.warning(e.getMessage());
		}
	}
	
	public GameClient getClient()
	{
		return _client;
	}
	
	public String getName()
	{
		return _name;
	}
	
	public int getClassId()
	{
		return _classId;
	}
	
	@Override
	public boolean isPlayer()
	{
		return true;
	}
	
	public void channelSend(SendablePacket packet)
	{
		_client.channelSend(packet);
	}
}
