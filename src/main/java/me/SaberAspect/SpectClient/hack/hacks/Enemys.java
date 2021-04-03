package me.SaberAspect.SpectClient.hack.hacks;

import me.SaberAspect.SpectClient.hack.Hack;
import me.SaberAspect.SpectClient.hack.HackCategory;
import me.SaberAspect.SpectClient.value.types.BooleanValue;

public class Enemys extends Hack{

	public Enemys() {
		super("Enemys", HackCategory.ANOTHER);
	}
	
	@Override
	public String getDescription() {
		return "Target only in enemy list.";
	}
}
