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
public class BroadcastCommand implements CommandExecutor
{
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        //if(label.equalsIgnoreCase("broadcast")) {
            Player player = (Player) sender;
            if (args.length == 0) {
                Core.messageFramework.showErrorMessage(player, ErrorType.CommandFormat);
                return true;
            }

            String message = "";
            for(String string : args) {
               message += (string + " ");
            }

            Core.messageFramework.broadcastMessage(message);
       // }
        return false;
    }
}
