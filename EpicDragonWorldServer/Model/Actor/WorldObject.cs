using System;

/**
 * @author Pantelis Andrianakis
 */
public class WorldObject
{
    private readonly long objectId = IdManager.GetNextId();
    private readonly DateTime spawnTime = DateTime.Now;
    private LocationHolder location = new LocationHolder(0, -1000, 0);

    public long GetObjectId()
    {
        return objectId;
    }

    public DateTime GetSpawnTime()
    {
        return spawnTime;
    }

    public LocationHolder GetLocation()
    {
        return location;
    }

    public void SetLocation(LocationHolder location)
    {
        this.location = location;
    }

    /**
	 * Calculates distance between this WorldObject and given x, y , z.
	 * @param x the X coordinate
	 * @param y the Y coordinate
	 * @param z the Z coordinate
	 * @return distance between object and given x, y, z.
	 */
    public double CalculateDistance(float x, float y, float z)
    {
        return Math.Pow(x - location.GetX(), 2) + Math.Pow(y - location.GetY(), 2) + Math.Pow(z - location.GetZ(), 2);
    }

    /**
	 * Calculates distance between this WorldObject and another WorldObject.
	 * @param object WorldObject
	 * @return distance between object and given x, y, z.
	 */
    public double CalculateDistance(WorldObject obj)
    {
        return CalculateDistance(obj.GetLocation().GetX(), obj.GetLocation().GetY(), obj.GetLocation().GetZ());
    }

    /**
	 * Verify if object is instance of Creature.
	 * @return {@code true} if object is instance of Creature, {@code false} otherwise.
	 */
    public virtual bool IsCreature()
    {
        return false;
    }

    /**
	 * @return {@link Creature} instance if current object is such, {@code null} otherwise.
	 */
    public virtual Creature AsCreature()
    {
        return null;
    }

    /**
	 * Verify if object is instance of Player.
	 * @return {@code true} if object is instance of Player, {@code false} otherwise.
	 */
    public virtual bool IsPlayer()
    {
        return false;
    }

    /**
	 * @return {@link Player} instance if current object is such, {@code null} otherwise.
	 */
    public virtual Player AsPlayer()
    {
        return null;
    }

    /**
	 * Verify if object is instance of Monster.
	 * @return {@code true} if object is instance of Monster, {@code false} otherwise.
	 */
    //public virtual bool IsMonster()
    //{
    //    return false;
    //}

    /**
	 * @return {@link Monster} instance if current object is such, {@code null} otherwise.
	 */
    //public virtual Monster AsMonster()
    //{
    //    return null;
    //}

    /**
	 * Verify if object is instance of Npc.
	 * @return {@code true} if object is instance of Npc, {@code false} otherwise.
	 */
    //public virtual bool IsNpc()
    //{
    //    return false;
    //}

    /**
	 * @return {@link Npc} instance if current object is such, {@code null} otherwise.
	 */
    //public virtual Npc AsNpc()
    //{
    //    return null;
    //}
}
