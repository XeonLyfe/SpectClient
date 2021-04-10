package me.SaberAspect.SpectClient.client.module.modules.render;

import org.lwjgl.input.Keyboard;

import me.SaberAspect.Main;
import me.SaberAspect.SpectClient.api.event.events.TransformSideFirstPersonEvent;
import me.SaberAspect.SpectClient.client.module.Category;
import me.SaberAspect.SpectClient.client.module.Module;
import me.SaberAspect.SpectClient.client.setting.settings.BooleanSetting;
import me.SaberAspect.SpectClient.client.setting.settings.NumberSetting;
import me.zero.alpine.listener.EventHandler;
import me.zero.alpine.listener.Listener;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.EnumHandSide;
import net.minecraftforge.common.MinecraftForge;

public class ViewModel extends Module {
	public BooleanSetting cancelEating = new BooleanSetting("noEat", this, false);
	public NumberSetting LeftX = new NumberSetting("LeftX", this, 0, -2, 2, 0.1);
	public NumberSetting LeftY = new NumberSetting("LeftY", this, 0, -2, 2, 0.1);
	public NumberSetting LeftZ = new NumberSetting("LeftZ", this, 0, -2, 2, 0.1);
	public NumberSetting RightX = new NumberSetting("RightX", this, 0, -2, 2, 0.1);
	public NumberSetting RightY = new NumberSetting("RightY", this, 0, -2, 2, 0.1);
	public NumberSetting RightZ = new NumberSetting("RightZ", this, 0, -2, 2, 0.1);
	
	public ViewModel() {
		super("viewModel", "allows u to change how your model look in 1st person.", Keyboard.KEY_NONE, Category.RENDER);
		this.addSettings(LeftX, LeftY, LeftZ, RightX, RightY, RightZ);
	}

	@EventHandler
	private final Listener<TransformSideFirstPersonEvent> listener = new Listener<>(event -> {
		if (event.getEnumHandSide() == EnumHandSide.RIGHT) {
			GlStateManager.translate(RightX.getValue(), RightY.getValue(), RightZ.getValue());
		} else if (event.getEnumHandSide() == EnumHandSide.LEFT) {
			GlStateManager.translate(LeftX.getValue(), LeftY.getValue(), LeftZ.getValue());
		}
	});

	public void onEnable() {
		Main.EVENT_BUS.subscribe(this);
		MinecraftForge.EVENT_BUS.register(this);
	}

	public void onDisable() {
		Main.EVENT_BUS.unsubscribe(this);
		MinecraftForge.EVENT_BUS.unregister(this);
	}
}