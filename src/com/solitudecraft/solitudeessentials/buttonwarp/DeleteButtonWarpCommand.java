package com.solitudecraft.solitudeessentials.buttonwarp;

import com.solitudecraft.solitudecore.Core;
import com.solitudecraft.solitudecore.ErrorType;
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
/**
 * Created by nolan on 6/24/2017.
 */
public class DeleteButtonWarpCommand implements CommandExecutor, Listener {

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;
        if (!(args.length == 0)) {
            Core.messageFramework.showErrorMessage(player, ErrorType.CommandFormat);
            return false;
        }
        ButtonWarpDatabase.deletingButtonWarp.add(player.getUniqueId());
        Core.messageFramework.sendUserMessage(player, "Right click a stone button to remove Button Warp functionality.");
        return true;
    }

    @EventHandler
    public void onButtonClick(PlayerInteractEvent event) {
        if(event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            Player player = event.getPlayer();
            if(ButtonWarpDatabase.deletingButtonWarp.contains(player.getUniqueId())) {
                if(event.getClickedBlock().getType() == Material.STONE_BUTTON) {
                    Block button = event.getClickedBlock();
                    if(ButtonWarpDatabase.doesButtonWarpExist(button) == true) {
                        ButtonWarp buttonWarp = ButtonWarpDatabase.getButtonWarp(button);
                        ButtonWarpDatabase.buttonWarpDatabase.remove(buttonWarp);
                        Core.messageFramework.sendUserMessage(player, "Button warp removed.");
                        ButtonWarpDatabase.deletingButtonWarp.remove(player.getUniqueId());
                        return;
                    }
                    Core.messageFramework.sendUserMessage(player, "Clicked button is not linked to a warp.");
                    ButtonWarpDatabase.deletingButtonWarp.remove(player.getUniqueId());
                    return;
                }
            }
        }
    }
}