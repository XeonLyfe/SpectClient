package me.SaberAspect.SpectClient.client.module.modules.render;

import org.lwjgl.input.Keyboard;

import me.SaberAspect.Main;
import me.SaberAspect.SpectClient.api.event.events.RenderRainEvent;
import me.SaberAspect.SpectClient.client.module.Category;
import me.SaberAspect.SpectClient.client.module.Module;
import me.SaberAspect.SpectClient.client.setting.settings.BooleanSetting;
import me.SaberAspect.SpectClient.client.setting.settings.ModeSetting;
import me.zero.alpine.listener.EventHandler;
import me.zero.alpine.listener.Listener;
import net.minecraft.init.MobEffects;
import net.minecraftforge.client.GuiIngameForge;
import net.minecraftforge.client.event.RenderBlockOverlayEvent;
import net.minecraftforge.client.event.RenderBlockOverlayEvent.OverlayType;
import net.minecraftforge.common.MinecraftForge;

public class NoRender extends Module {
	public BooleanSetting rain = new BooleanSetting("rain", this, false);
	public ModeSetting hurtCam = new ModeSetting("hurtCam", this, "disabled", "disabled", "normal", "penis");
	public BooleanSetting potionEffects = new BooleanSetting("potionEffects", this, false);
	public BooleanSetting fire = new BooleanSetting("fire", this, false);
	public BooleanSetting portalEffect = new BooleanSetting("portalEffect", this, false);
	
	public NoRender() {
		super("noRender", "stops certain events from rendering.", Keyboard.KEY_NONE, Category.RENDER);
		this.addSettings(hurtCam, rain, fire, potionEffects, portalEffect);
	}
	
	@Override
	public void onEnable() {
		Main.EVENT_BUS.subscribe(this);
		MinecraftForge.EVENT_BUS.register(this);
	}
	
	@Override
	public void onDisable() {
		Main.EVENT_BUS.unsubscribe(this);
		MinecraftForge.EVENT_BUS.unregister(this);
		GuiIngameForge.renderPortal = true;
	}
	
	@Override
	public void onUpdate() {
		// hurtCam penis mode
		if(hurtCam.is("penis")) {
			mc.player.performHurtAnimation();
		}
		
		// portalEffect
		if(portalEffect.isEnabled()) {
			GuiIngameForge.renderPortal = false;
			mc.player.removeActivePotionEffect(MobEffects.NAUSEA);
		}
	}
	
	// rain
	@EventHandler
	private Listener<RenderRainEvent> onRain = new Listener<>(event -> {
		if(rain.isEnabled()) {
		    if (mc.world == null)
		        return;
		    event.cancel();
		}
	});
	
	// hurtCam = MixinEntityRenderer
	
	// potionEffect = mixin... some sorta overlay idk
	
	// fire
	@EventHandler
    private Listener<RenderBlockOverlayEvent> OnBlockOverlayEvent = new Listener<>(event -> {
        if (fire.isEnabled() && event.getOverlayType() == OverlayType.FIRE) event.setCanceled(true);
    });
}
