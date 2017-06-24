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
 * Created by nolan on 6/24/2017.
 */

public class WeatherCommand implements CommandExecutor {
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;
        if (!(args.length == 0)) {
            Core.messageFramework.showErrorMessage(player, ErrorType.CommandFormat);
            return false;
        }

        for(World world : Bukkit.getServer().getWorlds()) {
            world.setStorm(false);
            world.setThundering(false);
        }
        Core.messageFramework.sendUserMessage(player, "You have cleared the weather.");

        return false;
    }
}
