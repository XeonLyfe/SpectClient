package me.SaberAspect.SpectClient.client.module.modules.player;

import org.lwjgl.input.Keyboard;

import me.SaberAspect.SpectClient.client.module.Category;
import me.SaberAspect.SpectClient.client.module.Module;

public class ElytraReplace extends Module {
	
	public ElytraReplace() {
		super ("elytraReplace", "automatically replaces a broken elytra.", Keyboard.KEY_NONE, Category.PLAYER);
	}

}
