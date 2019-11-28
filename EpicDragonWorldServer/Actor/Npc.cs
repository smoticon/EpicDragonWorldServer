using MySql.Data.MySqlClient;
using System;

/**
 * Author: Pantelis Andrianakis
 * Date: November 28th 2019
 */
public class Npc : Creature
{
    private readonly NpcHolder npcHolder;
    private readonly SpawnHolder spawnHolder;

    public Npc(NpcHolder npcHolder, SpawnHolder spawnHolder)
    {
        this.npcHolder = npcHolder;
        this.spawnHolder = spawnHolder;

        SetLocation(spawnHolder.GetLocation());
    }

    public NpcHolder GetNpcHolder()
    {
        return npcHolder;
    }

    public SpawnHolder GetSpawnHolder()
    {
        return spawnHolder;
    }

    public override bool IsNpc()
    {
        return true;
    }

    public override Npc AsNpc()
    {
        return this;
    }

    public override string ToString()
    {
        return "NPC [" + npcHolder.GetNpcId() + "]";
    }
}
