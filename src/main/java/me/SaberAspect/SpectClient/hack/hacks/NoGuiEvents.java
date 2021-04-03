package me.SaberAspect.SpectClient.hack.hacks;

import me.SaberAspect.SpectClient.hack.Hack;
import me.SaberAspect.SpectClient.hack.HackCategory;

public class NoGuiEvents extends Hack{

	public NoGuiEvents() {
		super("NoGuiEvents", HackCategory.ANOTHER);
	}
	
	@Override
	public String getDescription() {
		return "Disables events when the GUI is open.";
	}
}
