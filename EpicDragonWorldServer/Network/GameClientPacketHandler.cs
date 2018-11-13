/**
 * Author: Pantelis Andrianakis
 * Date: November 7th 2018
 */
class GameClientPacketHandler
{
    public static void Handle(GameClient client, ReceivablePacket packet)
    {
        switch (packet.ReadShort()) // Packet id.
        {
            case 1:
                new AccountAuthenticationRequest(client, packet);
                break;

            case 2:
                new CharacterSelectionInfoRequest(client, packet);
                break;

            case 3:
                new CharacterCreationRequest(client, packet);
                break;

            case 4:
                new CharacterDeletionRequest(client, packet);
                break;

            case 5:
                new CharacterSlotUpdate(client, packet);
                break;

            case 6:
                new CharacterSelectUpdate(client, packet);
                break;

            case 7:
                new EnterWorldRequest(client, packet);
                break;

            case 8:
                new LocationUpdate(client, packet);
                break;

            case 9:
                new ObjectInfoRequest(client, packet);
                break;

            case 10:
                new ChatRequest(client, packet);
                break;
        }
    }
}
