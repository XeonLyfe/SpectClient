package me.SaberAspect.SpectClient.client.module.modules.render;

import org.lwjgl.input.Keyboard;

import me.SaberAspect.SpectClient.client.module.Category;
import me.SaberAspect.SpectClient.client.module.Module;

public class Xray extends Module {
	
	public Xray() {
		super ("xray", "use commands for better customizability.", Keyboard.KEY_NONE, Category.RENDER);
	}

}