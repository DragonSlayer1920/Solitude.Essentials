package com.solitudecraft.solitudeessentials.warps;

import java.util.ArrayList;

/**
 * Created by nolan on 6/23/2017.
 */
public class WarpDatabase {
    public static ArrayList<Warp> warpDatabase = new ArrayList<Warp>();

    public static boolean doesWarpExist(String warpName) {
        for(Warp warp : warpDatabase) {
            if(warp.warpName.toUpperCase().equals(warpName.toUpperCase())) {
                return true;
            }
        }
        return  false;
    }

    public static Warp getWarp(String warpName) {
        for(Warp warp : warpDatabase) {
            if(warp.warpName.toUpperCase().equals(warpName.toUpperCase())) {
                return warp;
            }
        }
        return null;
    }
}
