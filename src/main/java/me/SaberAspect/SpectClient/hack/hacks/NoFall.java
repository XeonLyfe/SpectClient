package me.SaberAspect.SpectClient.hack.hacks;

import me.SaberAspect.SpectClient.hack.Hack;
import me.SaberAspect.SpectClient.hack.HackCategory;

import me.SaberAspect.SpectClient.wrappers.Wrapper;
import net.minecraft.network.play.client.CPacketPlayer;
import net.minecraftforge.fml.common.gameevent.TickEvent.ClientTickEvent;

public class NoFall extends Hack{

	public NoFall() {
		super("AntiFall", HackCategory.PLAYER);
	}
	
	@Override
	public String getDescription() {
		return "Gives you zero damage on fall.";
	}
	
	@Override
	public void onClientTick(ClientTickEvent event) { 
		if(Wrapper.INSTANCE.player().fallDistance > 2) 
			Wrapper.INSTANCE.sendPacket(new CPacketPlayer(true)); 
		super.onClientTick(event); 
	}
}
