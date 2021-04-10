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
import me.SaberAspect.SpectClient.client.setting.settings.ColorSetting;
import me.SaberAspect.SpectClient.client.setting.settings.ModeSetting;
import me.SaberAspect.SpectClient.client.ui.clickgui.ClickGui;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;

public class InventoryViewer extends HudModule {
	public ColorSetting color = new ColorSetting("color", this, new JColor(121, 193, 255, 100)); 
	public ModeSetting sizee = new ModeSetting("size", this, "normal", "normal", "compact");
    
    public InventoryViewer() {
    	super("inventoryViewer","shows your inventory on the hud.", new Point(122, 2), Category.HUD);
    	this.addSettings(color, sizee);
    	
		toggled = true;

    }
    
    @Override
    public void populate (Theme theme) {
    	component = new InventoryViewerComponent(theme);
    }

    private class InventoryViewerComponent extends HUDComponent {

		public InventoryViewerComponent (Theme theme) {
			super(getName(),theme.getPanelRenderer(),InventoryViewer.this.position);
		}
		
		@Override
		public void render (Context context) {
			
			super.render(context);
			Color bgcolor=new JColor(color.getValue(),100);
			context.getInterface().fillRect(context.getRect(),bgcolor,bgcolor,bgcolor,bgcolor);
		
	        NonNullList<ItemStack> items = Minecraft.getMinecraft().player.inventory.mainInventory;
	        for (int size = items.size(), item = 9; item < size; ++item) {
	        	if(sizee.is("normal")) {
	            int slotX = context.getPos().x + 1 + item % 9 * 18;
	            int slotY = context.getPos().y + 1 + (item / 9 - 1) * 18;
				ClickGui.renderItem(items.get(item),new Point(slotX,slotY));
	        	}else {
	        		int slotX = context.getPos().x + 1 + item % 9 * 17;
		            int slotY = context.getPos().y + 1 + (item / 9 - 1) * 17;
					ClickGui.renderItem(items.get(item),new Point(slotX,slotY));
	        	}
	        }
		}

		@Override
		public int getWidth (Interface inter) {
			if(sizee.is("normal")) {
			return 162;
			}else {
				return 154;
			}
		}

		@Override
		public void getHeight (Context context) {
			if(sizee.is("normal")) {
			context.setHeight(54);
			}else {
				context.setHeight(52);
			}
		}
	}
}