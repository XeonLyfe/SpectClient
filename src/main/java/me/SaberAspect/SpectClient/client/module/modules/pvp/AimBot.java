package me.SaberAspect.SpectClient.client.module.modules.pvp;

import org.lwjgl.input.Keyboard;

import me.SaberAspect.SpectClient.client.module.Category;
import me.SaberAspect.SpectClient.client.module.Module;

public class AimBot extends Module {
	
	public AimBot() {
		super ("aimBot", "locks camera on to the closest target.", Keyboard.KEY_NONE, Category.PVP);
	}

}
