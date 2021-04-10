package me.SaberAspect.SpectClient.client.module.modules.player;

import org.lwjgl.input.Keyboard;

import com.mojang.realmsclient.gui.ChatFormatting;

import me.SaberAspect.SpectClient.client.module.Category;
import me.SaberAspect.SpectClient.client.module.Module;
import me.SaberAspect.SpectClient.client.module.ModuleManager;

public class DeathCoords extends Module {
	
	public DeathCoords() {
		super ("deathCoords", "tells you your coords after death occurs.", Keyboard.KEY_NONE, Category.PLAYER);
	}
	
	public void onUpdate() {
		if(mc.player.isDead) {
			ModuleManager.addChatMessage(ChatFormatting.WHITE + "lol u just died loser" + 
					ChatFormatting.GRAY + " (x)" + mc.player.getPosition().x + " (y)" + mc.player.getPosition().y + " (z)" + mc.player.getPosition().z);
			toggled = false;
		}
	}

}