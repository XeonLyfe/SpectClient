package me.SaberAspect.SpectClient.hack.hacks;

import me.SaberAspect.SpectClient.Main;
import me.SaberAspect.SpectClient.hack.Hack;
import me.SaberAspect.SpectClient.hack.HackCategory;
import me.SaberAspect.SpectClient.utils.system.Connection;
import me.SaberAspect.SpectClient.utils.system.Connection.Side;
import me.SaberAspect.SpectClient.value.Mode;
import me.SaberAspect.SpectClient.value.types.BooleanValue;
import me.SaberAspect.SpectClient.value.types.ModeValue;
import me.SaberAspect.SpectClient.wrappers.Wrapper;
import net.minecraft.network.play.client.CPacketPlayer;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.ClientTickEvent;

public class GhostMode extends Hack{
	
	public static boolean enabled = false;
	
	public GhostMode() {
		super("GhostMode", HackCategory.ANOTHER);
	}
	
	@Override
	public String getDescription() {
		return "Disable all hacks.";
	}
	
	@Override
	public void onEnable() {
		if(this.getKey() == -1) 
			return;
		enabled = true;
		super.onEnable();
	}
	
	@Override
	public void onDisable() {
		enabled = false;
		super.onDisable();
	}
	
}
