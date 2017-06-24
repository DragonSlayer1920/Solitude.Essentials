package com.solitudecraft.solitudeessentials.buttonwarp;

import com.solitudecraft.solitudecore.Core;
import com.solitudecraft.solitudecore.ErrorType;
import com.solitudecraft.solitudeessentials.SolitudeEssentials;
import com.solitudecraft.solitudeessentials.warps.Warp;
import com.solitudecraft.solitudeessentials.warps.WarpDatabase;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.HashMap;

/**
 * Created by nolan on 6/24/2017.
 */
public class CreateButtonWarpCommand implements CommandExecutor, Listener {
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;
        if (!(args.length == 1)) {
            Core.messageFramework.showErrorMessage(player, ErrorType.CommandFormat);
            return false;
        }
        if(WarpDatabase.doesWarpExist(args[0]) == true) {
            Warp warp = WarpDatabase.getWarp(args[0]);
            ButtonWarpDatabase.creatingButtonWarp.put(player, warp);
            Core.messageFramework.sendUserMessage(player, "Right click a stone button to warp to " + (warp.warpName.charAt(0) + warp.warpName.substring(1).toLowerCase()) + ".");
        } else {
            Core.messageFramework.showErrorMessage(player, ErrorType.InvalidWarp);
            return false;
        }
        return true;
    }

    @EventHandler
    public void onButtonClick(PlayerInteractEvent event) {
        if(event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            Player player = event.getPlayer();
            if(ButtonWarpDatabase.creatingButtonWarp.containsKey(player)) {
                if(event.getClickedBlock().getType() == Material.STONE_BUTTON) {
                    Block button = event.getClickedBlock();
                    Warp warp = ButtonWarpDatabase.creatingButtonWarp.get(player);
                    ButtonWarp buttonWarp = new ButtonWarp(SolitudeEssentials.locationToString(button.getLocation()), warp);
                    if(ButtonWarpDatabase.doesButtonWarpExist(button) == true) {
                        Core.messageFramework.showErrorMessage(player, ErrorType.ButtonWarpExists);
                        ButtonWarpDatabase.creatingButtonWarp.remove(player);
                        return;
                    }
                    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(SolitudeEssentials.solitudeEssentials, new Runnable() {
                        public void run() {
                            ButtonWarpDatabase.buttonWarpDatabase.add(buttonWarp);
                        }
                    }, 10L);
                    ButtonWarpDatabase.creatingButtonWarp.remove(player);
                    Core.messageFramework.sendUserMessage(player, "Button warp created.");
                }
            }
        }
    }
}
