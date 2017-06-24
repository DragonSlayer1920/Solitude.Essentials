package com.solitudecraft.solitudeessentials.warps;

import com.solitudecraft.solitudecore.Core;
import com.solitudecraft.solitudecore.ErrorType;
import com.solitudecraft.solitudeessentials.SolitudeEssentials;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by nolan on 6/23/2017.
 */
public class WarpCommand implements CommandExecutor {
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;
        if (args.length > 1) {
            Core.messageFramework.showErrorMessage(player, ErrorType.CommandFormat);
            return false;
        }

        if(args.length == 0) {
            printWarpList(player);
            return true;
        }

        if(args.length == 1) {
            if(WarpDatabase.doesWarpExist(args[0])) {
                Warp warp = WarpDatabase.getWarp(args[0]);
                Location warpLocation = SolitudeEssentials.stringToLocation(warp.warpLocation);
                player.teleport(warpLocation);
                Core.messageFramework.sendUserMessage(player, "You have warped to " + ("" + warp.warpName.charAt(0)).toUpperCase() + warp.warpName.substring(1).toLowerCase() + ".");
            } else {
                printWarpList(player);
            }
        }

        return true;
    }

    private void printWarpList(Player player) {
        StringBuilder stringBuilder = new StringBuilder();

        ArrayList<String> warpNames = new ArrayList<>();

        for(Warp warp : WarpDatabase.warpDatabase) {
            warpNames.add(warp.warpName);
        }

        Collections.sort(warpNames);

        int i = 1;

        for(String string : warpNames) {
            if(i < warpNames.size()) {
                stringBuilder.append((ChatColor.GREEN + "" + string.charAt(0)) + string.substring(1).toLowerCase()).append(ChatColor.AQUA + ", ");
            } else {
                stringBuilder.append((ChatColor.GREEN + "" + string.charAt(0)) + string.substring(1).toLowerCase()).append(ChatColor.AQUA + " ");
            }
            i++;
        }

        Core.messageFramework.sendUserMessage(player, "Warps: " + stringBuilder.toString() + ChatColor.GRAY + "(" + warpNames.size() + ")");
    }
}


