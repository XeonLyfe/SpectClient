package me.SaberAspect.SpectClient.hack.hacks;

import me.SaberAspect.SpectClient.hack.Hack;
import me.SaberAspect.SpectClient.hack.HackCategory;
import me.SaberAspect.SpectClient.utils.Utils;
import me.SaberAspect.SpectClient.value.types.BooleanValue;
import net.minecraftforge.fml.common.gameevent.TickEvent.ClientTickEvent;

public class Targets extends Hack{

	public BooleanValue players;
    public BooleanValue mobs;
    public BooleanValue invisibles;
    public BooleanValue murder;
    
	public Targets() {
		super("Targets", HackCategory.ANOTHER);
		this.setShow(false);
		this.setToggled(true);
		
		players = new BooleanValue("Players", true);
		mobs = new BooleanValue("Mobs", false);
		invisibles = new BooleanValue("Invisibles", false);
		murder = new BooleanValue("Murder", false);
		
		addValue(players, mobs, invisibles, murder);
	}
	
	@Override
	public String getDescription() {
		return "Manage targets for hacks.";
	}
}
