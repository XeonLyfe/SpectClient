package me.SaberAspect.SpectClient.api.mixin.mixins;

import net.minecraft.client.multiplayer.PlayerControllerMP;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import me.SaberAspect.SpectClient.client.module.ModuleManager;

@Mixin(PlayerControllerMP.class)
public abstract class MixinPlayerControllerMP {
	//author cookiedragon234
	@Inject(method = "resetBlockRemoving", at = @At("HEAD"), cancellable = true)
	private void resetBlock(CallbackInfo callbackInfo) {
		if (ModuleManager.isModuleEnabled("multitask")) {
			callbackInfo.cancel();
		}
	}
}