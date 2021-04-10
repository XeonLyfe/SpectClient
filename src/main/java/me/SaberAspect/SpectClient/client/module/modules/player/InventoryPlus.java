package me.SaberAspect.SpectClient.client.module.modules.player;

import org.lwjgl.input.Keyboard;

import me.SaberAspect.Main;
import me.SaberAspect.SpectClient.api.event.events.PacketEvent;
import me.SaberAspect.SpectClient.client.module.Category;
import me.SaberAspect.SpectClient.client.module.Module;
import me.zero.alpine.listener.EventHandler;
import me.zero.alpine.listener.Listener;
import net.minecraft.client.Minecraft;
import net.minecraft.network.play.client.CPacketCloseWindow;

public class InventoryPlus extends Module {
	
	public InventoryPlus() {
		super ("inventoryPlus", "lets you hold extra items in your crafting gui.", Keyboard.KEY_NONE, Category.PLAYER);
	}
	
	public void onEnable() {
		Main.EVENT_BUS.subscribe(this);
	}
	
	public void onDisable() {
		Main.EVENT_BUS.unsubscribe(this);
	}
	
	 @EventHandler
	 private final Listener<PacketEvent.Send> listener = new Listener<>(event -> {
		 if (event.getPacket() instanceof CPacketCloseWindow) {
	         final CPacketCloseWindow packet = (CPacketCloseWindow) event.getPacket();
	         if (packet.windowId == Minecraft.getMinecraft().player.inventoryContainer.windowId) {
	             event.cancel();
	         }
		 }
	 });
}