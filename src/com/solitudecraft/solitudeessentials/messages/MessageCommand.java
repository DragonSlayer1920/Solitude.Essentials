package com.solitudecraft.solitudeessentials.messages;

import com.solitudecraft.solitudecore.Core;
import com.solitudecraft.solitudecore.ErrorType;
import com.solitudecraft.solitudeessentials.SolitudeEssentials;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Date;

/**
 * Created by nolan on 6/21/2017.
 */

public class MessageCommand implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;
        if (!(args.length >= 2)) {
            Core.messageFramework.showErrorMessage(player, ErrorType.CommandFormat);
            return false;
        }
        if (player.getServer().getPlayer(args[0]) != null) {
            Player target = player.getServer().getPlayer((args[0]));
            String message;
            StringBuilder stringBuilder = new StringBuilder();
            for(int i=1; i<args.length; i++){ // change 1
                stringBuilder.append(args[i]).append(" ");
            }
            message = stringBuilder.toString();
            Core.messageFramework.sendPrivateMessage(player,target,message);
            Date date = new Date();
            String senderUUID = Core.uuidFramework.getUUID(player).toString();
            String receiverUUID = Core.uuidFramework.getUUID(target).toString();
            Message msg = new Message(senderUUID, receiverUUID, message, date);
            SolitudeEssentials.messageMySQL.saveMessage(msg);
            for(Player moderator : MessageViewCommand.viewingMessage) {
                Core.messageFramework.sendMessageSpy(moderator, player, target, message);
            }
            MessageLookupCommand.previousMessages.add(msg);

        } else {
            Core.messageFramework.showErrorMessage(player, ErrorType.PlayerNotFound);
            return false;
        }
        return false;
    }
}