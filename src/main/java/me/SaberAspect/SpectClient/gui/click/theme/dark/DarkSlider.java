package me.SaberAspect.SpectClient.gui.click.theme.dark;

import java.awt.Color;

import me.SaberAspect.SpectClient.gui.click.base.Component;
import me.SaberAspect.SpectClient.gui.click.base.ComponentRenderer;
import me.SaberAspect.SpectClient.gui.click.base.ComponentType;
import me.SaberAspect.SpectClient.gui.click.elements.Slider;
import me.SaberAspect.SpectClient.gui.click.theme.Theme;
import me.SaberAspect.SpectClient.hack.hacks.ClickGui;
import me.SaberAspect.SpectClient.utils.visual.ColorUtils;
import me.SaberAspect.SpectClient.utils.visual.GLUtils;
import me.SaberAspect.SpectClient.utils.visual.RenderUtils;

public class DarkSlider extends ComponentRenderer {

    public DarkSlider(Theme theme) {

        super(ComponentType.SLIDER, theme);
    }

    @Override
    public void drawComponent(Component component, int mouseX, int mouseY) {

        Slider slider = (Slider) component;
        int width = (int) ((slider.getDimension().getWidth()) * slider.getPercent());
        
        int mainColor = ClickGui.isLight ? ColorUtils.color(255, 255, 255, 255) : ColorUtils.color(0, 0, 0, 255);
        int mainColorInv = ClickGui.isLight ? ColorUtils.color(0, 0, 0, 255) : ColorUtils.color(255, 255, 255, 255);
        int strColor = ClickGui.isLight ? ColorUtils.color(0.3f, 0.3f, 0.3f, 1.0f) : ColorUtils.color(0.5f, 0.5f, 0.5f, 1.0f);
        
        String value = slider.getRenderValue();
        
        //GLUtils.glColor(ColorUtils.color(1.0f, 1.0f, 1.0f, 1.0f));
        
        theme.fontRenderer.drawString(slider.getText(), slider.getX() + 4, slider.getY() + 2, 
        		strColor);
        
        theme.fontRenderer.drawString(value, slider.getX() + slider.getDimension().width - theme.fontRenderer.getStringWidth(value) - 2, slider.getY() + 2, 
        		mainColorInv);
        
        RenderUtils.drawRect(slider.getX(), slider.getY() + slider.getDimension().height / 2 + 3, slider.getX() + (width) + 3, (slider.getY() + slider.getDimension().height / 2) + 6, 
        		mainColorInv);
        
        RenderUtils.drawRect(slider.getX(), slider.getY() + slider.getDimension().height / 2 + 3, slider.getX() + (width), (slider.getY() + slider.getDimension().height / 2) + 6, 
        		ClickGui.getColor());
    }

    @Override
    public void doInteractions(Component component, int mouseX, int mouseY) {

    }
}
