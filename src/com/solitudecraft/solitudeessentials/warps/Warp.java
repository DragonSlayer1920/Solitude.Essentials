package com.solitudecraft.solitudeessentials.warps;

import java.io.*;
import java.util.ArrayList;

/**
 * Created by nolan on 6/23/2017.
 */

public class Warp implements Serializable {
    private static final long serialVersionUID = 3401705964094619778L;
    public String warpName;
    public String warpLocation;

    public Warp(String name, String location) {
        warpName = name;
        warpLocation = location;
    }

    public static void saveWarps() {
        try {
            FileOutputStream file = new FileOutputStream("warps.bin");
            ObjectOutputStream oos = new ObjectOutputStream(file);
            oos.writeObject(WarpDatabase.warpDatabase);
            file.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void loadWarps() {
        try {
            FileInputStream fileInputStream = new FileInputStream("warps.bin");
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            WarpDatabase.warpDatabase = (ArrayList<Warp>)objectInputStream.readObject();
            fileInputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

