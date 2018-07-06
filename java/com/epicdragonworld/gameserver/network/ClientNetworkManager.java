package com.epicdragonworld.gameserver.network;

import com.epicdragonworld.Config;
import com.epicdragonworld.gameserver.managers.NetworkManager;

/**
 * @author Nos
 */
public class ClientNetworkManager extends NetworkManager
{
	protected ClientNetworkManager()
	{
		super(EventLoopGroupManager.getInstance().getBossGroup(), EventLoopGroupManager.getInstance().getWorkerGroup(), new ClientInitializer(), Config.GAMESERVER_HOSTNAME, Config.GAMESERVER_PORT);
	}
	
	public static ClientNetworkManager getInstance()
	{
		return SingletonHolder._instance;
	}
	
	private static class SingletonHolder
	{
		protected static final ClientNetworkManager _instance = new ClientNetworkManager();
	}
}
