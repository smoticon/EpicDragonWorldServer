using System.Threading;

/**
 * Author: Pantelis Andrianakis
 * Date: November 7th 2018
 */
public class IdManager
{
    private static long lastId = 0;

    public static long GetNextId()
    {
        return Interlocked.Increment(ref lastId);
    }
}
