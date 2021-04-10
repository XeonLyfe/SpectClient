package me.SaberAspect.SpectClient.client.module.modules.client;

import org.lwjgl.input.Keyboard;

import me.SaberAspect.SpectClient.api.util.misc.Discord;
import me.SaberAspect.SpectClient.client.module.Category;
import me.SaberAspect.SpectClient.client.module.Module;

public class DiscordRichPresence extends Module {
	
	public DiscordRichPresence() {
		super ("discordRpc", "shows your are playing SpectClient on discord.", Keyboard.KEY_NONE, Category.CLIENT);
	}
	
		public void onEnable() {
	    Discord.startRPC();
	}
	
	public void onDisable() {
	    Discord.stopRPC();
		}
	}
	