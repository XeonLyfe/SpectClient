package me.SaberAspect.SpectClient.hack.hacks;

import java.lang.reflect.Field;

import me.SaberAspect.SpectClient.hack.Hack;
import me.SaberAspect.SpectClient.hack.HackCategory;

import me.SaberAspect.SpectClient.utils.system.Mapping;
import me.SaberAspect.SpectClient.wrappers.Wrapper;
import net.minecraft.client.multiplayer.PlayerControllerMP;
import net.minecraftforge.fml.common.gameevent.TickEvent.ClientTickEvent;

public class Spider extends Hack{
	
	public Spider() {
		super("Spider", HackCategory.PLAYER);
	}
	
	@Override
	public String getDescription() {
		return "Allows you to climb up walls like a spider.";
	}
	
	@Override
	public void onClientTick(ClientTickEvent event) {
        if(!Wrapper.INSTANCE.player().isOnLadder() 
        		&& Wrapper.INSTANCE.player().collidedHorizontally 
        		&& Wrapper.INSTANCE.player().motionY < 0.2) {
        	Wrapper.INSTANCE.player().motionY = 0.2;
        }
		super.onClientTick(event);
	}
	
}
