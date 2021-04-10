package me.SaberAspect.SpectClient.api.mixin.mixins;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.At;

import me.SaberAspect.Main;
import me.SaberAspect.SpectClient.api.event.events.TransformSideFirstPersonEvent;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.util.EnumHandSide;

@Mixin(ItemRenderer.class)
public class MixinItemRenderer {

	@Inject(method = "transformSideFirstPerson", at = @At("HEAD"))
	public void transformSideFirstPerson(EnumHandSide hand, float p_187459_2_, CallbackInfo callbackInfo) {
		TransformSideFirstPersonEvent event = new TransformSideFirstPersonEvent(hand);
		Main.EVENT_BUS.post(event);
	}

	@Inject(method = "transformFirstPerson", at = @At("HEAD"))
	public void transformFirstPerson(EnumHandSide hand, float p_187453_2_, CallbackInfo callbackInfo) {
		TransformSideFirstPersonEvent event = new TransformSideFirstPersonEvent(hand);
		Main.EVENT_BUS.post(event);
	}

}