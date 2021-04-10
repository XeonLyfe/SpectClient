package me.SaberAspect.SpectClient.client.ui.clickgui;

import java.awt.Color;
import java.awt.Point;

import org.lwjgl.opengl.GL11;

import com.lukflug.panelstudio.CollapsibleContainer;
import com.lukflug.panelstudio.DraggableContainer;
import com.lukflug.panelstudio.FixedComponent;
import com.lukflug.panelstudio.SettingsAnimation;
import com.lukflug.panelstudio.hud.HUDClickGUI;
import com.lukflug.panelstudio.hud.HUDPanel;
import com.lukflug.panelstudio.mc12.GLInterface;
import com.lukflug.panelstudio.mc12.MinecraftHUDGUI;
import com.lukflug.panelstudio.settings.BooleanComponent;
import com.lukflug.panelstudio.settings.EnumComponent;
import com.lukflug.panelstudio.settings.KeybindComponent;
import com.lukflug.panelstudio.settings.NumberComponent;
import com.lukflug.panelstudio.settings.SimpleToggleable;
import com.lukflug.panelstudio.settings.Toggleable;
import com.lukflug.panelstudio.theme.FixedDescription;
import com.lukflug.panelstudio.theme.GameSenseTheme;
import com.lukflug.panelstudio.theme.MouseDescription;
import com.lukflug.panelstudio.theme.SettingsColorScheme;
import com.lukflug.panelstudio.theme.Theme;

import me.SaberAspect.SpectClient.api.util.font.FontUtils;
import me.SaberAspect.SpectClient.api.util.render.ColorMain;
import me.SaberAspect.SpectClient.api.util.render.JColor;
import me.SaberAspect.SpectClient.client.module.Category;
import me.SaberAspect.SpectClient.client.module.HudModule;
import me.SaberAspect.SpectClient.client.module.Module;
import me.SaberAspect.SpectClient.client.module.ModuleManager;
import me.SaberAspect.SpectClient.client.setting.Setting;
import me.SaberAspect.SpectClient.client.setting.settings.BooleanSetting;
import me.SaberAspect.SpectClient.client.setting.settings.ColorSetting;
import me.SaberAspect.SpectClient.client.setting.settings.KeybindSetting;
import me.SaberAspect.SpectClient.client.setting.settings.ModeSetting;
import me.SaberAspect.SpectClient.client.setting.settings.NumberSetting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;

public class ClickGui extends MinecraftHUDGUI {
	public static final int WIDTH=ClickGuiModule.INSTANCE.thinGui.isEnabled() ? 80 : 100,HEIGHT=12,DISTANCE=10,HUD_BORDER=2;
	private final Toggleable colorToggle;
	public final GUIInterface guiInterface;
	public final HUDClickGUI gui;
	private Theme theme;
	
	public ClickGui() {
			theme = new GameSenseTheme(new SettingsColorScheme(ClickGuiModule.INSTANCE.enabledColor, ClickGuiModule.INSTANCE.backgroundColor,ClickGuiModule.INSTANCE.settingBackgroundColor,
					ClickGuiModule.INSTANCE.outlineColor,ClickGuiModule.INSTANCE.fontColor,ClickGuiModule.INSTANCE.opacity),HEIGHT,2, 5);		
			
			colorToggle = new Toggleable() {
				@Override
				public void toggle() {
					ColorMain.colorModel.increment();
				}
				
				@Override
				public boolean isOn() {
					return ColorMain.colorModel.is("RGB");
				}
			};
		guiInterface = new GUIInterface(true) {
			@Override
			public void drawString(Point pos, String s, Color c) {
				GLInterface.end();
				int x=pos.x+2, y=pos.y+1;
				if(ModuleManager.getModuleByName("clientFont").isToggled())FontUtils.drawStringWithShadow(true,s,x,y,new JColor(c));
				else FontUtils.drawStringWithShadow(false,s,x,y,new JColor(c));
				GLInterface.begin();
			}
			
			@Override
			public int getFontWidth(String s) {
				if(ModuleManager.isModuleEnabled("clientFont")) return Math.round(FontUtils.getStringWidth(true,s))+4;
				else return Math.round(FontUtils.getStringWidth(false,s))+4;
			}

			@Override
			public int getFontHeight() {
				if(ModuleManager.isModuleEnabled("clientFont")) return Math.round(FontUtils.getFontHeight(true))+2;
				else return Math.round(FontUtils.getFontHeight(false))+2;
			}

			@Override
			protected String getResourcePrefix() {
				return "spc/textures/";
			}
		};
		gui = new HUDClickGUI(guiInterface,ClickGuiModule.INSTANCE.description.is("mouse") ? new MouseDescription(new Point(5,0)) : new FixedDescription(new Point(0,0))) {
			@Override
			public void handleScroll (int diff) {
				super.handleScroll(diff);
				if (ClickGuiModule.INSTANCE.scrollMode.is("screen")) {
					for (FixedComponent component: components) {
		        		if (!hudComponents.contains(component)) {
			        		Point p=component.getPosition(guiInterface);
			        		p.translate(0,-diff);
			        		component.setPosition(guiInterface,p);
		        		}
		        	}
				}
			}
		};
		Toggleable hudToggle=new Toggleable() {
			@Override
			public void toggle() {
				render();
			}

			@Override
			public boolean isOn() {
				return hudEditor;
			}
		};
		
		for (Module module: ModuleManager.getModules()) {
			if (module instanceof HudModule) {
				((HudModule)module).populate(theme);
				gui.addHUDComponent(new HUDPanel(((HudModule)module).getComponent(),theme.getPanelRenderer(),module,new SettingsAnimation(ClickGuiModule.INSTANCE.animationSpeed),hudToggle,HUD_BORDER));
			}
		}
		Point pos = new Point(DISTANCE,DISTANCE);
		for (Category category: Category.values()) {
			DraggableContainer panel=new DraggableContainer(category.name,null,theme.getPanelRenderer(),new SimpleToggleable(false),new SettingsAnimation(ClickGuiModule.INSTANCE.animationSpeed),null,new Point(pos),WIDTH) {
				@Override
				protected int getScrollHeight (int childHeight) {
					if (ClickGuiModule.INSTANCE.scrollMode.is("screen")) {
						return childHeight;
					}
					return Math.min(childHeight,Math.max(HEIGHT*4,ClickGui.this.height-getPosition(guiInterface).y-renderer.getHeight(open.getValue()!=0)-HEIGHT));
				}
			};
			gui.addComponent(panel);
			pos.translate(0,HEIGHT + DISTANCE);
			for (Module module: ModuleManager.getModulesInCategory(category)) {
				addModule(panel,module);
			}
		}
	}

