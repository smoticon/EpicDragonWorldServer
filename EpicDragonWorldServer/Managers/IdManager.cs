using System.Threading;

/**
 * Author: Pantelis Andrianakis
 * Date: November 7th 2018
 */
class IdManager
{
    static long lastId = 0;

    public static long GetNextId()
    {
        return Interlocked.Increment(ref lastId);
    }
}
