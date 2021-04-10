package me.SaberAspect.SpectClient.client.module.modules.pvp;

import org.lwjgl.input.Keyboard;

import me.SaberAspect.Main;
import me.SaberAspect.SpectClient.client.module.Category;
import me.SaberAspect.SpectClient.client.module.Module;
import me.SaberAspect.SpectClient.client.module.ModuleManager;
import me.SaberAspect.SpectClient.client.setting.settings.BooleanSetting;
import me.SaberAspect.SpectClient.client.setting.settings.ModeSetting;
import me.SaberAspect.SpectClient.client.setting.settings.NumberSetting;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.init.Items;
import net.minecraft.util.EnumHand;

public class AutoGap extends Module {
	public ModeSetting mode = new ModeSetting("mode", this, "always", "always", "smart");
	public NumberSetting health = new NumberSetting("health", this, 16, 1, 20, 1);
	public ModeSetting disableOn = new ModeSetting("disableOn", this, "switchToCrystal", "switchToCrystal", "autoCrystalEnabled");
	public BooleanSetting disableOnSurround = new BooleanSetting("disableOnSurround", this, false);
	
	public AutoGap() {
		super("autoGap", "automattically eats any gapples in your hand.", Keyboard.KEY_NONE, Category.PVP);
		this.addSettings(mode, health, disableOnSurround);;
	}
	
	@Override
	public void onEnable() {
		Main.EVENT_BUS.subscribe(this);
	}
	
	@Override
	public void onDisable() {
		Main.EVENT_BUS.unsubscribe(this);
		
		 if (wasEating) {
			 wasEating = false;
	            KeyBinding.setKeyBindState(mc.gameSettings.keyBindUseItem.getKeyCode(), false);
		 }
	}
	
	private boolean wasEating = false;
	
	public void onUpdate() {
		if(mode.is("always")) {
				if(mc.gameSettings.keyBindSprint.isKeyDown()) mc.player.setSprinting(true);
				eatGap();
		}
		
		if(mode.is("smart")) {
			if(mc.player.getHealth() <= health.getValue()) eatGap();
			
			if (wasEating && mc.player.getHealth() >= health.getValue()) {
				wasEating = false;
	            KeyBinding.setKeyBindState(mc.gameSettings.keyBindUseItem.getKeyCode(), false);
	        }
		}
		
		if(disableOnSurround.isEnabled()) {
			if(((Surround)ModuleManager.getModuleByName("surround")).shiftOnly.isEnabled()) {
				if(mc.player.isSneaking()) toggle();
			}else {
				if(ModuleManager.isModuleEnabled("surround")) toggle();
			}
		}
	}
	
	public void eatGap() {
			if(mc.player.getHeldItemMainhand().getItem() == Items.GOLDEN_APPLE || mc.player.getHeldItemOffhand().getItem() == Items.GOLDEN_APPLE) {
				if(mc.currentScreen == null) {
					KeyBinding.setKeyBindState(mc.gameSettings.keyBindUseItem.getKeyCode(), true);
					wasEating = true;
				}else {
		            mc.playerController.processRightClick(mc.player, mc.world, EnumHand.MAIN_HAND);
				}
			}
	}
}