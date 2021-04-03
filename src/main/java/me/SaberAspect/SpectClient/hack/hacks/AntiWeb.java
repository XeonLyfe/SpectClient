package me.SaberAspect.SpectClient.hack.hacks;

import java.lang.reflect.Field;

import me.SaberAspect.SpectClient.hack.Hack;
import me.SaberAspect.SpectClient.hack.HackCategory;

import me.SaberAspect.SpectClient.utils.system.Mapping;
import me.SaberAspect.SpectClient.wrappers.Wrapper;
import net.minecraft.entity.Entity;
import net.minecraftforge.fml.common.gameevent.TickEvent.ClientTickEvent;

public class AntiWeb extends Hack{
	
	public AntiWeb() {
		super("AntiWeb", HackCategory.PLAYER);
	}
	
	@Override
	public String getDescription() {
		return "Does not change walking speed in web.";
	}
	
	@Override
	public void onClientTick(ClientTickEvent event) {
		try {
			Field isInWeb = Entity.class.getDeclaredField(Mapping.isInWeb);
			isInWeb.setAccessible(true);
			isInWeb.setBoolean(Wrapper.INSTANCE.player(), false);
		} catch (Exception ex) {
			this.setToggled(false);
		}
		super.onClientTick(event);
	}
	
}
