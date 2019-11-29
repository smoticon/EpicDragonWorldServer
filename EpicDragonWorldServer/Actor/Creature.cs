/**
 * Author: Pantelis Andrianakis
 * Date: November 7th 2018
 */
public class Creature : WorldObject
{
    private long currentHp = 0;
    private long currentMp = 0;
    private WorldObject target;

    public void SetCurrentHp(long value)
    {
        currentHp = value;
    }

    public long GetCurrentHp()
    {
        return currentHp;
    }

    public void SetCurrentMp(long value)
    {
        currentMp = value;
    }

    public long GetCurrentMp()
    {
        return currentMp;
    }

    public void SetTarget(WorldObject worldObject)
    {
        target = worldObject;
    }

    public WorldObject GetTarget()
    {
        return target;
    }

    public override bool IsCreature()
    {
        return true;
    }

    public override Creature AsCreature()
    {
        return this;
    }

    public override string ToString()
    {
        return "Creature [" + GetObjectId() + "]";
    }
}
