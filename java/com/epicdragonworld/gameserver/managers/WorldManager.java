package com.epicdragonworld.gameserver.managers;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import com.epicdragonworld.gameserver.model.WorldObject;
import com.epicdragonworld.gameserver.model.actor.Player;
import com.epicdragonworld.gameserver.network.GameClient;
import com.epicdragonworld.gameserver.network.packets.sendable.DeleteObject;

/**
 * @author Pantelis Andrianakis
 */
public class WorldManager
{
	private static final List<GameClient> ONLINE_CLIENTS = new CopyOnWriteArrayList<>();
	private static final List<Player> PLAYER_OBJECTS = new CopyOnWriteArrayList<>();
	private static final List<WorldObject> GAME_OBJECTS = new CopyOnWriteArrayList<>();
	private static final int VISIBILITY_RANGE = 3000;
	
	public static void addObject(WorldObject object)
	{
		if (object.isPlayer())
		{
			if (!PLAYER_OBJECTS.contains(object))
			{
				ONLINE_CLIENTS.add(((Player) object).getClient());
				PLAYER_OBJECTS.add((Player) object);
			}
		}
		else if (!GAME_OBJECTS.contains(object))
		{
			GAME_OBJECTS.add(object);
		}
	}
	
	public static void removeObject(WorldObject object)
	{
		for (Player player : getVisiblePlayers(object))
		{
			player.channelSend(new DeleteObject(object));
		}
		if (object.isPlayer())
		{
			PLAYER_OBJECTS.remove(object);
		}
		else
		{
			GAME_OBJECTS.remove(object);
		}
	}
	
	public static List<WorldObject> getVisibleObjects(WorldObject object)
	{
		final List<WorldObject> result = new ArrayList<>();
		for (Player player : PLAYER_OBJECTS)
		{
			if (player == object)
			{
				continue;
			}
			if (object.calculateDistance(player) < VISIBILITY_RANGE)
			{
				result.add(player);
			}
		}
		for (WorldObject obj : GAME_OBJECTS)
		{
			if (obj == object)
			{
				continue;
			}
			if (object.calculateDistance(obj) < VISIBILITY_RANGE)
			{
				result.add(obj);
			}
		}
		return result;
	}
	
	public static List<Player> getVisiblePlayers(WorldObject object)
	{
		final List<Player> result = new ArrayList<>();
		for (Player player : PLAYER_OBJECTS)
		{
			if (player == object)
			{
				continue;
			}
			if (object.calculateDistance(player) < VISIBILITY_RANGE)
			{
				result.add(player);
			}
		}
		return result;
	}
	
	public static Player getPlayerByName(String name)
	{
		for (Player player : PLAYER_OBJECTS)
		{
			if (player == null)
			{
				continue;
			}
			if (player.getName().equalsIgnoreCase(name))
			{
				return player;
			}
		}
		return null;
	}
	
	public static int getOnlineCount()
	{
		return ONLINE_CLIENTS.size();
	}
	
	public static void addClient(GameClient client)
	{
		if (!ONLINE_CLIENTS.contains(client))
		{
			ONLINE_CLIENTS.add(client);
		}
	}
	
	public static void removeClient(GameClient client)
	{
		final Player player = client.getActiveChar();
		if (player != null)
		{
			player.storeMe();
			removeObject(player);
		}
		ONLINE_CLIENTS.remove(client);
	}
	
	public static void removeClientByAccountName(String accountName)
	{
		for (GameClient client : ONLINE_CLIENTS)
		{
			if (client.getAccountName().equals(accountName))
			{
				removeClient(client);
				break;
			}
		}
	}
	
	public static GameClient getClientByAccountName(String accountName)
	{
		for (GameClient client : ONLINE_CLIENTS)
		{
			if (client.getAccountName().equals(accountName))
			{
				return client;
			}
		}
		return null;
	}
}
