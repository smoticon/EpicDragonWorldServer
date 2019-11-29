/**
 * Author: Pantelis Andrianakis
 * Date: November 29th 2019
 */
public class ReturnCommand
{
    public static void Handle(Player player)
    {
        player.SetTeleporting();
        player.SetLocation(Config.STARTING_LOCATION);
        player.ChannelSend(new LocationUpdate(player, true));
    }
}
