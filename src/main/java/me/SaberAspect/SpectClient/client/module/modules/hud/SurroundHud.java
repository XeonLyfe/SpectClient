package me.SaberAspect.SpectClient.client.module.modules.hud;

import java.awt.Color;
import java.awt.Point;

import com.lukflug.panelstudio.hud.HUDList;
import com.lukflug.panelstudio.hud.ListComponent;
import com.lukflug.panelstudio.theme.Theme;
import com.mojang.realmsclient.gui.ChatFormatting;

import me.SaberAspect.SpectClient.api.util.render.JColor;
import me.SaberAspect.SpectClient.client.module.Category;
import me.SaberAspect.SpectClient.client.module.HudModule;
import me.SaberAspect.SpectClient.client.module.ModuleManager;
import me.SaberAspect.SpectClient.client.setting.settings.BooleanSetting;
import me.SaberAspect.SpectClient.client.setting.settings.ColorSetting;


public class SurroundHud extends HudModule {
	private SurroundInfoList list=new SurroundInfoList();
	
	public ColorSetting color = new ColorSetting("color", this, new JColor(230, 0, 0, 255)); 
	public BooleanSetting sort = new BooleanSetting("sortRight", this, false);

	public SurroundHud() {
		super("surroundHud", "shows if surround is on or off.", new Point(-2, 59), Category.HUD);
		this.addSettings(color, sort);
		
		
		toggled = true;

	}
	
	@Override
	public void populate (Theme theme) {
		component = new ListComponent(getName(), theme.getPanelRenderer(), position, list);
	}
	
	private class SurroundInfoList implements HUDList {

		@Override
		public int getSize() {
			return 1;
		}

		@Override
		public String getItem(int index) {
			if (ModuleManager.isModuleEnabled("surround")) return ChatFormatting.GREEN + "srnd" + " on";
			else return "srnd" + " off";
		}

		@Override
		public Color getItemColor(int index) {
			return color.getValue();
		}

		@Override
		public boolean sortUp() {
			return false;
		}

		@Override
		public boolean sortRight() {
			return sort.isEnabled();
		}
	}
}