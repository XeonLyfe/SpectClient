package me.SaberAspect.SpectClient.api.save;

import java.io.IOException;

import me.SaberAspect.Main;

public class ConfigStopper extends Thread {

    @Override
    public void run() {
        saveConfig();
    }

    public static void saveConfig() {
        try {
            Main.clickGuiSave.clickGuiSave();
            Main.clickGuiSave.saveClickGUIPositions();
            Main.log.info("saved config.");
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}