package me.SaberAspect.SpectClient.client.module.modules.hud;

import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import com.lukflug.panelstudio.hud.HUDList;
import com.lukflug.panelstudio.hud.ListComponent;
import com.lukflug.panelstudio.theme.Theme;

import me.SaberAspect.Main;
import me.SaberAspect.SpectClient.api.util.render.JColor;
import me.SaberAspect.SpectClient.client.module.Category;
import me.SaberAspect.SpectClient.client.module.HudModule;
import me.SaberAspect.SpectClient.client.module.Module;
import me.SaberAspect.SpectClient.client.module.ModuleManager;
import me.SaberAspect.SpectClient.client.setting.settings.BooleanSetting;
import me.SaberAspect.SpectClient.client.setting.settings.ColorSetting;
import me.SaberAspect.SpectClient.client.setting.settings.ModeSetting;

public class ArrayLis extends HudModule {
	private ModuleArrayList list=new ModuleArrayList();
	
	public ColorSetting color = new ColorSetting("color", this, new JColor(255, 255, 255, 255));
	public ModeSetting sortHeight = new ModeSetting("sortHeight", this, "betic", "betic", "up", "down");
	public ModeSetting sortLength = new ModeSetting("sortLength", this, "left", "left", "right");
	public BooleanSetting forgeHax = new BooleanSetting("forgeHax", this, true);
	public BooleanSetting showHidden = new BooleanSetting("showHidden", this, false);

	public ArrayLis() {
		super("arrayList", "shows currently enabled modules.", new Point(-2, 69), Category.HUD);
		this.addSettings(color, sortHeight, sortLength, showHidden, forgeHax);
		toggled = true;

	}
    
    @Override
    public void populate (Theme theme) {
    	component = new ListComponent(getName(),theme.getPanelRenderer(),position,list);
    }

    public void onRender() {
    	list.activeModules.clear();
    	for (Module module: ModuleManager.getModules()) {
    		if(!showHidden.isEnabled()) {
    			if (module.isToggled() && !module.getCategory().equals(Category.HUD) && !module.getCategory().equals(Category.CLIENT)) {
    				list.activeModules.add(module);
    			}
    		}else
    			if (module.isToggled() && !module.getName().equalsIgnoreCase("Esp2dHelper")) list.activeModules.add(module);
    	}
    	if(sortHeight.is("up") || sortHeight.is("down")) {
    	list.activeModules.sort(Comparator.comparing(module -> -Main.clickGui.guiInterface.getFontWidth(module.getName())));
    	}
    }

    private class ModuleArrayList implements HUDList {

		public List<Module> activeModules=new ArrayList<Module>();
		
		@Override
		public int getSize() {
			return activeModules.size();
		}
		
		@Override
		public String getItem(int index) {
			Module module = activeModules.get(index);
			if(forgeHax.isEnabled() && sortLength.is("right")) return module.getName() + "<";
			else if(forgeHax.isEnabled() && sortLength.is("left")) return ">" + module.getName();
			else return module.getName();
		}
		
		@Override
		public Color getItemColor(int index) {
			JColor c = color.getValue();
			return Color.getHSBColor(c.getHue() + (color.getRainbow() ? .05f * index : 0), (color.getRainbow() ? 0.5f : c.getSaturation()), c.getBrightness());
		}
		
		@Override
		public boolean sortUp() {
			return sortHeight.is("up");
		}

		@Override
		public boolean sortRight() {
			return sortLength.is("right");
		}
	}
}