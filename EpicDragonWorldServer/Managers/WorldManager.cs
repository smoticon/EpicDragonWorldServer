using System.Collections.Concurrent;
using System.Collections.Generic;
using System.Linq;

/**
 * Author: Pantelis Andrianakis
 * Date: November 7th 2018
 */
class WorldManager
{
    private static readonly BlockingCollection<GameClient> ONLINE_CLIENTS = new BlockingCollection<GameClient>();
    private static readonly ConcurrentDictionary<long, Player> PLAYER_OBJECTS = new ConcurrentDictionary<long, Player>();
    private static readonly ConcurrentDictionary<long, WorldObject> GAME_OBJECTS = new ConcurrentDictionary<long, WorldObject>();
    public static readonly int VISIBILITY_RADIUS = 10000;
    // TODO: Separate data to WorldRegions.

    public static void AddObject(WorldObject obj)
    {
        if (obj.IsPlayer())
        {
            if (!PLAYER_OBJECTS.Values.Contains(obj))
            {
                ONLINE_CLIENTS.Add(obj.AsPlayer().GetClient());
                PLAYER_OBJECTS.TryAdd(obj.GetObjectId(), obj.AsPlayer());

                // Log world access.
                if (Config.LOG_WORLD)
                {
                    LogManager.LogWorld("Player [" + obj.AsPlayer().GetName() + "] Account [" + obj.AsPlayer().GetClient().GetAccountName() + "] Entered the world.");
                }
            }
        }
        else if (!GAME_OBJECTS.Values.Contains(obj))
        {
            GAME_OBJECTS.TryAdd(obj.GetObjectId(), obj);
        }
    }

    public static void RemoveObject(WorldObject obj)
    {
        // Broadcast deletion to nearby players.
        foreach (Player player in GetVisiblePlayers(obj))
        {
            player.ChannelSend(new DeleteObject(obj));
        }

        // Remove from list and take necessary actions.
        if (obj.IsPlayer())
        {
            ((IDictionary<long, Player>)PLAYER_OBJECTS).Remove(obj.GetObjectId());
            // Store player.
            obj.AsPlayer().StoreMe();

            // Log world access.
            if (Config.LOG_WORLD)
            {
                LogManager.LogWorld("Player [" + obj.AsPlayer().GetName() + "] Account [" + obj.AsPlayer().GetClient().GetAccountName() + "] Left the world.");
            }
        }
        else
        {
            ((IDictionary<long, WorldObject>)GAME_OBJECTS).Remove(obj.GetObjectId());
        }
    }

    public static List<WorldObject> GetVisibleObjects(WorldObject obj)
    {
        List<WorldObject> result = new List<WorldObject>();
        foreach (Player player in PLAYER_OBJECTS.Values)
        {
            if (player.GetObjectId() == obj.GetObjectId())
            {
                continue;
            }
            if (obj.CalculateDistance(player) < VISIBILITY_RADIUS)
            {
                result.Add(player);
            }
        }
        foreach (WorldObject wo in GAME_OBJECTS.Values)
        {
            if (wo.GetObjectId() == obj.GetObjectId())
            {
                continue;
            }
            if (obj.CalculateDistance(wo) < VISIBILITY_RADIUS)
            {
                result.Add(wo);
            }
        }
        return result;
    }

    public static List<Player> GetVisiblePlayers(WorldObject obj)
    {
        List<Player> result = new List<Player>();
        foreach (Player player in PLAYER_OBJECTS.Values)
        {
            if (player.GetObjectId() == obj.GetObjectId())
            {
                continue;
            }
            if (obj.CalculateDistance(player) < VISIBILITY_RADIUS + 500) // Need the extra distance to send location of objects getting out of range.
            {
                result.Add(player);
            }
        }
        return result;
    }

    public static WorldObject GetObject(long objectId)
    {
        if (PLAYER_OBJECTS.ContainsKey(objectId))
        {
            return PLAYER_OBJECTS[objectId];
        }
        return GAME_OBJECTS[objectId];
    }

    public static Player GetPlayerByName(string name)
    {
        foreach (Player player in PLAYER_OBJECTS.Values)
        {
            if (player.GetName().ToLowerInvariant().Equals(name.ToLowerInvariant()))
            {
                return player;
            }
        }
        return null;
    }

    public static int GetOnlineCount()
    {
        return ONLINE_CLIENTS.Count;
    }

    public static void AddClient(GameClient client)
    {
        if (!ONLINE_CLIENTS.Contains(client))
        {
            ONLINE_CLIENTS.Add(client);
        }
    }

    public static void RemoveClient(GameClient client)
    {
        // Store and remove player.
        Player player = client.GetActiveChar();
        if (player != null)
        {
            RemoveObject(player);
        }

        // Remove from list.
        ONLINE_CLIENTS.TryTake(out client);
    }

    public static GameClient GetClientByAccountName(string accountName)
    {
        foreach (GameClient client in ONLINE_CLIENTS)
        {
            if (client.GetAccountName().Equals(accountName))
            {
                return client;
            }
        }
        return null;
    }
}
