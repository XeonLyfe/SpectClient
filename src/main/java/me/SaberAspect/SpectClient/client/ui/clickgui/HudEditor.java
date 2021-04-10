package me.SaberAspect.SpectClient.client.ui.clickgui;

import org.lwjgl.input.Keyboard;

import me.SaberAspect.Main;
import me.SaberAspect.SpectClient.client.module.Category;
import me.SaberAspect.SpectClient.client.module.Module;
import me.SaberAspect.SpectClient.client.setting.settings.BooleanSetting;

public class HudEditor extends Module {
	public BooleanSetting exitToClickGui = new BooleanSetting("exitToClickGui", this, true);
	
	public HudEditor() {
		super("hudEditor", "descrp", Keyboard.KEY_NONE, Category.HUD);
		this.addSettings(exitToClickGui);
	}
	
	public void onEnable() {
		Main.clickGui.enterHUDEditor();
	}
	
	public void onUpdate() {
		
		if(Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) {
			if(exitToClickGui.isEnabled()) {
				this.setToggled(false); 
				Main.clickGui.enterGUI(); 
			}else {
				this.setToggled(false);
			}
		}
		
	}
	
	public void onDisable() {
	}
}
