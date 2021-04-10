package me.SaberAspect.SpectClient.api.mixin.mixins;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiChat;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.util.MovementInput;
import net.minecraft.util.MovementInputFromOptions;
import org.lwjgl.input.Keyboard;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import me.SaberAspect.Main;
import me.SaberAspect.SpectClient.api.event.events.PlayerUpdateMoveStateEvent;
import me.SaberAspect.SpectClient.client.module.ModuleManager;
import me.SaberAspect.SpectClient.client.module.modules.movement.GuiMove;

@Mixin(value = MovementInputFromOptions.class, priority = 10000)
public abstract class MixinMovementInputFromOptions extends MovementInput {
	
	@Inject(method = "updatePlayerMoveState", at = @At("RETURN"))
    public void updatePlayerMoveStateReturn(CallbackInfo callback) {
        Main.EVENT_BUS.post(new PlayerUpdateMoveStateEvent());
    }

	@Redirect(method = "updatePlayerMoveState", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/settings/KeyBinding;isKeyDown()Z"))
	public boolean isKeyPressed(KeyBinding keyBinding) {
		if (ModuleManager.isModuleEnabled("guiMove") && ((GuiMove)ModuleManager.getModuleByName("guiMove")).isToggled()
				&& Minecraft.getMinecraft().currentScreen != null
				&& !(Minecraft.getMinecraft().currentScreen instanceof GuiChat)
				&& Minecraft.getMinecraft().player != null) {
			return Keyboard.isKeyDown(keyBinding.getKeyCode());
		}
		return keyBinding.isKeyDown();
	}
}
