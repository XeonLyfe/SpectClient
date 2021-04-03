package me.SaberAspect.SpectClient.hack.hacks;

import org.lwjgl.input.Keyboard;

import me.SaberAspect.SpectClient.Main;
import me.SaberAspect.SpectClient.gui.GuiConsole;
import me.SaberAspect.SpectClient.hack.Hack;
import me.SaberAspect.SpectClient.hack.HackCategory;
import me.SaberAspect.SpectClient.value.types.BooleanValue;
import me.SaberAspect.SpectClient.wrappers.Wrapper;

public class Console extends Hack{

	public Console() {
		super("Console", HackCategory.VISUAL);
		this.setKey(Keyboard.KEY_Y);
		this.setShow(false);
	}
	
	@Override
	public String getDescription() {
		return "Console.";
	}
	
	@Override
	public void onEnable() {
		if(GhostMode.enabled) return;
		Wrapper.INSTANCE.mc().displayGuiScreen(new GuiConsole());
		super.onEnable();
	}
}
