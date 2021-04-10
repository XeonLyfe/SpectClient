package me.SaberAspect.SpectClient.client.module.modules.pvp;

import org.lwjgl.input.Keyboard;

import me.SaberAspect.Main;
import me.SaberAspect.SpectClient.api.event.events.NetworkPacketEvent;
import me.SaberAspect.SpectClient.client.module.Category;
import me.SaberAspect.SpectClient.client.module.Module;
import me.SaberAspect.SpectClient.client.setting.settings.ModeSetting;
import me.zero.alpine.listener.EventHandler;
import me.zero.alpine.listener.Listener;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.network.play.client.CPacketPlayer;
import net.minecraft.network.play.client.CPacketUseEntity;

public class Criticals extends Module {
	public ModeSetting mode = new ModeSetting("mode", this, "packet", "packet", "jump");
	
	public Criticals() {
		super ("criticals", "always land a critical hit.", Keyboard.KEY_NONE, Category.PVP);
		this.addSettings(mode);
	}
	
	public void onEnable() {
		Main.EVENT_BUS.subscribe(this);
	}
		
	public void onDisable() {
		Main.EVENT_BUS.unsubscribe(this);
	}

    @EventHandler
    private Listener<NetworkPacketEvent> PacketEvent = new Listener<>(event -> {
        if (event.getPacket() instanceof CPacketUseEntity) {
            CPacketUseEntity packet = (CPacketUseEntity)event.getPacket();
            
            if (packet.getAction() == CPacketUseEntity.Action.ATTACK) {
                if (packet.getEntityFromWorld(mc.world) instanceof EntityLivingBase && mc.player.onGround && !mc.gameSettings.keyBindJump.isKeyDown()) {
                	
                	if(mode.is("jump")) {
                		mc.player.jump();
                	}
                	
                	if(mode.is("packet")) {
                        mc.player.connection.sendPacket(new CPacketPlayer.Position(mc.player.posX, mc.player.posY + 0.1f, mc.player.posZ, false));
                        mc.player.connection.sendPacket(new CPacketPlayer.Position(mc.player.posX, mc.player.posY, mc.player.posZ, false));
                	}
                }
            }
        }
    });
}
