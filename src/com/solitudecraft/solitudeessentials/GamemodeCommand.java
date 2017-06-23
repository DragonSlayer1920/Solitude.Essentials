package com.solitudecraft.solitudeessentials;

import com.solitudecraft.solitudecore.Core;
import com.solitudecraft.solitudecore.ErrorType;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by nolan on 6/19/2017.
 */
public class GamemodeCommand implements CommandExecutor {
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;
        if (!(args.length > 0) & !(args.length < 3)) {
            Core.messageFramework.showErrorMessage(player, ErrorType.CommandFormat);
            return false;
        }

        if(args.length == 1) {
            GameMode gameMode;
            switch (args[0].toString().toUpperCase()) {
                case "0": gameMode = GameMode.SURVIVAL;
                    break;
                case "1": gameMode = GameMode.CREATIVE;
                    break;
                case "2": gameMode = GameMode.ADVENTURE;
                    break;
                case "3": gameMode = GameMode.SURVIVAL;
                    break;
                case "S": gameMode = GameMode.SURVIVAL;
                    break;
                case "SURVIVAL": gameMode = GameMode.SURVIVAL;
                    break;
                case "C": gameMode = GameMode.CREATIVE;
                    break;
                case "CREATIVE": gameMode = GameMode.CREATIVE;
                    break;
                case "A": gameMode = GameMode.ADVENTURE;
                    break;
                case "ADVENTURE": gameMode = GameMode.ADVENTURE;
                    break;
                case "SPEC": gameMode = GameMode.SPECTATOR;
                    break;
                case "SPECTATOR": gameMode = GameMode.SPECTATOR;
                    break;
                default: gameMode = GameMode.SURVIVAL;
                    break;
            }
            player.setGameMode(gameMode);
            Core.messageFramework.sendUserMessage(player, "Your gamemode has been updated to " + ("" + gameMode.name().charAt(0)).toUpperCase() + gameMode.name().substring(1).toLowerCase() + ".");
        }

        if(args.length == 2) {
            if (player.getServer().getPlayer(args[0]) != null) {
                Player target = player.getServer().getPlayer((args[0]));

                GameMode gameMode;
                switch (args[0].toString().toUpperCase()) {
                    case "0": gameMode = GameMode.SURVIVAL;
                        break;
                    case "1": gameMode = GameMode.CREATIVE;
                        break;
                    case "2": gameMode = GameMode.ADVENTURE;
                        break;
                    case "3": gameMode = GameMode.SURVIVAL;
                        break;
                    case "S": gameMode = GameMode.SURVIVAL;
                        break;
                    case "SURVIVAL": gameMode = GameMode.SURVIVAL;
                        break;
                    case "C": gameMode = GameMode.CREATIVE;
                        break;
                    case "CREATIVE": gameMode = GameMode.CREATIVE;
                        break;
                    case "A": gameMode = GameMode.ADVENTURE;
                        break;
                    case "ADVENTURE": gameMode = GameMode.ADVENTURE;
                        break;
                    case "SPEC": gameMode = GameMode.SPECTATOR;
                        break;
                    case "SPECTATOR": gameMode = GameMode.SPECTATOR;
                        break;
                    default: gameMode = GameMode.SURVIVAL;
                        break;
                }
                target.setGameMode(gameMode);
                Core.messageFramework.sendUserMessage(player, "You have set " + target.getDisplayName() + "'s gamemode to "  + ("" + gameMode.name().charAt(0)).toUpperCase() + gameMode.name().substring(1).toLowerCase() + ".");
                Core.messageFramework.sendUserMessage(target, "Your gamemode has been updated to " + ("" + gameMode.name().charAt(0)).toUpperCase() + gameMode.name().substring(1).toLowerCase() + ".");
            } else {
                Core.messageFramework.showErrorMessage(player, ErrorType.PlayerNotFound);
                return  false;
            }
            return false;
        }
        return false;
    }
}
