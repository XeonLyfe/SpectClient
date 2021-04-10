package me.SaberAspect.SpectClient.client.module.modules.bot;

import org.lwjgl.input.Keyboard;

import me.SaberAspect.SpectClient.client.module.Category;
import me.SaberAspect.SpectClient.client.module.Module;

public class OffHandBot extends Module {
	
	public OffHandBot() {
		super("offHandBot", "A bot that manages your off hand.", Keyboard.KEY_NONE, Category.BOT);
	}

}
