package me.SaberAspect.SpectClient.hack.hacks;

import me.SaberAspect.SpectClient.hack.Hack;
import me.SaberAspect.SpectClient.hack.HackCategory;
import me.SaberAspect.SpectClient.utils.TimerUtils;
import me.SaberAspect.SpectClient.value.types.DoubleValue;
import me.SaberAspect.SpectClient.wrappers.Wrapper;
import net.minecraftforge.fml.common.gameevent.TickEvent.ClientTickEvent;

public class AntiAfk extends Hack{
	
	public DoubleValue delay;
	public TimerUtils timer;
	
	public AntiAfk() {
		super("AntiAfk", HackCategory.ANOTHER);
		
		this.timer = new TimerUtils();
		this.delay = new DoubleValue("DelaySec", 10.0D, 1.0D, 100.0D);
		
		this.addValue(delay);
	}
	
	@Override
	public String getDescription() {
		return "Prevents from being kicked for AFK.";
	}
	
	@Override
	public void onClientTick(ClientTickEvent event) { 
		if(timer.isDelay((long)(1000 * delay.getValue().longValue()))) {
			Wrapper.INSTANCE.player().jump();
			timer.setLastMS();
		}
		super.onClientTick(event); 
	}
}
