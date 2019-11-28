/**
 * Author: Pantelis Andrianakis
 * Date: November 28th 2019
 */
public class Monster : Npc
{
    public Monster(NpcHolder npcHolder, SpawnHolder spawnHolder) : base(npcHolder, spawnHolder)
    {
    }

    public override bool IsMonster()
    {
        return true;
    }

    public override Monster AsMonster()
    {
        return this;
    }

    public override string ToString()
    {
        return "MONSTER [" + GetNpcHolder().GetNpcId() + "]";
    }
}
