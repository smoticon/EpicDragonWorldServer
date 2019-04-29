using System;
using System.Runtime.CompilerServices;
using System.Threading.Tasks;

/**
 * Author: Pantelis Andrianakis
 * Date: November 7th 2018
 */
public class WorldObject
{
    private readonly long objectId = IdManager.GetNextId();
    private readonly DateTime spawnTime = DateTime.Now;
    private LocationHolder location = new LocationHolder(0, -1000, 0);
    private RegionHolder region = null;
    private bool isTeleporting = false;

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

    [MethodImpl(MethodImplOptions.Synchronized)]
    public void SetLocation(LocationHolder location)
    {
        this.location = location;

        // When changing location test for appropriate region.
        RegionHolder testRegion = WorldManager.GetRegion(this);
        if (!testRegion.Equals(region))
        {
            if (region != null)
            {
                // Remove this object from the region.
                region.RemoveObject(objectId);

                // Broadcast change to players left behind when teleporting.
                if (isTeleporting)
                {
                    DeleteObject deleteObject = new DeleteObject(this);
                    foreach (RegionHolder nearbyRegion in region.GetSurroundingRegions())
                    {
                        foreach (WorldObject obj in region.GetObjects())
                        {
                            if (obj == this)
                            {
                                continue;
                            }
                            if (obj.IsPlayer())
                            {
                                obj.AsPlayer().ChannelSend(deleteObject);
                            }
                        }
                    }
                }
            }
            region = testRegion;
            region.AddObject(this);
        }
    }

    public RegionHolder GetRegion()
    {
        return region;
    }

    public void SetTeleporting()
    {
        isTeleporting = true;
        Task.Delay(1000).ContinueWith(task => StopTeleporting());
    }

    private void StopTeleporting()
    {
        isTeleporting = false;

        // Broadcast location to nearby players after teleporting.
        LocationUpdate locationUpdate = new LocationUpdate(this);
        foreach (Player nearby in WorldManager.GetVisiblePlayers(this))
        {
            if (nearby.IsPlayer())
            {
                nearby.AsPlayer().ChannelSend(locationUpdate);
            }
            if (IsPlayer())
            {
                AsPlayer().ChannelSend(new LocationUpdate(nearby));
            }
        }
    }

    public bool IsTeleporting()
    {
        return isTeleporting;
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

    public override String ToString()
    {
        return "WorldObject [" + objectId + "]";
    }
}
