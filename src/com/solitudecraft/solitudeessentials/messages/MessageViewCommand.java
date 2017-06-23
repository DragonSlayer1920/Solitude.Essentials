package com.solitudecraft.solitudeessentials.messages;

import com.solitudecraft.solitudecore.Core;
import com.solitudecraft.solitudecore.ErrorType;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nolan on 6/21/2017.
 */
public class MessageViewCommand implements CommandExecutor {
    public static ArrayList<Player> viewingMessage = new ArrayList<Player>();
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;
        if (!(args.length == 0)) {
            Core.messageFramework.showErrorMessage(player, ErrorType.CommandFormat);
            return false;
        }
        if(viewingMessage.contains(player)) {
            viewingMessage.remove(player);
            Core.messageFramework.sendUserMessage(player, "You will no longer see all private messages.");
        } else {
            viewingMessage.add(player);
            Core.messageFramework.sendUserMessage(player, "You will see all private messages.");
        }
        return false;
    }
}
