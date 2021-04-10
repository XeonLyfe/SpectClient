package me.SaberAspect.SpectClient.client.module.modules.render;

import org.lwjgl.input.Keyboard;

import me.SaberAspect.SpectClient.client.module.Category;
import me.SaberAspect.SpectClient.client.module.Module;
import me.SaberAspect.SpectClient.client.setting.settings.NumberSetting;
import net.minecraft.client.renderer.ItemRenderer;

/*
 * Written by @SrgantMooMoo on 11/17/20.
 */

	public class LowOffHand extends Module {
		public NumberSetting lowness = new NumberSetting("lowness", this, 0.7, 0, 1, 0.1);
		
		public LowOffHand() {
			super ("lowOffHand", "lowers the offhand.", Keyboard.KEY_NONE, Category.RENDER);
			this.addSettings(lowness);
		}
		
		ItemRenderer itemRenderer = mc.entityRenderer.itemRenderer;

		
		@Override
		public void onUpdate(){
			itemRenderer.equippedProgressOffHand = (float) lowness.getValue();
		}
	
}


