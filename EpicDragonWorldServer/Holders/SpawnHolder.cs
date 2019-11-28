/**
 * Author: Pantelis Andrianakis
 * Date: November 28th 2019
 */
public class SpawnHolder
{
    private readonly LocationHolder location;
    private readonly int respawnDelay;

    public SpawnHolder(LocationHolder location, int respawnDelay)
    {
        this.location = location;
        this.respawnDelay = respawnDelay;
    }

    public LocationHolder GetLocation()
    {
        return location;
    }

    public int GetRespawnDelay()
    {
        return respawnDelay;
    }
}
