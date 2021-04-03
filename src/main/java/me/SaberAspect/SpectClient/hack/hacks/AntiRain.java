package me.SaberAspect.SpectClient.hack.hacks;

import me.SaberAspect.SpectClient.hack.Hack;
import me.SaberAspect.SpectClient.hack.HackCategory;
import me.SaberAspect.SpectClient.wrappers.Wrapper;
import net.minecraftforge.fml.common.gameevent.TickEvent.ClientTickEvent;

public class AntiRain extends Hack{

	public AntiRain() {
		super("AntiRain", HackCategory.VISUAL);
	}
	
	@Override
	public String getDescription() {
		return "Stops rain.";
	}
	
	@Override
	public void onClientTick(ClientTickEvent event) {
        Wrapper.INSTANCE.world().setRainStrength(0.0f);
		super.onClientTick(event);
	}

}
