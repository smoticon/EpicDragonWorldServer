package com.epicdragonworld.gameserver.model.actor;

import com.epicdragonworld.gameserver.managers.ThreadPoolManager;
import com.epicdragonworld.gameserver.managers.WorldManager;
import com.epicdragonworld.gameserver.model.holders.NpcHolder;
import com.epicdragonworld.gameserver.model.holders.SpawnHolder;

/**
 * @author Pantelis Andrianakis
 */
public class Npc extends Creature
{
	private final NpcHolder _npcHolder;
	private final SpawnHolder _spawnHolder;
	
	public Npc(NpcHolder npcHolder, SpawnHolder spawnHolder)
	{
		_npcHolder = npcHolder;
		_spawnHolder = spawnHolder;
		
		initialize();
	}
	
	private void initialize()
	{
		setLocation(_spawnHolder.getLocation());
		setLevel(_npcHolder.getLevel());
		setSTA(_npcHolder.getSTA());
		setSTR(_npcHolder.getSTR());
		setDEX(_npcHolder.getDEX());
		setINT(_npcHolder.getINT());
		setAlive(true);
		WorldManager.addObject(this);
	}
	
	public NpcHolder getNpcHolder()
	{
		return _npcHolder;
	}
	
	public SpawnHolder getSpawn()
	{
		return _spawnHolder;
	}
	
	@Override
	public void onDeath()
	{
		super.onDeath();
		
		// Create a re-spawn task.
		final int respawnTime = _spawnHolder.getRespawnTime();
		if (respawnTime > 0)
		{
			ThreadPoolManager.schedule(() ->
			{
				WorldManager.removeObject(this);
				initialize();
			}, respawnTime);
		}
		else
		{
			ThreadPoolManager.schedule(() ->
			{
				WorldManager.removeObject(this);
			}, 60000); // Remove corpse after 60 seconds.
		}
	}
	
	@Override
	public boolean isNpc()
	{
		return true;
	}
	
	@Override
	public Npc asNpc()
	{
		return this;
	}
}
