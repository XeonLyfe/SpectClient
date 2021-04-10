package me.SaberAspect.SpectClient.client.module.modules.hud;

import java.awt.Color;
import java.awt.Point;

import com.lukflug.panelstudio.Context;
import com.lukflug.panelstudio.Interface;
import com.lukflug.panelstudio.hud.HUDComponent;
import com.lukflug.panelstudio.theme.Theme;

import me.SaberAspect.SpectClient.api.util.render.JColor;
import me.SaberAspect.SpectClient.client.module.Category;
import me.SaberAspect.SpectClient.client.module.HudModule;
import me.SaberAspect.SpectClient.client.setting.settings.BooleanSetting;
import me.SaberAspect.SpectClient.client.setting.settings.ColorSetting;
import me.SaberAspect.SpectClient.client.setting.settings.NumberSetting;
import me.SaberAspect.SpectClient.client.ui.clickgui.ClickGui;

public class PlayerModel extends HudModule {
	public BooleanSetting rect = new BooleanSetting("rect", this, true);
	public NumberSetting size = new NumberSetting("size", this, 28, 10, 100, 1);
	public ColorSetting color = new ColorSetting("rectColor", this, new JColor(121, 193, 255, 100)); 
	
	public PlayerModel() {
    	super("playerModel","shows the player model on the hud.", new Point(75, 2), Category.HUD);
    	this.addSettings(size, rect, color);
    	
    	
		toggled = true;

    }
	
	@Override
    public void populate (Theme theme) {
    	component = new PlayerModelComponent(theme);
    }
	
	private class PlayerModelComponent extends HUDComponent {

		public PlayerModelComponent (Theme theme) {
			super(getName(),theme.getPanelRenderer(),PlayerModel.this.position);
		}
		
		@Override
		public void render (Context context) {
			super.render(context);
			if(rect.isEnabled()) {
			Color bgcolor=new JColor(color.getValue(),100);
			context.getInterface().fillRect(context.getRect(),bgcolor,bgcolor,bgcolor,bgcolor);
			}
			ClickGui.renderEntity(mc.player,new Point(context.getPos().x+22,context.getPos().y+58-(mc.player.isSneaking()?10:0)), (int) size.getValue());
		}

		@Override
		public int getWidth (Interface inter) {
			return 44;
		}

		@Override
		public void getHeight (Context context) {
			context.setHeight(64);
		}
	}

}