package me.SaberAspect.SpectClient.client.module.modules.movement;

import org.lwjgl.input.Keyboard;

import me.SaberAspect.SpectClient.client.module.Category;
import me.SaberAspect.SpectClient.client.module.Module;
import me.SaberAspect.SpectClient.client.setting.settings.ModeSetting;
import net.minecraft.client.Minecraft;

public class Sprint extends Module {
	public ModeSetting mode = new ModeSetting("mode", this, "normal", "normal", "sickomode");
	
	private Minecraft mc = Minecraft.getMinecraft();
	public boolean on;
	
	public Sprint() {
		super ("sprint", "now u cant walk, good going.", Keyboard.KEY_NONE, Category.MOVEMENT);
		this.addSettings(mode);
	}
	
	@Override
	public void onUpdate() {
			if(mode.is("normal")) {
			if(mc.player.movementInput.moveForward > 0 && !mc.player.isSneaking() && !mc.player.collidedHorizontally) {
				mc.player.setSprinting(true);
				}
			}else if(mode.is("sickomode")) {
					mc.player.setSprinting(true);
			
		}
	}

}
