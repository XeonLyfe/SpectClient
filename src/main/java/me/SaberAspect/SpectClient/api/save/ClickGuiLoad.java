package me.SaberAspect.SpectClient.api.save;

import java.io.IOException;

import me.SaberAspect.Main;
import me.SaberAspect.SpectClient.client.ui.clickgui.ClickGuiConfig;



public class ClickGuiLoad {

    public ClickGuiLoad() {
        try {
        	clickGuiLoad();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    String fileName = "SpectClient/";
    String mainName = "clickGui/";

    public void clickGuiLoad() throws IOException {
        loadClickGUIPositions();
    }

    public void loadClickGUIPositions() throws IOException {
		Main.clickGui.gui.loadConfig(new ClickGuiConfig(fileName+mainName));
    }
}