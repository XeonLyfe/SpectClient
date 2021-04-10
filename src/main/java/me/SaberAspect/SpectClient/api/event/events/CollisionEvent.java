package me.SaberAspect.SpectClient.api.event.events;

import me.SaberAspect.SpectClient.api.event.Event;
import net.minecraft.entity.Entity;

public final class CollisionEvent extends Event {
    private final Entity entity;

    public CollisionEvent(Entity entity)
    {
        this.entity = entity;
    }

    public Entity getEntity()
    {
        return entity;
    }
}