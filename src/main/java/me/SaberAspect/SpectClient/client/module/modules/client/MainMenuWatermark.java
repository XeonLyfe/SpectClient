package me.SaberAspect.SpectClient.client.module.modules.client;

import org.lwjgl.input.Keyboard;

import me.SaberAspect.SpectClient.client.module.Category;
import me.SaberAspect.SpectClient.client.module.Module;

public class MainMenuWatermark extends Module {
	
	public MainMenuWatermark() {
		super("mainMenuWatermark", "shows SpectClient on minecrafts main menu screen.", Keyboard.KEY_NONE, Category.CLIENT);
		toggled = true;
	}
	//check MixinGuiMainMenu :)

}
