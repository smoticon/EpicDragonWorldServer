/**
 * @author Pantelis Andrianakis
 */
public class CharacterCreationResult : SendablePacket
{
    public CharacterCreationResult(int result)
    {
        // Send the data.
        WriteShort(3); // Packet id.
        WriteByte(result);
    }
}
