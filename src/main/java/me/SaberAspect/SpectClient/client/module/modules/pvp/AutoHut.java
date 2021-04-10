package me.SaberAspect.SpectClient.client.module.modules.pvp;

import org.lwjgl.input.Keyboard;

import me.SaberAspect.SpectClient.client.module.Category;
import me.SaberAspect.SpectClient.client.module.Module;

public class AutoHut extends Module {
	
	public AutoHut() {
		super ("autoHut", "automatically builds hut for u.", Keyboard.KEY_NONE, Category.PVP);
	}

}
