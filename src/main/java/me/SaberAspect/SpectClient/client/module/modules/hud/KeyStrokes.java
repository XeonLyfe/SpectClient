package me.SaberAspect.SpectClient.client.module.modules.hud;

import java.awt.Color;
import java.awt.Dimension;

import org.lwjgl.opengl.GL11;

import me.SaberAspect.SpectClient.client.module.Category;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;

/*
 * Written by @SrgantMooMoo on November 7th, 2020.
 * Rewritten by @SrgantMooMoo on January 10th, 2021.
 */

import java.awt.Point;
import java.awt.Rectangle;

import com.lukflug.panelstudio.Context;
import com.lukflug.panelstudio.Interface;
import com.lukflug.panelstudio.hud.HUDComponent;
import com.lukflug.panelstudio.theme.Theme;

import me.SaberAspect.SpectClient.api.util.render.JColor;
import me.SaberAspect.SpectClient.client.module.HudModule;
import me.SaberAspect.SpectClient.client.setting.settings.ColorSetting;
import net.minecraft.client.gui.ScaledResolution;

public class KeyStrokes extends HudModule {
	public ColorSetting color = new ColorSetting("color", this, new JColor(121, 193, 255, 100)); 
    
    public KeyStrokes() {
    	super("keyStrokes","renders keystrokes", new Point(0,10), Category.HUD);
    	this.addSettings(color);
    	

    }
    
    @Override
    public void populate (Theme theme) {
    	component = new KeyStrokesComponent(theme);
    }
    
	public static enum KeyStrokesMode {
		
		WASD(Key.W, Key.A, Key.S, Key.D),
		WASD_SHFT(Key.W, Key.A, Key.S, Key.D, Key.SHFT, Key.JMP);
		
		private final Key[] keys;
		private int width;
		private int height;

		private KeyStrokesMode(Key... keysIn) {
			this.keys = keysIn;
			
			for(Key key : keys) {
				this.width = Math.max(this.width, key.getX() + key.getWidth());
				this.height = Math.max(this.height, key.getY() + key.getHeight());
			}
		}
		
		public int getHeight() {
			return height;
		}
		
		public int getWidth() {
			return width;
		}
		
		public Key[] getKeys() {
			return keys;
		}
	}
	
	private static class Key {
		
		private static final Key W = new Key("w", Minecraft.getMinecraft().gameSettings.keyBindForward, 21, 1, 18, 18);
		private static final Key A = new Key("a", Minecraft.getMinecraft().gameSettings.keyBindLeft, 1, 21, 18, 18);
		private static final Key S = new Key("s", Minecraft.getMinecraft().gameSettings.keyBindBack, 21, 21, 18, 18);
		private static final Key D = new Key("d", Minecraft.getMinecraft().gameSettings.keyBindRight, 41, 21, 18, 18);
		
		private static final Key SHFT = new Key("shft", Minecraft.getMinecraft().gameSettings.keyBindSneak, 1, 41, 28, 18);
		private static final Key JMP = new Key("jmp", Minecraft.getMinecraft().gameSettings.keyBindJump, 31, 41, 28, 18);
		
		private final int x;
		private final int y;
		private final int width;
		private final int height;
		
		public Key(String name, KeyBinding keyBind, int x, int y, int width, int height) {
			this.x = x;
			this.y = y;
			this.width = width;
			this.height = height;
		}
		
		public int getHeight() {
			return height;
		}
		
		public int getWidth() {
			return width;
		}
		
		public int getX() {
			return x;
		}
		
		public int getY() {
			return y;
		}
	}
	
	private KeyStrokesMode mode = KeyStrokesMode.WASD_SHFT;

    private class KeyStrokesComponent extends HUDComponent {

		public KeyStrokesComponent (Theme theme) {
			super(getName(),theme.getPanelRenderer(),KeyStrokes.this.position);
		}
		
		@Override
		public void render (Context context) {
			new ScaledResolution(mc);
			
			super.render(context);
			Color colors=new JColor(color.getValue(),100);
			
			GL11.glPushMatrix();
			
			boolean blend = GL11.glIsEnabled(GL11.GL_BLEND);
			
			GL11.glDisable(GL11.GL_BLEND);
			
			for(Key key : mode.getKeys()) { 
			context.getInterface().fillRect(new Rectangle(context.getPos(),new Dimension(key.getWidth(),key.getHeight())),colors,colors,colors,colors);
			}
			

			if(blend) {
				GL11.glEnable(GL11.GL_BLEND);
			}
			
			GL11.glPopMatrix();
			
		}

		@Override
		public int getWidth (Interface inter) {
			return 56;
			}

		@Override
		public void getHeight(Context context) {
			context.setHeight(54);
			
		}
	}
}
