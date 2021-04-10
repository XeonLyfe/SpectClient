package me.SaberAspect.SpectClient.client.module.modules.render;

import org.lwjgl.input.Keyboard;

import me.SaberAspect.Main;
import me.SaberAspect.SpectClient.client.module.Category;
import me.SaberAspect.SpectClient.client.module.Module;
import me.SaberAspect.SpectClient.client.setting.settings.ModeSetting;
import me.SaberAspect.SpectClient.client.setting.settings.NumberSetting;

public class World extends Module {
	public ModeSetting weather = new ModeSetting("weather", this, "clear", "clear", "rain", "thunderStorm");
	public NumberSetting time = new NumberSetting("time", this, 0.0D, 0.0D, 24000.0D, 1.0D);
	
	public World() {
		super("world", "change world shit.", Keyboard.KEY_NONE, Category.RENDER);
		this.addSettings(weather, time);
	}
	
	public void onEnable() {
		Main.EVENT_BUS.subscribe(this);
	}

	public void onDisable() {
		Main.EVENT_BUS.unsubscribe(this);
	}
	
	public void onUpdate() {
		// timeOfDay
		mc.world.setWorldTime((long) time.getValue());
		
		// weather
		if(weather.is("clear")) mc.world.setRainStrength(0);
		if(weather.is("rain")) mc.world.setRainStrength(1);
		if(weather.is("thunderStorm")) mc.world.setRainStrength(2);
	}	
}
