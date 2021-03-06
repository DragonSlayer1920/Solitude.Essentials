package com.solitudecraft.solitudeessentials.messages;

import com.solitudecraft.solitudecore.Core;
import com.solitudecraft.solitudecore.ErrorType;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * Created by nolan on 6/21/2017.
 */

public class MessageLookupCommand implements CommandExecutor {
    public static ArrayList<Message> previousMessages = new ArrayList<Message>();

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;
        if (!(args.length > 0) & (args.length <4)) {
            Core.messageFramework.showErrorMessage(player, ErrorType.CommandFormat);
            return false;
        }
        ArrayList<Message> results = new ArrayList<Message>();
        if(args.length == 1) {
            String target = args[0];
            OfflinePlayer offlinePlayer = Bukkit.getOfflinePlayer(target);
            if(offlinePlayer.hasPlayedBefore()) {
                for(Message message : previousMessages) {
                    if(message.UUID1.equals(offlinePlayer.getUniqueId().toString()) || message.UUID2.equals(offlinePlayer.getUniqueId().toString())) {
                        results.add(message);
                    }
                }
            }
            int maxPage;
            maxPage = (Math.round((results.size() - 1) / 5));
            sendMessageLookup(player, results, maxPage);
            return  true;
        }
        if(args.length == 2) {
            int Page = 0;
            if(isInteger(args[1]) == true) {
                String target = args[0];
                OfflinePlayer offlinePlayer = Bukkit.getOfflinePlayer(target);
                if(offlinePlayer.hasPlayedBefore()) {
                    for(Message message : previousMessages) {
                        if(message.UUID1.equals(offlinePlayer.getUniqueId().toString()) || message.UUID2.equals(offlinePlayer.getUniqueId().toString())) {
                            results.add(message);
                        }
                    }
                    Page = (Integer.parseInt(args[1]) - 1);
                }
            } else {
                String target = args[0];
                OfflinePlayer offlinePlayer = Bukkit.getOfflinePlayer(target);
                String target2 = args[1];
                OfflinePlayer offlinePlayer2 = Bukkit.getOfflinePlayer(target2);
                if(offlinePlayer.hasPlayedBefore() & offlinePlayer2.hasPlayedBefore()) {
                    for(Message message : previousMessages) {
                        if(message.UUID1.equals(offlinePlayer.getUniqueId().toString()) && message.UUID2.equals(offlinePlayer2.getUniqueId().toString())
                                || message.UUID1.equals(offlinePlayer2.getUniqueId().toString()) || message.UUID2.equals(offlinePlayer.getUniqueId().toString())) {
                            results.add(message);
                        }
                    }
                }
            }
            int maxPage;
            maxPage = (Math.round(results.size() / 5));
            if(Page > maxPage) {
                Core.messageFramework.showErrorMessage(player, ErrorType.PageNotFound);
                return false;
            }
            sendMessageLookup(player, results, Page);
        }
        if(args.length == 3) {
            int Page = 0;
            if (isInteger(args[2]) == true) {
                String target = args[0];
                OfflinePlayer offlinePlayer = Bukkit.getOfflinePlayer(target);
                String target2 = args[1];
                OfflinePlayer offlinePlayer2 = Bukkit.getOfflinePlayer(target2);
                if (offlinePlayer.hasPlayedBefore() & offlinePlayer2.hasPlayedBefore()) {
                    for (Message message : previousMessages) {
                        if (message.UUID1.equals(offlinePlayer.getUniqueId().toString()) && message.UUID2.equals(offlinePlayer2.getUniqueId().toString())
                                || message.UUID1.equals(offlinePlayer2.getUniqueId().toString()) || message.UUID2.equals(offlinePlayer.getUniqueId().toString())) {
                            results.add(message);
                        }
                    }
                    Page = (Integer.parseInt(args[2]) - 1);
                }
                sendMessageLookup(player, results, Page);
            }
        }
            return  true;
    }

    private boolean isInteger(String string) {
        try {
            Integer.parseInt(string);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    private void sendMessageLookup(Player player, ArrayList<Message> messageList, int Page) {
        int maxPage;
        maxPage = (Math.round((messageList.size() - 1) / 5));
        Core.messageFramework.sendUserMessage(player, "Showing search results... " + ChatColor.GRAY + "(" + (Page + 1) + "/" + (maxPage + 1) + ")");

        for (int i = (Page * 5); i < ((Page * 5) + 5); i++) {
            if(i < messageList.size()) {
                Message message = messageList.get(i);
                DateFormat dateFormat = new SimpleDateFormat("MM/dd/yy HH:mm");
                String formatDate;
                formatDate = dateFormat.format(message.messageDate);
                Core.messageFramework.sendUserMessage(player, Core.uuidFramework.getUsername(message.UUID1) + ChatColor.GREEN + " to " + ChatColor.AQUA + Core.uuidFramework.getUsername(message.UUID2) +
                        ChatColor.GREEN + ": " + message.messageContent + ChatColor.GRAY + "(" + formatDate + ")");
            }
        }
    }
}
