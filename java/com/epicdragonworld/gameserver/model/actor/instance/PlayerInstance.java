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
	private static final String STORE_CHARACTER = "UPDATE characters SET name=?, class_id=? WHERE account=? AND name=?";
	
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
	
	public void storeMe()
	{
		try (Connection con = DatabaseManager.getInstance().getConnection();
			PreparedStatement ps = con.prepareStatement(STORE_CHARACTER))
		{
			ps.setString(1, _name);
			ps.setInt(2, _classId);
			// TODO: Save location.
			ps.setString(3, _client.getAccountName());
			ps.setString(4, _name);
			ps.execute();
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
