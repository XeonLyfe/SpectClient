package me.SaberAspect.SpectClient.client.module.modules.movement;

import org.lwjgl.input.Keyboard;

import me.SaberAspect.SpectClient.client.module.Category;
import me.SaberAspect.SpectClient.client.module.Module;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiChat;
public class GuiMove extends Module {
	
	public GuiMove() {
		super ("guiMove", "lets you move while in the a gui screen.", Keyboard.KEY_NONE, Category.MOVEMENT);
	}
	
	private Minecraft mc = Minecraft.getMinecraft();

	public void onUpdate(){
		if (mc.currentScreen != null){
			if (!(mc.currentScreen instanceof GuiChat)){
				if (Keyboard.isKeyDown(200)){
					mc.player.rotationPitch -= 5;
				}
				if (Keyboard.isKeyDown(208)){
					mc.player.rotationPitch += 5;
				}
				if (Keyboard.isKeyDown(205)){
					mc.player.rotationYaw += 5;
				}
				if (Keyboard.isKeyDown(203)){
					mc.player.rotationYaw -= 5;
				}
				if (mc.player.rotationPitch > 90){
					mc.player.rotationPitch = 90;
				}
				if (mc.player.rotationPitch < -90){
					mc.player.rotationPitch = -90;
				}
			}
		}
	}
}