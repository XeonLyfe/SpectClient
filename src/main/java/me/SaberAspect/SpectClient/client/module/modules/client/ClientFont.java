package me.SaberAspect.SpectClient.client.module.modules.client;

import java.awt.Font;

import org.lwjgl.input.Keyboard;

import me.SaberAspect.Main;
import me.SaberAspect.SpectClient.api.util.font.CustomFontRenderer;
import me.SaberAspect.SpectClient.client.module.Category;
import me.SaberAspect.SpectClient.client.module.Module;
import me.SaberAspect.SpectClient.client.setting.settings.ModeSetting;
import net.minecraft.client.gui.GuiDisconnected;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.gui.GuiScreenServerList;

public class ClientFont extends Module {
	
	public ClientFont() {
		super ("clientFont", "custom font for the client.", Keyboard.KEY_NONE, Category.CLIENT);
	}
	
	public void onEnable() {
		
		if(this.isOn()) {
			Main.customFontRenderer = new CustomFontRenderer(new Font("Verdana", Font.PLAIN, 18), true, true);
		}
	}
}
