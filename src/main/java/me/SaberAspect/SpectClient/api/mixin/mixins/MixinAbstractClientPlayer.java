package me.SaberAspect.SpectClient.api.mixin.mixins;

import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.network.NetworkPlayerInfo;
import net.minecraft.util.ResourceLocation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import me.SaberAspect.Main;
import me.SaberAspect.Reference;
import me.SaberAspect.SpectClient.client.module.ModuleManager;

import java.util.UUID;

import javax.annotation.Nullable;

@Mixin(AbstractClientPlayer.class)
public abstract class MixinAbstractClientPlayer {

	@Shadow @Nullable protected abstract NetworkPlayerInfo getPlayerInfo();

	@Inject(method = "getLocationCape", at = @At("HEAD"), cancellable = true)
	public void getLocationCape(CallbackInfoReturnable<ResourceLocation> callbackInfoReturnable) {
		UUID uuid = getPlayerInfo().getGameProfile().getId();
		if (ModuleManager.isModuleEnabled("capes") && Main.cape.hasCape(uuid)) {
			callbackInfoReturnable.setReturnValue(new ResourceLocation(Reference.MOD_ID, "textures/spectclient-cape.png"));
		}
	}
}