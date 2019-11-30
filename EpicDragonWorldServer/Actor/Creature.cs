/**
 * Author: Pantelis Andrianakis
 * Date: November 7th 2018
 */
public class Creature : WorldObject
{
    private long currentHp = 0;
    private long currentMp = 0;
    private WorldObject target;

    // TODO: Implement Player level data.
    // TODO: Implement Creature stats holder.
    public long GetMaxHp()
    {
        return 100;
    }

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
