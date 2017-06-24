package com.solitudecraft.solitudeessentials.buttonwarp;

import com.solitudecraft.solitudeessentials.SolitudeEssentials;
import com.solitudecraft.solitudeessentials.warps.Warp;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

/**
 * Created by nolan on 6/24/2017.
 */
public class ButtonWarpDatabase {
    public static ArrayList<ButtonWarp> buttonWarpDatabase = new ArrayList<ButtonWarp>();
    public static HashMap<Player, Warp> creatingButtonWarp = new HashMap<Player, Warp>();
    public static ArrayList<UUID> deletingButtonWarp = new ArrayList<>();

    public static boolean doesButtonWarpExist(Block block) {
        Location blockLocation  = block.getLocation();
        for(ButtonWarp buttonWarp : buttonWarpDatabase) {
            Location buttonWarpLocation = SolitudeEssentials.stringToLocation(buttonWarp.buttonWarpButton);
            if(blockLocation.equals(buttonWarpLocation)) {
                return true;
            }
        }
        return false;
    }

    public static ButtonWarp getButtonWarp(Block block) {
        for(ButtonWarp buttonWarp : buttonWarpDatabase) {
            Location blockLocation = block.getLocation();
            Location buttonWarpLocation = SolitudeEssentials.stringToLocation(buttonWarp.buttonWarpButton);
            if(blockLocation.equals(buttonWarpLocation)) {
                return  buttonWarp;
            }
        }
        return null;
    }
}
