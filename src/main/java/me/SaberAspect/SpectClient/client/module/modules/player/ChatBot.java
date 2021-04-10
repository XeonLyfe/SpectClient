package me.SaberAspect.SpectClient.client.module.modules.player;

import org.lwjgl.input.Keyboard;

import me.SaberAspect.SpectClient.client.module.Category;
import me.SaberAspect.SpectClient.client.module.Module;

public class ChatBot extends Module {
	
	public ChatBot() {
		super ("chatBot", "bot chat.", Keyboard.KEY_NONE, Category.PLAYER);
	}
	

}
