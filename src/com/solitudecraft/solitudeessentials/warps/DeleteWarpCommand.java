package com.solitudecraft.solitudeessentials.warps;

import com.solitudecraft.solitudecore.Core;
import com.solitudecraft.solitudecore.ErrorType;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by nolan on 6/23/2017.
 */
public class DeleteWarpCommand implements CommandExecutor {
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;
        if (!(args.length == 1)) {
            Core.messageFramework.showErrorMessage(player, ErrorType.CommandFormat);
            return false;
        }

        String warpName = args[0];

        if(WarpDatabase.doesWarpExist(warpName)) {
            Warp warp = WarpDatabase.getWarp(warpName);
            Core.messageFramework.sendUserMessage(player, "You have deleted Warp " + ("" + warpName.charAt(0)).toUpperCase() + warpName.substring(1).toLowerCase() + ".");
            WarpDatabase.warpDatabase.remove(warp);
            return true;
        } else {
            Core.messageFramework.showErrorMessage(player, ErrorType.InvalidWarp);
            return false;
        }
    }
}
