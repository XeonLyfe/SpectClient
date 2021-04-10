package me.SaberAspect.SpectClient.api.mixin.mixins;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.multiplayer.PlayerControllerMP;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import me.SaberAspect.SpectClient.api.mixin.mixins.accessor.AccessorEntityPlayerSP;
import me.SaberAspect.SpectClient.client.module.ModuleManager;

@Mixin(value = Minecraft.class)
public class MixinMinecraft {

	@Shadow public EntityPlayerSP player;
	@Shadow public PlayerControllerMP playerController;

	private boolean handActive = false;
	private boolean isHittingBlock = false;

	@Inject(method = "rightClickMouse", at = @At("HEAD"))
	public void rightClickMousePre(CallbackInfo ci) {
		if (ModuleManager.isModuleEnabled("multitask")) {
			isHittingBlock = playerController.getIsHittingBlock();
			playerController.isHittingBlock = false;
		}
	}

	@Inject(method = "rightClickMouse", at = @At("RETURN"))
	public void rightClickMousePost(CallbackInfo ci) {
		if (ModuleManager.isModuleEnabled("multitask") && !playerController.getIsHittingBlock()) {
			playerController.isHittingBlock = isHittingBlock;
		}
	}

	@Inject(method = "sendClickBlockToController", at = @At("HEAD"))
	public void sendClickBlockToControllerPre(boolean leftClick, CallbackInfo ci) {
		if (ModuleManager.isModuleEnabled("multitask")) {
			handActive = player.isHandActive();
			((AccessorEntityPlayerSP) player).gsSetHandActive(false);
		}
	}

	@Inject(method = "sendClickBlockToController", at = @At("RETURN"))
	public void sendClickBlockToControllerPost(boolean leftClick, CallbackInfo ci) {
		if (ModuleManager.isModuleEnabled("multitask") && !player.isHandActive()) {
			((AccessorEntityPlayerSP) player).gsSetHandActive(handActive);
		}
	}
}
