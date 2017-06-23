package com.solitudecraft.solitudeessentials;

import com.solitudecraft.solitudecore.Core;
import com.solitudecraft.solitudecore.ErrorType;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by nolan on 6/23/2017.
 */
public class TeleportHereCommand implements CommandExecutor {
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;
        if (!(args.length == 1)) {
            Core.messageFramework.showErrorMessage(player, ErrorType.CommandFormat);
            return false;
        }

        if (player.getServer().getPlayer(args[0]) != null) {
            Player target = player.getServer().getPlayer(args[0]);

            target.teleport(player.getLocation());
            Core.messageFramework.sendUserMessage(player, "You have teleported " + target.getDisplayName().toString() + " to your location.");
            Core.messageFramework.sendUserMessage(target, "You have been teleported to " + player.getDisplayName().toString() + ".");
            return true;
        }
        return true;
    }
}
