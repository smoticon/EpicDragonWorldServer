package com.epicdragonworld.gameserver.managers;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import com.epicdragonworld.gameserver.model.WorldObject;
import com.epicdragonworld.gameserver.model.actor.instance.PlayerInstance;
import com.epicdragonworld.gameserver.network.GameClient;
import com.epicdragonworld.gameserver.network.packets.sendable.DeleteObject;

/**
 * @author Pantelis Andrianakis
 */
public class WorldManager
{
	private final List<WorldObject> GAME_OBJECTS = new CopyOnWriteArrayList<>();
	private final List<GameClient> ONLINE_CLIENTS = new CopyOnWriteArrayList<>();
	private final int VISIBILITY_RANGE = 3000;
	
	public WorldManager()
	{
	}
	
	public synchronized void addObject(WorldObject object)
	{
		if (!GAME_OBJECTS.contains(object))
		{
			if (object.isPlayer())
			{
				ONLINE_CLIENTS.add(((PlayerInstance) object).getClient());
			}
			GAME_OBJECTS.add(object);
		}
	}
	
	public void removeObject(WorldObject object)
	{
		for (WorldObject obj : getVisibleObjects(object))
		{
			if (obj.isPlayer())
			{
				((PlayerInstance) obj).channelSend(new DeleteObject(object));
			}
		}
		GAME_OBJECTS.remove(object);
	}
	
	public List<WorldObject> getVisibleObjects(WorldObject object)
	{
		final List<WorldObject> result = new ArrayList<>();
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
	
	public int getOnlineCount()
	{
		return ONLINE_CLIENTS.size();
	}
	
	public void addClient(GameClient client)
	{
		if (!ONLINE_CLIENTS.contains(client))
		{
			ONLINE_CLIENTS.add(client);
		}
	}
	
	public void removeClient(GameClient client)
	{
		final PlayerInstance player = client.getActiveChar();
		if (player != null)
		{
			player.storeMe();
			removeObject(player);
		}
		ONLINE_CLIENTS.remove(client);
	}
	
	public void removeClientByAccountName(String accountName)
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
	
	public GameClient getClientByAccountName(String accountName)
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
	
	public static WorldManager getInstance()
	{
		return SingletonHolder.INSTANCE;
	}
	
	private static class SingletonHolder
	{
		protected static final WorldManager INSTANCE = new WorldManager();
	}
}
