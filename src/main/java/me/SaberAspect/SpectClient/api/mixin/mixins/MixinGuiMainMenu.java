package me.SaberAspect.SpectClient.api.mixin.mixins;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import me.SaberAspect.Reference;
import me.SaberAspect.SpectClient.api.util.Wrapper;
import me.SaberAspect.SpectClient.client.module.ModuleManager;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.util.text.TextFormatting;

@Mixin({GuiMainMenu.class})
public class MixinGuiMainMenu extends GuiScreen {
	@Inject(method = {"drawScreen"}, at = {@At("TAIL")}, cancellable = true)
	public void drawText(int mouseX, int mouseY, float partialTicks, CallbackInfo ci) {
		if(ModuleManager.getModuleByName("mainMenuWatermark").isToggled()) {
			ScaledResolution sr = new ScaledResolution(Wrapper.getMinecraft());
			FontRenderer fr = mc.fontRenderer;
		    fr.drawStringWithShadow(TextFormatting.ITALIC + Reference.NAME + TextFormatting.WHITE + " by" + TextFormatting.GRAY + "" + TextFormatting.ITALIC + " SaberAspect", 2, 2, 0xff0000); 
		    fr.drawStringWithShadow(TextFormatting.WHITE + "ur on version " + TextFormatting.BLUE + TextFormatting.ITALIC + Reference.VERSION + TextFormatting.RESET + TextFormatting.WHITE + "!",  sr.getScaledWidth() - 100, 2, 0xff79c2ec);
		}
	}
}