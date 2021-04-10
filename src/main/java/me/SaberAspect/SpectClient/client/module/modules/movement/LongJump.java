package me.SaberAspect.SpectClient.client.module.modules.movement;

import org.lwjgl.input.Keyboard;

import me.SaberAspect.SpectClient.client.module.Category;
import me.SaberAspect.SpectClient.client.module.Module;

public class LongJump extends Module {
	
	public LongJump() {
		super ("longJump", "long, jump.", Keyboard.KEY_NONE, Category.MOVEMENT);
	}
}
