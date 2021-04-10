package me.SaberAspect.SpectClient.client.module.modules.render;

import org.lwjgl.input.Keyboard;

import me.SaberAspect.SpectClient.client.module.Category;
import me.SaberAspect.SpectClient.client.module.Module;

public class Peek extends Module {
	
	//look in me.srgantmoomoo.api.mixin.mixins.MixinGuiScreen
	public Peek() {
		super ("peek", "shows preview of wuts in a shulker.", Keyboard.KEY_NONE, Category.RENDER);
	}

}
