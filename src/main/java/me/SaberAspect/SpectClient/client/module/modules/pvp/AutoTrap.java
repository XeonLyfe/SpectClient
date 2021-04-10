package me.SaberAspect.SpectClient.client.module.modules.pvp;

import org.lwjgl.input.Keyboard;

import me.SaberAspect.SpectClient.client.module.Category;
import me.SaberAspect.SpectClient.client.module.Module;

public class AutoTrap  extends Module {
	
	public AutoTrap() {
		super ("autoTrap", "automatically traps opponent.", Keyboard.KEY_NONE, Category.PVP);
	}

}
