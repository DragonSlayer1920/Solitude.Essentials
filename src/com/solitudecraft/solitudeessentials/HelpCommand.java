package com.solitudecraft.solitudeessentials;

import com.solitudecraft.solitudecore.Core;
import com.solitudecraft.solitudecore.HelpMenu;
import com.solitudecraft.solitudecore.HelpMenuItem;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;

/**
 * Created by nolan on 6/23/2017.
 */


/**
 * /Help
 *   /Help Commands (Commands)
 *   /Help Guard (Guard Info & App Link)
 *   /Help
 *
 */

public class HelpCommand implements CommandExecutor {
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;
        if (!(args.length == 1)) {
            ArrayList<HelpMenuItem> helpMenuItems = new ArrayList<HelpMenuItem>();
                helpMenuItems.add(new HelpMenuItem("Help", "Commands", "Shows commands available to your rank."));
                helpMenuItems.add(new HelpMenuItem("Help", "Guard", "Shows guard information."));
            HelpMenu helpMenu = new HelpMenu("Solitude Craft Help", helpMenuItems);
            Core.messageFramework.showHelpMenu(player, helpMenu);
            return  true;
        }
        return true;
    }
}
