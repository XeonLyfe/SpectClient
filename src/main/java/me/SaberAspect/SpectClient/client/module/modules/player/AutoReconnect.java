package me.SaberAspect.SpectClient.client.module.modules.player;

import org.lwjgl.input.Keyboard;

import me.SaberAspect.Main;
import me.SaberAspect.SpectClient.api.event.Event.Era;
import me.SaberAspect.SpectClient.api.event.events.PacketEvent;
import me.SaberAspect.SpectClient.api.util.world.JTimer;
import me.SaberAspect.SpectClient.client.module.Category;
import me.SaberAspect.SpectClient.client.module.Module;
import me.SaberAspect.SpectClient.client.setting.settings.NumberSetting;
import me.zero.alpine.listener.EventHandler;
import me.zero.alpine.listener.Listener;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.GuiConnecting;
import net.minecraft.network.EnumConnectionState;
import net.minecraft.network.handshake.client.C00Handshake;

public class AutoReconnect extends Module {
	public NumberSetting delay = new NumberSetting("delay", this, 5, 1, 20, 1);
	
	public AutoReconnect() {
		super("autoReconnect", "automatically reconnects to a server.", Keyboard.KEY_NONE, Category.PLAYER);
		this.addSettings(delay);
	}
	private String lastIp;
    private int lastPort;
    private boolean reconnect;
    private JTimer timer = new JTimer();
	
	@EventHandler
	private final Listener<PacketEvent.Send> sendPacketEventPre = new Listener<>(event -> {
		if(event.getEra() == Era.PRE) {
			if(event.getPacket() instanceof C00Handshake) {
				final C00Handshake packet = (C00Handshake) event.getPacket();
				if(packet.getRequestedState() == EnumConnectionState.LOGIN) {
					this.lastIp = packet.ip;
                    this.lastPort = packet.port;
				}
			}
		}
		if(event.getEra() == Era.POST) {
			if (this.lastIp != null && this.lastPort > 0 && this.reconnect) {
                if (this.timer.hasReached((long) delay.getValue())) {
                    Minecraft.getMinecraft().displayGuiScreen(new GuiConnecting(null, Minecraft.getMinecraft(), this.lastIp, this.lastPort));
                    this.timer.reset();
                    this.reconnect = false;
                }
            }
		}
	});
	
	public void onEnable() {
		Main.EVENT_BUS.subscribe(this);
	}
	
	public void onDisbale() {
		Main.EVENT_BUS.unsubscribe(this);
	}

}