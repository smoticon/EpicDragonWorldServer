/**
 * @author Pantelis Andrianakis
 */
public class Logout : SendablePacket
{
    public Logout(string accountName)
    {
        // Send the data.
        WriteShort(8); // Packet id.

        // Extreme case precaution in case of client crash or forced close.
        WorldManager.RemoveClientByAccountName(accountName);
    }
}
