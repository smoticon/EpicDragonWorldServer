package com.epicdragonworld.gameserver.model.actor;

import com.epicdragonworld.gameserver.model.holders.NpcHolder;
import com.epicdragonworld.gameserver.model.holders.SpawnHolder;

/**
 * @author Pantelis Andrianakis
 */
public class Monster extends Npc
{
	public Monster(NpcHolder npcHolder, SpawnHolder spawnHolder)
	{
		super(npcHolder, spawnHolder);
		
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
