package com.solitudecraft.solitudeessentials.buttonwarp;

import com.solitudecraft.solitudeessentials.warps.Warp;

import java.io.*;
import java.util.ArrayList;

/**
 * Created by nolan on 6/23/2017.
 */

public class ButtonWarp implements Serializable {
    public String buttonWarpButton;
    public Warp buttonWarpWarp;

    public ButtonWarp(String buttonLocation, Warp warp) {
        buttonWarpButton = buttonLocation;
        buttonWarpWarp = warp;

    }

    public static void saveButtonWarps() {
        try {
            FileOutputStream file = new FileOutputStream("buttonwarps.bin");
            ObjectOutputStream oos = new ObjectOutputStream(file);
            oos.writeObject(ButtonWarpDatabase.buttonWarpDatabase);
            file.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void loadButtonWarps() {
        try {
            FileInputStream fileInputStream = new FileInputStream("buttonwarps.bin");
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            ButtonWarpDatabase.buttonWarpDatabase = (ArrayList<ButtonWarp>)objectInputStream.readObject();
            fileInputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
