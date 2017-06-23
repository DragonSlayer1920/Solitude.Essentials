package com.solitudecraft.solitudeessentials;

import com.solitudecraft.solitudecore.Core;
import com.solitudecraft.solitudecore.ErrorType;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by nolan on 6/21/2017.
 */
public class HealCommand implements CommandExecutor {
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        //if(label.equalsIgnoreCase("broadcast")) {
        Player player = (Player) sender;
        if (args.length > 1) {
            Core.messageFramework.showErrorMessage(player, ErrorType.CommandFormat);
            return false;
        }

        if(args.length == 0) {
            player.setHealth(20);
            player.setFireTicks(0);
            Core.messageFramework.sendUserMessage(player, "You have been healed.");
            return true;
        }

        if(args.length == 1) {
            if (player.getServer().getPlayer(args[0]) != null) {
                Player target = player.getServer().getPlayer((args[0]));
                target.setHealth(20);
                target.setFireTicks(0);
                Core.messageFramework.sendUserMessage(player, "You have healed " + target.getDisplayName() + ".");
                Core.messageFramework.sendUserMessage(target, "You have been healed.");
            } else {
                Core.messageFramework.showErrorMessage(player, ErrorType.PlayerNotFound);
                return  false;
            }
        }
        return false;
    }
}
