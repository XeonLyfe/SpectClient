package me.SaberAspect.SpectClient.gui.click.theme.dark;

import java.awt.Color;

import org.lwjgl.input.Keyboard;

import me.SaberAspect.SpectClient.gui.click.base.Component;
import me.SaberAspect.SpectClient.gui.click.base.ComponentRenderer;
import me.SaberAspect.SpectClient.gui.click.base.ComponentType;
import me.SaberAspect.SpectClient.gui.click.elements.KeybindMods;
import me.SaberAspect.SpectClient.gui.click.theme.Theme;
import me.SaberAspect.SpectClient.hack.hacks.ClickGui;
import me.SaberAspect.SpectClient.utils.visual.ColorUtils;
import me.SaberAspect.SpectClient.utils.visual.GLUtils;
import me.SaberAspect.SpectClient.utils.visual.RenderUtils;

public class DarkKeybinds extends ComponentRenderer {

    public DarkKeybinds(Theme theme) {

        super(ComponentType.KEYBIND, theme);
    }

    @Override
    public void drawComponent(Component component, int mouseX, int mouseY) {

        KeybindMods keybind = (KeybindMods) component;
        
        int mainColor = ClickGui.isLight ? ColorUtils.color(255, 255, 255, 55) : ColorUtils.color(0, 0, 0, 55);
        int mainColorInv = ClickGui.isLight ? ColorUtils.color(0, 0, 0, 255) : ColorUtils.color(255, 255, 255, 255);
        
        theme.fontRenderer.drawString("Key", keybind.getX() + 4, keybind.getY() + 2, 
        		mainColorInv);
        
        int nameWidth = theme.fontRenderer.getStringWidth("Key") + 7;
        
        RenderUtils.drawRect(keybind.getX() + nameWidth, keybind.getY(), keybind.getX() + keybind.getDimension().width, keybind.getY() + 12, 
        		mainColor);
        
        if(keybind.getMod().getKey() == -1) {
        	theme.fontRenderer.drawString(keybind.isEditing() ? "|" : "NONE", keybind.getX() + keybind.getDimension().width / 2 + nameWidth / 2 - theme.fontRenderer.getStringWidth("NONE") / 2, keybind.getY() + 2, keybind.isEditing() ? 
        			mainColorInv : 
        				ColorUtils.color(0.4f, 0.4f, 0.4f, 1.0f));
        }
        else
        {
        	theme.fontRenderer.drawString(keybind.isEditing() ? "|" : Keyboard.getKeyName(keybind.getMod().getKey()), keybind.getX() + keybind.getDimension().width / 2 + nameWidth / 2 - theme.fontRenderer.getStringWidth(Keyboard.getKeyName(keybind.getMod().getKey())) / 2, keybind.getY() + 2, keybind.isEditing() ? 
        			mainColorInv : 
        				mainColorInv);
        }
    }

    @Override
    public void doInteractions(Component component, int mouseX, int mouseY) {

    }

}
