package me.SaberAspect.SpectClient.api.mixin.mixins;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(Render.class)
abstract class MixinRender<T extends Entity> {

    @Shadow
    protected abstract boolean bindEntityTexture(T entity);
}