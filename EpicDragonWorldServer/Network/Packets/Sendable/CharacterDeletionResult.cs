/**
 * @author Pantelis Andrianakis
 */
public class CharacterDeletionResult : SendablePacket
{
    public CharacterDeletionResult()
    {
        // Send the data.
        WriteShort(4); // Packet id.
    }
}
