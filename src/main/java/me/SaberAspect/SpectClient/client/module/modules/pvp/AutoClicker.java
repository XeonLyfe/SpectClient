package me.SaberAspect.SpectClient.client.module.modules.pvp;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

import me.SaberAspect.SpectClient.client.module.Category;
import me.SaberAspect.SpectClient.client.module.Module;
import net.minecraft.client.settings.KeyBinding;

public class AutoClicker extends Module {
	
	private long lastClick;
	private long hold;
	
	private double speed;
	private double holdLength;
	
	public AutoClicker() {
		super ("autoClicker", "clicks fast when holding down left click.", Keyboard.KEY_NONE, Category.PVP);
	}

	public void onUpdate() {
		if(Mouse.isButtonDown(0)) {
			if(System.currentTimeMillis() - lastClick > speed * 1000) {
				lastClick = System.currentTimeMillis();
				if(hold < lastClick) {
					hold = lastClick;
				}
						int key = mc.gameSettings.keyBindAttack.getKeyCode();
						KeyBinding.setKeyBindState(key, true);
						KeyBinding.onTick(key);
					} else if (System.currentTimeMillis() - hold > holdLength * 1000) {
						KeyBinding.setKeyBindState(mc.gameSettings.keyBindAttack.getKeyCode(), false);
				}
			}
	}

}