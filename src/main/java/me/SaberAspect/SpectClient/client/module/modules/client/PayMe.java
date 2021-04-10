package me.SaberAspect.SpectClient.client.module.modules.client;

import java.awt.Desktop;
import java.net.URI;

import org.lwjgl.input.Keyboard;

import me.SaberAspect.SpectClient.client.module.Category;
import me.SaberAspect.SpectClient.client.module.Module;
import me.SaberAspect.SpectClient.client.setting.settings.ModeSetting;

public class PayMe extends Module {
	public ModeSetting dupeMode = new ModeSetting("mode", this, "donate", "donate", "github");
	
	public PayMe() {
		super("PayMe", "DO IT!", Keyboard.KEY_NONE, Category.CLIENT);
		this.addSettings(dupeMode);
	}
	
	public void onEnable() {
		if(dupeMode.is("donate")) {
			try {
				Desktop.getDesktop().browse(URI.create("https://www.paypal.com/biz/fund?id=4A9XUTEQMVUZG"));
			} catch (Exception e) {}
		}
		if(dupeMode.is("github")) {
			try {
				Desktop.getDesktop().browse(URI.create("https://github.com/SaberAspect/SpectClient"));
			} catch (Exception e) {}
		}
		
	}

}
