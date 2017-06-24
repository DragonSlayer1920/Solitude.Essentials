package com.solitudecraft.solitudeessentials;

import com.solitudecraft.solitudeessentials.buttonwarp.*;
import com.solitudecraft.solitudeessentials.messages.*;
import com.solitudecraft.solitudeessentials.warps.*;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Created by nolan on 6/19/2017.
 */

public class SolitudeEssentials extends JavaPlugin
{
    public static SolitudeEssentials solitudeEssentials;
    public static MessageMySQL messageMySQL;
    public void onEnable() {
        solitudeEssentials = this;
        messageMySQL = new MessageMySQL("sql3.freemysqlhosting.net", "sql3181335", "67vwPsEQUH", "sql3181335");
        messageMySQL.populateMessageDatabase();
        Warp.loadWarps();
        ButtonWarp.loadButtonWarps();
        getCommand("broadcast").setExecutor(new BroadcastCommand());
        getCommand("feed").setExecutor(new FeedCommand());
        getCommand("fly").setExecutor(new FlyCommand());
        getCommand("gamemode").setExecutor(new GamemodeCommand());
        getCommand("time").setExecutor(new TimeCommand());
        getCommand("heal").setExecutor(new HealCommand());
        getCommand("message").setExecutor(new MessageCommand());
        getCommand("viewmessage").setExecutor(new MessageViewCommand());
        getCommand("lookupmessage").setExecutor(new MessageLookupCommand());
        getCommand("teleport").setExecutor(new TeleportCommand());
        getCommand("tp").setExecutor(new TeleportCommand());
        getCommand("teleporthere").setExecutor(new TeleportHereCommand());
        getCommand("help").setExecutor(new HelpCommand());
        getCommand("setwarp").setExecutor(new SetWarpCommand());
        getCommand("deletewarp").setExecutor(new DeleteWarpCommand());
        getCommand("warp").setExecutor(new WarpCommand());
        getCommand("createbuttonwarp").setExecutor(new CreateButtonWarpCommand());
        getCommand("deletebuttonwarp").setExecutor(new DeleteButtonWarpCommand());
        getCommand("weather").setExecutor(new WeatherCommand());
        Bukkit.getPluginManager().registerEvents(new MessageViewJoinEvent(), this);
        Bukkit.getPluginManager().registerEvents(new CreateButtonWarpCommand(), this);
        Bukkit.getPluginManager().registerEvents(new DeleteButtonWarpCommand(), this);
        Bukkit.getPluginManager().registerEvents(new ButtonWarpUseEvent(),this);
        Bukkit.broadcastMessage(ButtonWarpDatabase.buttonWarpDatabase.size() + "");
    }

    public void onDisable() {
        Warp.saveWarps();
        ButtonWarp.saveButtonWarps();
    }
    public static String locationToString(Location l){
        String world = l.getWorld().getName(); //get the world name
        Double x = l.getX(); //get the x coordinate
        Double y = l.getY(); //get the y coordinate
        Double z = l.getZ(); //get the z coordinate
        Float pitch = l.getPitch();
        Float yaw = l.getYaw();

        return world + "," + x + "," + y + "," + z + "," + pitch + "," + yaw;
    }
    public static Location stringToLocation(String s){
        String[] str = s.split(","); //split s by ','
        World world = Bukkit.getWorld(str[0]); //get the world
        Double x = Double.parseDouble(str[1]); //get the x coord
        Double y = Double.parseDouble(str[2]); //get the y coord
        Double z = Double.parseDouble(str[3]); //get the z coord
        Float pitch = Float.parseFloat(str[4]);
        Float yaw = Float.parseFloat(str[5]);
        Location location = new Location(world, x, y, z);
        location.setPitch(pitch);
        location.setYaw(yaw );

        return location; //return a location created from the String
    }
}
