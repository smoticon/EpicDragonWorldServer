package com.epicdragonworld.gameserver.model.actor;

import com.epicdragonworld.gameserver.model.holders.NpcTemplateHolder;
import com.epicdragonworld.gameserver.model.holders.SpawnHolder;

/**
 * @author Pantelis Andrianakis
 */
public class Monster extends Npc
{
	public Monster(NpcTemplateHolder template, SpawnHolder spawn)
	{
		super(template, spawn);
		
		// TODO: AI Tasks.
		// TODO: Loot corpse.
	}
	
	@Override
	public boolean isMonster()
	{
		return true;
	}
	
	@Override
	public Monster asMonster()
	{
		return this;
	}
}
