package com.solitudecraft.solitudeessentials.warps;

import com.solitudecraft.solitudecore.Core;
import com.solitudecraft.solitudecore.ErrorType;
import com.solitudecraft.solitudeessentials.SolitudeEssentials;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by nolan on 6/23/2017.
 */
public class SetWarpCommand implements CommandExecutor {
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;
        if (!(args.length == 1)) {
            Core.messageFramework.showErrorMessage(player, ErrorType.CommandFormat);
            return false;
        }

        String warpName = args[0];

        Warp warp = new Warp(args[0].toUpperCase(), SolitudeEssentials.locationToString(player.getLocation()));
        WarpDatabase.warpDatabase.add(warp);
        Core.messageFramework.sendUserMessage(player, "You have created Warp " + ("" + warpName.charAt(0)).toUpperCase() + warpName.substring(1).toLowerCase() + ".");
        return true;
    }
}