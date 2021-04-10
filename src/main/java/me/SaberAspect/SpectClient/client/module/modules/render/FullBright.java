 package me.SaberAspect.SpectClient.client.module.modules.render;

import org.lwjgl.input.Keyboard;

import me.SaberAspect.Main;
import me.SaberAspect.SpectClient.api.event.events.PlayerUpdateEvent;
import me.SaberAspect.SpectClient.client.module.Category;
import me.SaberAspect.SpectClient.client.module.Module;
import me.zero.alpine.listener.EventHandler;
import me.zero.alpine.listener.Listener;
import net.minecraft.init.MobEffects;

/*
 * Written by @SrgantMooMoo on 11/17/20.
 * Rewritten by @SrgantMooMoo on 1/4/21.
 */

public class FullBright extends Module {
	
	public FullBright() {
		super ("fullBright", "makes everything fully bright.", Keyboard.KEY_NONE, Category.RENDER);
	}
	 private float lastGamma;

	 @Override
	 public void onEnable() {
	     Main.EVENT_BUS.subscribe(this);
	        
	     lastGamma = mc.gameSettings.gammaSetting;
	 }

	 @Override
	 public void onDisable() {
	     Main.EVENT_BUS.unsubscribe(this);
	           
	     mc.gameSettings.gammaSetting = this.lastGamma;
	 }

	 @EventHandler
	 private Listener<PlayerUpdateEvent> OnPlayerUpdate = new Listener<>(p_Event -> {
	     mc.gameSettings.gammaSetting = 1000;
         mc.player.removePotionEffect(MobEffects.NIGHT_VISION);
	 });
}