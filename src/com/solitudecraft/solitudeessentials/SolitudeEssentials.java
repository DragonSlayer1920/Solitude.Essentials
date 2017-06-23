package com.solitudecraft.solitudeessentials;

import com.solitudecraft.solitudecore.Core;
import com.solitudecraft.solitudeessentials.messages.*;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Created by nolan on 6/19/2017.
 */
public class SolitudeEssentials extends JavaPlugin
{
    public static MessageMySQL messageMySQL;
    public void onEnable() {
        messageMySQL = new MessageMySQL("sql3.freemysqlhosting.net", "sql3181335", "67vwPsEQUH", "sql3181335");
        messageMySQL.populateMessageDatabase();
        getCommand("broadcast").setExecutor(new BroadcastCommand());
        getCommand("feed").setExecutor(new FeedCommand());
        getCommand("fly").setExecutor(new FlyCommand());
        getCommand("gamemode").setExecutor(new GamemodeCommand());
        getCommand("time").setExecutor(new TimeCommand());
        getCommand("heal").setExecutor(new HealCommand());
        getCommand("message").setExecutor(new MessageCommand());
        getCommand("viewmessage").setExecutor(new MessageViewCommand());
        getCommand("lookupmessage").setExecutor(new MessageLookupCommand());
        Bukkit.getPluginManager().registerEvents(new MessageViewJoinEvent(), this);
    }
}
