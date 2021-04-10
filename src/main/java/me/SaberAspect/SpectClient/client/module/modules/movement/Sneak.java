package me.SaberAspect.SpectClient.client.module.modules.movement;

import org.lwjgl.input.Keyboard;

import me.SaberAspect.SpectClient.client.module.Category;
import me.SaberAspect.SpectClient.client.module.Module;

public class Sneak extends Module {
	
	public Sneak() {
		super ("sneak", "sneak... doesn't work rn.", Keyboard.KEY_NONE, Category.MOVEMENT);
	}
}
