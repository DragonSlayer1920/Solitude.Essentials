package com.solitudecraft.solitudeessentials.buttonwarp;

import com.solitudecraft.solitudeessentials.SolitudeEssentials;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

/**
 * Created by nolan on 6/24/2017.
 */
public class ButtonWarpUseEvent implements Listener {
    @EventHandler
    public void onButtonClick(PlayerInteractEvent event) {
        if(event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            Player player = event.getPlayer();
            if (event.getClickedBlock().getType() == Material.STONE_BUTTON) {
                Block block = event.getClickedBlock();
                if(ButtonWarpDatabase.doesButtonWarpExist(block) == true) {
                    if(!(ButtonWarpDatabase.creatingButtonWarp.containsKey(player)) & (!(ButtonWarpDatabase.deletingButtonWarp.contains(player.getUniqueId())))) {
                        ButtonWarp buttonWarp = ButtonWarpDatabase.getButtonWarp(block);
                        Location location = SolitudeEssentials.stringToLocation(buttonWarp.buttonWarpWarp.warpLocation);
                        player.teleport(location);
                    }
                }
            }
        }
    }
}
