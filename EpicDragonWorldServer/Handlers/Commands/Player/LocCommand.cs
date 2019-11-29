/**
 * Author: Pantelis Andrianakis
 * Date: November 29th 2019
 */
public class LocCommand
{
    public static void Handle(Player player)
    {
        LocationHolder location = player.GetLocation();

        // Send player success message.
        ChatManager.SendSystemMessage(player, "Your location is " + location.GetX() + " " + location.GetZ() + " " + location.GetY());
    }
}
