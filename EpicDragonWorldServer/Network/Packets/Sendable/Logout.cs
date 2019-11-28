/**
 * Author: Pantelis Andrianakis
 * Date: November 7th 2018
 */
public class Logout : SendablePacket
{
    public Logout()
    {
        // Send the data.
        WriteShort(9); // Packet id.
    }
}
