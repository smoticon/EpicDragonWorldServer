package com.epicdragonworld.gameserver.model.actor;

import com.epicdragonworld.gameserver.managers.ThreadPoolManager;
import com.epicdragonworld.gameserver.managers.WorldManager;
import com.epicdragonworld.gameserver.model.holders.NpcTemplateHolder;
import com.epicdragonworld.gameserver.model.holders.SpawnHolder;

/**
 * @author Pantelis Andrianakis
 */
public class Npc extends Creature
{
	private final NpcTemplateHolder _template;
	private final SpawnHolder _spawn;
	
	public Npc(NpcTemplateHolder template, SpawnHolder spawn)
	{
		_template = template;
		_spawn = spawn;
		
		initialize();
	}
	
	private void initialize()
	{
		setLocation(_spawn.getLocation());
		setLevel(_template.getLevel());
		setSTA(_template.getSTA());
		setSTR(_template.getSTR());
		setDEX(_template.getDEX());
		setINT(_template.getINT());
		
		WorldManager.addObject(this);
	}
	
	public NpcTemplateHolder getTemplate()
	{
		return _template;
	}
	
	public SpawnHolder getSpawn()
	{
		return _spawn;
	}
	
	@Override
	public void onDeath()
	{
		super.onDeath();
		
		// Create a re-spawn task.
		final int respawnTime = _spawn.getRespawnTime();
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
