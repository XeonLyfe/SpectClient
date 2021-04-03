package me.SaberAspect.SpectClient.hack.hacks;

import org.lwjgl.input.Keyboard;

import me.SaberAspect.SpectClient.gui.click.ClickGuiScreen;
import me.SaberAspect.SpectClient.hack.Hack;
import me.SaberAspect.SpectClient.hack.HackCategory;
import me.SaberAspect.SpectClient.managers.HackManager;
import me.SaberAspect.SpectClient.utils.Utils;
import me.SaberAspect.SpectClient.wrappers.Wrapper;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.init.MobEffects;
import net.minecraftforge.fml.common.gameevent.TickEvent.ClientTickEvent;

public class AutoSprint extends Hack{

	public AutoSprint() {
		super("AutoSprint", HackCategory.PLAYER);
	}
	
	@Override
    public String getDescription() {
        return "Sprints automatically when you should be walking.";
    }
	
	@Override
	public void onClientTick(ClientTickEvent event) {
		if(isMoveInGui() && this.canSprint(false)) {
			Wrapper.INSTANCE.player().setSprinting(true); 
			return;
		}
		if(this.canSprint(true))
			Wrapper.INSTANCE.player().setSprinting(Utils.isMoving(Wrapper.INSTANCE.player()));
		super.onClientTick(event);
	}
	
	boolean isMoveInGui() {
		return Keyboard.isKeyDown(Wrapper.INSTANCE.mcSettings().keyBindForward.getKeyCode())
				&& (boolean)(Wrapper.INSTANCE.mc().currentScreen instanceof GuiContainer
				|| Wrapper.INSTANCE.mc().currentScreen instanceof ClickGuiScreen) 
				&& HackManager.getHack("GuiWalk").isToggled();
	}
	
	boolean canSprint(boolean forward) {
		if(!Wrapper.INSTANCE.player().onGround) {
			return false;
		}
		if(Wrapper.INSTANCE.player().isSprinting()) {
			return false;
		}
		if(Wrapper.INSTANCE.player().isOnLadder()) {
			return false;
		}
		if(Wrapper.INSTANCE.player().isInWater()) {
			return false;
		}
		if(Wrapper.INSTANCE.player().isInLava()) {
			return false;
		}
		if(Wrapper.INSTANCE.player().collidedHorizontally) {
			return false;
		}
		if(forward && Wrapper.INSTANCE.player().moveForward < 0.1F) {
			return false;
		}
		if(Wrapper.INSTANCE.player().isSneaking()) {
			return false;
		}
		if(Wrapper.INSTANCE.player().getFoodStats().getFoodLevel() < 6) {
			return false;
		}
		if(Wrapper.INSTANCE.player().isRiding()) {
			return false;
		}
		if(Wrapper.INSTANCE.player().isPotionActive(MobEffects.BLINDNESS)) {
			return false;
		}
        return true;
    }
}
