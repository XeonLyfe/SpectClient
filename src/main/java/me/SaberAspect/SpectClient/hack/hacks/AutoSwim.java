package me.SaberAspect.SpectClient.hack.hacks;

import me.SaberAspect.SpectClient.hack.Hack;
import me.SaberAspect.SpectClient.hack.HackCategory;

import me.SaberAspect.SpectClient.utils.Utils;
import me.SaberAspect.SpectClient.value.Mode;
import me.SaberAspect.SpectClient.value.types.ModeValue;
import me.SaberAspect.SpectClient.wrappers.Wrapper;
import net.minecraft.init.MobEffects;
import net.minecraftforge.fml.common.gameevent.TickEvent.ClientTickEvent;

public class AutoSwim extends Hack{

	public ModeValue mode;
	public AutoSwim() {
		super("AutoSwim", HackCategory.PLAYER);
		
		this.mode = new ModeValue("Mode", new Mode("Jump", true), new Mode("Dolphin", false), new Mode("Fish", false));
		this.addValue(mode);
	}
	
	@Override
    public String getDescription() {
        return "Jumps automatically when you in water.";
    }
	
	@Override
	public void onClientTick(ClientTickEvent event) {
		if(!Wrapper.INSTANCE.player().isInWater() && !Wrapper.INSTANCE.player().isInLava()) {
			return;
		}
		if(Wrapper.INSTANCE.player().isSneaking() || Wrapper.INSTANCE.mcSettings().keyBindJump.isKeyDown()) {
			return;
		}
		if(mode.getMode("Jump").isToggled()) {
			Wrapper.INSTANCE.player().jump();
		} 
		else if(mode.getMode("Dolphin").isToggled()) {
			Wrapper.INSTANCE.player().motionY += 0.04f;
		} 
		else if(mode.getMode("Fish").isToggled()) {
			Wrapper.INSTANCE.player().motionY += 0.02f;
		}
		super.onClientTick(event);
	}
}
