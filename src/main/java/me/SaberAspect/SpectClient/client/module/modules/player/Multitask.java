package me.SaberAspect.SpectClient.client.module.modules.player;

import org.lwjgl.input.Keyboard;

import me.SaberAspect.SpectClient.client.module.Category;
import me.SaberAspect.SpectClient.client.module.Module;

public class Multitask extends Module {
	
	public Multitask() {
		super("multitask", "allows you to do different things in each hand.", Keyboard.KEY_NONE, Category.PLAYER);
	}

}
