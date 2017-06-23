package com.solitudecraft.solitudeessentials;

import com.solitudecraft.solitudecore.Core;
import com.solitudecraft.solitudecore.ErrorType;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by nolan on 6/19/2017.
 */
public class FlyCommand implements CommandExecutor {
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;
        if (args.length > 0) {
            Core.messageFramework.showErrorMessage(player, ErrorType.CommandFormat);
            return false;
        }

        if(args.length == 0) {
            if(player.getAllowFlight() == true) {
                player.setAllowFlight(false);
                Core.messageFramework.sendUserMessage(player, "Flight has been disabled.");
                return true;
            } else {
                player.setAllowFlight(true);
                Core.messageFramework.sendUserMessage(player, "Flight has been enabled.");
                return true;
            }
        }
        return false;
    }
}
