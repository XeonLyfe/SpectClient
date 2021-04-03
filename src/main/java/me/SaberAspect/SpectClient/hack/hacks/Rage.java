package me.SaberAspect.SpectClient.hack.hacks;

import me.SaberAspect.SpectClient.hack.Hack;
import me.SaberAspect.SpectClient.hack.HackCategory;
import me.SaberAspect.SpectClient.utils.TimerUtils;
import me.SaberAspect.SpectClient.utils.Utils;
import me.SaberAspect.SpectClient.value.types.IntegerValue;
import me.SaberAspect.SpectClient.wrappers.Wrapper;
import net.minecraft.network.play.client.CPacketPlayer;
import net.minecraftforge.fml.common.gameevent.TickEvent.ClientTickEvent;

public class Rage extends Hack{
	
	public TimerUtils timer;
	
	public IntegerValue delay;
	
	public Rage() {
		super("Rage", HackCategory.PLAYER);
		
		this.timer = new TimerUtils();
		delay = new IntegerValue("Delay", 0, 0, 1000);
		
		this.addValue(delay);
	}
	
	@Override
	public String getDescription() {
		return "Changes the tick rate of the client and the CPacketPlayer rotation motion (Do not use on servers)";
	}
		
		
	
	@Override
	public void onClientTick(ClientTickEvent event) {
		if(timer.isDelay(delay.getValue().longValue())) {
			Wrapper.INSTANCE.sendPacket(new CPacketPlayer.Rotation(Utils.random(-160, 160), Utils.random(-160, 160), true));
			timer.setLastMS();
		}
		super.onClientTick(event);
	}
	
//	@Override // TODO Added camera fix
//	public void onCameraSetup(CameraSetup event) {
//		// TODO Auto-generated method stub
//		super.onCameraSetup(event);
//	}
}
