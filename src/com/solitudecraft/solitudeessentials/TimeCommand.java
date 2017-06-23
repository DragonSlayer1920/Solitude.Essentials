package com.solitudecraft.solitudeessentials;

import com.solitudecraft.solitudecore.Core;
import com.solitudecraft.solitudecore.ErrorType;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by nolan on 6/21/2017.
 */

public class TimeCommand implements CommandExecutor {
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;
        if (!(args.length == 1)) {
            Core.messageFramework.showErrorMessage(player, ErrorType.CommandFormat);
            return false;
        }

        if(args.length == 1) {
            Long time;
            String timeString = "";
            switch (args[0].toString().toUpperCase()) {
                case "DAY": time = 0L;
                    timeString = "Day";
                    break;
                case "NIGHT": time = 18000L;
                    timeString = "Night";
                    break;
                default: time = 0L;
                    timeString = "Day";
                    break;
            }

            for (World world : Bukkit.getServer().getWorlds()){
                world.setTime(time);
            }

            Core.messageFramework.sendUserMessage(player, "You have updated the time to " + timeString + ".");
        }

        return false;
    }
}