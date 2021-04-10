package me.SaberAspect.SpectClient.client.module.modules.player;

import org.lwjgl.input.Keyboard;

import me.SaberAspect.SpectClient.client.module.Category;
import me.SaberAspect.SpectClient.client.module.Module;

public class AutoRespawn extends Module {
	
	public AutoRespawn() {
		super("autoRespawn", "automatically respawns after death occurs.", Keyboard.KEY_NONE, Category.PLAYER);
	}
	
	public void onUpdate() {
		if(mc.player.isDead) {
			mc.player.respawnPlayer();
		}
	}

}
