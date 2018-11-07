using System.Threading;

/**
 * @author Pantelis Andrianakis
 */
public class IdManager
{
    private static long lastId = 0;

    public static long GetNextId()
    {
        return Interlocked.Increment(ref lastId);
    }
}
