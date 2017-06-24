package com.solitudecraft.solitudeessentials.messages;

import com.solitudecraft.solitudeessentials.SolitudeEssentials;
import com.solitudecraft.solitudeessentials.buttonwarp.ButtonWarp;
import com.solitudecraft.solitudeessentials.buttonwarp.ButtonWarpDatabase;
import com.solitudecraft.solitudeessentials.warps.Warp;
import com.solitudecraft.solitudeessentials.warps.WarpDatabase;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.material.Button;

/**
 * Created by nolan on 6/22/2017.
 */
public class MessageViewJoinEvent implements Listener{
    @EventHandler
    public void onAdminJoin(PlayerJoinEvent event) {
          MessageViewCommand.viewingMessage.add(event.getPlayer());
    }
}
