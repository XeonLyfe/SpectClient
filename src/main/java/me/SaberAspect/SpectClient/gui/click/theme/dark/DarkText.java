package me.SaberAspect.SpectClient.gui.click.theme.dark;

import me.SaberAspect.SpectClient.gui.click.base.Component;
import me.SaberAspect.SpectClient.gui.click.base.ComponentRenderer;
import me.SaberAspect.SpectClient.gui.click.base.ComponentType;
import me.SaberAspect.SpectClient.gui.click.elements.Text;
import me.SaberAspect.SpectClient.gui.click.theme.Theme;


public class DarkText extends ComponentRenderer {

    public DarkText(Theme theme) {
        super(ComponentType.TEXT, theme);
    }

    @Override
    public void drawComponent(Component component, int mouseX, int mouseY) {
        Text text = (Text) component;
        String[] message = text.getMessage();

        int y = text.getY();

        for (String s : message) {
            theme.fontRenderer.drawString(s, text.getX() - 4, y - 4, -1);
            y += 10;
        }
    }

    @Override
    public void doInteractions(Component component, int mouseX, int mouseY) {}
}
