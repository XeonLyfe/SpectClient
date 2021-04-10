package me.SaberAspect.SpectClient.client.module.modules.pvp;

import me.SaberAspect.Main;
import me.SaberAspect.SpectClient.api.event.events.PacketEvent;
import me.SaberAspect.SpectClient.client.module.Category;
import me.SaberAspect.SpectClient.client.module.Module;
import me.zero.alpine.listener.EventHandler;
import me.zero.alpine.listener.Listener;
import net.minecraft.client.entity.EntityOtherPlayerMP;
import net.minecraft.network.play.client.CPacketPlayer;

import java.util.LinkedList;
import java.util.Queue;

import org.lwjgl.input.Keyboard;

public class Blink extends Module {
	  private final Queue<CPacketPlayer> packetQueue = new LinkedList<>();
	  private EntityOtherPlayerMP player;

	public Blink() {
		super ("blink", "makes temporary player clone and stuff.", Keyboard.KEY_NONE, Category.PVP);
	}
	
@EventHandler
private final Listener<PacketEvent.Send> sendListener = new Listener<>(event -> {
	if(mc.player == null || mc.world == null) return;
	
	if (event.getPacket() instanceof CPacketPlayer) {
        event.cancel();
        packetQueue.add((CPacketPlayer) event.getPacket());
    }
});

@Override
public void onEnable() {
	Main.EVENT_BUS.subscribe(this);
    player = new EntityOtherPlayerMP(mc.world, mc.getSession().getProfile());
    player.copyLocationAndAnglesFrom(mc.player);
    player.rotationYawHead = mc.player.rotationYawHead;
    mc.world.addEntityToWorld(-100, player);
}

@Override
public void onDisable() {
	Main.EVENT_BUS.unsubscribe(this);
    while (!packetQueue.isEmpty()) mc.player.connection.sendPacket(packetQueue.poll());

    if (mc.player != null)
    {
        mc.world.removeEntityFromWorld(-100);
        player = null;
    }
}
}
