package me.SaberAspect.SpectClient.client.module.modules.pvp;

import org.lwjgl.input.Keyboard;

import me.SaberAspect.SpectClient.client.module.Category;
import me.SaberAspect.SpectClient.client.module.Module;

public class LogOutSpot extends Module {
	
	public LogOutSpot() {
		super ("logOutSpot", "shows where a player logs out.", Keyboard.KEY_NONE, Category.PVP);
	}

}
