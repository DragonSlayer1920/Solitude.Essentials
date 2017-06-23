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

public class TeleportCommand implements CommandExecutor {
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;
        if (!(args.length == 1)) {
            Core.messageFramework.showErrorMessage(player, ErrorType.CommandFormat);
            return false;
        }

        if (player.getServer().getPlayer(args[0]) != null) {
            Player target = player.getServer().getPlayer(args[0]);

            player.teleport(target.getLocation());
            Core.messageFramework.sendUserMessage(player, "You have teleported to " + target.getDisplayName().toString() + ".");
            return true;
        }
        return true;
    }
}