	private void addModule (CollapsibleContainer panel, Module module) {
		CollapsibleContainer container=new CollapsibleContainer(module.getName(),module.getDescription(),theme.getContainerRenderer(),new SimpleToggleable(false),new SettingsAnimation(ClickGuiModule.INSTANCE.animationSpeed),module);
		if(!module.getName().equals("Esp2dHelper")) {
		panel.addComponent(container);
		for (Setting property: module.settings) {
			if (property instanceof BooleanSetting) {
				container.addComponent(new BooleanComponent(property.name,null,theme.getComponentRenderer(),(BooleanSetting)property));
			} else if (property instanceof NumberSetting) {
				container.addComponent(new NumberComponent(property.name,null,theme.getComponentRenderer(),(NumberSetting)property,((NumberSetting)property).getMinimun(),((NumberSetting)property).getMaximum()));
			}  else if (property instanceof ModeSetting) {
				container.addComponent(new EnumComponent(property.name,null,theme.getComponentRenderer(),(ModeSetting)property));
			}	else if (property instanceof ColorSetting) { 
				container.addComponent(new SyncableColorComponent(theme,(ColorSetting)property,colorToggle,new SettingsAnimation(ClickGuiModule.INSTANCE.animationSpeed)));
			} else if (property instanceof KeybindSetting) {
				container.addComponent(new KeybindComponent(theme.getComponentRenderer(),(KeybindSetting)property));
			}
		}
		}
	}
	
	public static void renderItem (ItemStack item, Point pos) {
		GlStateManager.enableTexture2D();
		GlStateManager.depthMask(true);
		GL11.glPushAttrib(GL11.GL_SCISSOR_BIT);
		GL11.glDisable(GL11.GL_SCISSOR_TEST);
		GlStateManager.clear(GL11.GL_DEPTH_BUFFER_BIT);
		GL11.glPopAttrib();
		GlStateManager.enableDepth();
		GlStateManager.disableAlpha();
		GlStateManager.pushMatrix();
		Minecraft.getMinecraft().getRenderItem().zLevel = -150.0f;
        RenderHelper.enableGUIStandardItemLighting();
        Minecraft.getMinecraft().getRenderItem().renderItemAndEffectIntoGUI(item,pos.x,pos.y);
        Minecraft.getMinecraft().getRenderItem().renderItemOverlays(Minecraft.getMinecraft().fontRenderer,item,pos.x,pos.y);
        RenderHelper.disableStandardItemLighting();
        Minecraft.getMinecraft().getRenderItem().zLevel = 0.0F;
        GlStateManager.popMatrix();
		GlStateManager.disableDepth();
		GlStateManager.depthMask(false);
		GLInterface.begin();
	}
	
	public static void renderEntity (EntityLivingBase entity, Point pos, int scale) {
		GlStateManager.enableTexture2D();
		GlStateManager.depthMask(true);
		GL11.glPushAttrib(GL11.GL_SCISSOR_BIT);
		GL11.glDisable(GL11.GL_SCISSOR_TEST);
		GlStateManager.clear(GL11.GL_DEPTH_BUFFER_BIT);
		GL11.glPopAttrib();
		GlStateManager.enableDepth();
		GlStateManager.disableAlpha();
        GlStateManager.pushMatrix();
        GlStateManager.color(1,1,1,1);
        GuiInventory.drawEntityOnScreen(pos.x,pos.y,scale,28,60,entity);
        GlStateManager.popMatrix();
		GlStateManager.disableDepth();
		GlStateManager.depthMask(false);
		GLInterface.begin();
	}
	
	@Override
	protected HUDClickGUI getHUDGUI() {
		return gui;
	}

	@Override
	protected GUIInterface getInterface() {
		return guiInterface;
	}

	@Override
	protected int getScrollSpeed() {
		return (int) ClickGuiModule.INSTANCE.scrolls.getValue();
	}
}