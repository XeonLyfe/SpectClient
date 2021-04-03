package me.SaberAspect.SpectClient.hack.hacks;

import me.SaberAspect.SpectClient.hack.Hack;
import me.SaberAspect.SpectClient.hack.HackCategory;
import me.SaberAspect.SpectClient.value.Mode;
import me.SaberAspect.SpectClient.value.types.BooleanValue;
import me.SaberAspect.SpectClient.value.types.ModeValue;

public class Teams extends Hack{

	public ModeValue mode;
	
	public Teams() {
		super("Teams", HackCategory.ANOTHER);
		this.mode = new ModeValue("Mode", new Mode("Base", false), new Mode("ArmorColor", false), new Mode("NameColor", true));
		this.addValue(mode);
	}
	
	@Override
	public String getDescription() {
		return "Ignore if player in your team.";
	}

}
