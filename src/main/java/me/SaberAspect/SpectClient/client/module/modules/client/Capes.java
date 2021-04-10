package me.SaberAspect.SpectClient.client.module.modules.client;

import org.lwjgl.input.Keyboard;

import me.SaberAspect.SpectClient.client.module.Category;
import me.SaberAspect.SpectClient.client.module.Module;

public class Capes extends Module {
	
	public Capes() {
		super("capes", "allows you to see your and others SpectClient capes.", Keyboard.KEY_NONE, Category.CLIENT);
	}

}
