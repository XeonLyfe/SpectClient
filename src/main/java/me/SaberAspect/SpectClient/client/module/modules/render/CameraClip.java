package me.SaberAspect.SpectClient.client.module.modules.render;

import org.lwjgl.input.Keyboard;

import me.SaberAspect.Main;
import me.SaberAspect.SpectClient.api.event.events.RenderCameraEvent;
import me.SaberAspect.SpectClient.client.module.Category;
import me.SaberAspect.SpectClient.client.module.Module;
import me.zero.alpine.listener.EventHandler;
import me.zero.alpine.listener.Listener;

public class CameraClip extends Module {
	
	public CameraClip() {
		super ("cameraClip", "camera clips when in 3rd person.", Keyboard.KEY_NONE, Category.RENDER);
	}
	
	public void onEnable() {
		Main.EVENT_BUS.subscribe(this);
	}
	
	public void onDisable() {
		Main.EVENT_BUS.unsubscribe(this);
	}
	
	@EventHandler
    private Listener<RenderCameraEvent> onRenderCameraEvent = new Listener<>(event -> {
        event.cancel();
    });

}
