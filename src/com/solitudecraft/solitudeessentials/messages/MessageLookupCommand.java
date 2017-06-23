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

        if(args.length == 1) {
            String target = args[0];
            OfflinePlayer offlinePlayer = Bukkit.getOfflinePlayer(target);
            ArrayList<Message> results = new ArrayList<Message>();
            if(offlinePlayer.hasPlayedBefore()) {
                for(Message message : previousMessages) {
                    if(message.UUID1.equals(offlinePlayer.getUniqueId().toString())) {
                        results.add(message);
                    }
                }
            }
            int maxPage;
            maxPage = (Math.round(results.size() / 5));
            sendMessageLookup(player, results, maxPage);
            return  true;
        }

        if(args.length == 2) {
            if(isInteger(args[1]) == true) {
                String target = args[0];
                OfflinePlayer offlinePlayer = Bukkit.getOfflinePlayer(target);
                ArrayList<Message> results = new ArrayList<Message>();
                if(offlinePlayer.hasPlayedBefore()) {
                    for(Message message : previousMessages) {
                        if(message.UUID1.equals(offlinePlayer.getUniqueId().toString())) {
                            results.add(message);
                        }
                    }
                }
                int Page = (Integer.parseInt(args[1]) - 1);
                int maxPage;
                maxPage = (Math.round(results.size() / 5));
                if(Page > maxPage) {
                    Core.messageFramework.showErrorMessage(player, ErrorType.PageNotFound);
                    return false;
                }
                sendMessageLookup(player, results, Page);
                return  true;
            }
        }
        return false;
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

        int test;
        test = (maxPage - (Page));


       /* for(int i = ((test * 5) + 9); i >= ((test * 5) + 5); i--) {
            if(i < messageList.size()) {
                Message message = messageList.get(i);
                DateFormat dateFormat = new SimpleDateFormat("MM/dd/yy HH:mm");
                String formatDate;
                formatDate = dateFormat.format(message.messageDate);
                Core.messageFramework.sendUserMessage(player, Core.uuidFramework.getUsername(message.UUID1) +
                        " to " + Core.uuidFramework.getUsername(message.UUID2) + ": " + message.messageContent + "" + ChatColor.GRAY + " (" +
                        formatDate + ")");
            }
        }*/

        /*for (int i = ((Page * 5) + 5); i >= (Page * 5); i--) {
            if(i < messageList.size()) {
                Message message = messageList.get(i);
                DateFormat dateFormat = new SimpleDateFormat("MM/dd/yy HH:mm");
                String formatDate;
                formatDate = dateFormat.format(message.messageDate);
                Core.messageFramework.sendUserMessage(player, Core.uuidFramework.getUsername(message.UUID1) +
                        " to " + Core.uuidFramework.getUsername(message.UUID2) + ": " + message.messageContent + "" + ChatColor.GRAY + " (" +
                        formatDate + ")");
            }
        }*/

        for (int i = (Page * 5); i < ((Page * 5) + 5); i++) {
            if(i < messageList.size()) {
                Message message = messageList.get(i);
                DateFormat dateFormat = new SimpleDateFormat("MM/dd/yy HH:mm");
                String formatDate;
                formatDate = dateFormat.format(message.messageDate);
                Core.messageFramework.sendUserMessage(player, Core.uuidFramework.getUsername(message.UUID1) +
                        " to " + Core.uuidFramework.getUsername(message.UUID2) + ": " + message.messageContent + "" + ChatColor.GRAY + " (" +
                        formatDate + ")");
            }
        }
    }

    public static void Test() {
        Bukkit.broadcastMessage("" + previousMessages.size());
    }
}
