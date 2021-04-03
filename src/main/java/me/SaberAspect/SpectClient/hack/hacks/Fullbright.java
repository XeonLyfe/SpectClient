package me.SaberAspect.SpectClient.hack.hacks;

import me.SaberAspect.SpectClient.hack.Hack;
import me.SaberAspect.SpectClient.hack.HackCategory;
import me.SaberAspect.SpectClient.utils.Utils;
import me.SaberAspect.SpectClient.value.Mode;
import me.SaberAspect.SpectClient.value.types.BooleanValue;
import me.SaberAspect.SpectClient.value.types.ModeValue;
import me.SaberAspect.SpectClient.wrappers.Wrapper;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionHelper;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.gameevent.TickEvent.ClientTickEvent;

public class Fullbright extends Hack{

	public ModeValue mode;
	
	public Fullbright() {
		super("Fullbright", HackCategory.VISUAL);
		
		this.mode = new ModeValue("Mode", new Mode("Gamma", true), new Mode("Effect", false));
		this.addValue(mode);
	}
	
	@Override
	public String getDescription() {
		return "Gives you max Brightness.";
	}
	
	@Override
	public void onEnable() {
		super.onEnable();
	}
	@Override
	public void onDisable() {
		if(this.mode.getMode("Gamma").isToggled())
			Wrapper.INSTANCE.mcSettings().gammaSetting = 1;
		else
			Utils.removeEffect(16);
		super.onDisable();
	}
	
	@Override
	public void onClientTick(ClientTickEvent event) {
		if(this.mode.getMode("Gamma").isToggled())
			Wrapper.INSTANCE.mcSettings().gammaSetting = 10;
		else
			Utils.addEffect(16, 1000, 3);
		super.onClientTick(event);
	}
}
