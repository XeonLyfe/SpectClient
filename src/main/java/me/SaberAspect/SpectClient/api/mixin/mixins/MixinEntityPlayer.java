package me.SaberAspect.SpectClient.api.mixin.mixins;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import me.SaberAspect.Main;
import me.SaberAspect.SpectClient.api.event.events.PlayerJumpEvent;
import me.SaberAspect.SpectClient.api.event.events.WaterPushEvent;

@Mixin(EntityPlayer.class)
public abstract class MixinEntityPlayer {

	@Shadow public abstract String getName();

	@Inject(method = "jump", at = @At("HEAD"), cancellable = true)
	public void onJump(CallbackInfo callbackInfo) {
		if (Minecraft.getMinecraft().player.getName() == this.getName()) {
			Main.EVENT_BUS.post(new PlayerJumpEvent());
		}
	}

	@Inject(method = "isPushedByWater", at = @At("HEAD"), cancellable = true)
	private void onPushedByWater(CallbackInfoReturnable<Boolean> callbackInfoReturnable) {
		WaterPushEvent event = new WaterPushEvent();
		Main.EVENT_BUS.post(event);
		if (event.isCancelled()) {
			callbackInfoReturnable.setReturnValue(false);
		}
	}
}