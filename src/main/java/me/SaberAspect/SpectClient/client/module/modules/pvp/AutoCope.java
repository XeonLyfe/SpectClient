package me.SaberAspect.SpectClient.client.module.modules.pvp;

import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

import org.lwjgl.input.Keyboard;

import me.SaberAspect.Main;
import me.SaberAspect.SpectClient.api.event.events.PacketEvent;
import me.SaberAspect.SpectClient.client.module.Category;
import me.SaberAspect.SpectClient.client.module.Module;
import me.SaberAspect.SpectClient.client.setting.settings.BooleanSetting;
import me.SaberAspect.SpectClient.client.setting.settings.ModeSetting;
import me.zero.alpine.listener.EventHandler;
import me.zero.alpine.listener.Listener;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.play.client.CPacketChatMessage;
import net.minecraft.network.play.client.CPacketUseEntity;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingDeathEvent;

public class AutoCope extends Module {
	public ModeSetting msg = new ModeSetting("msg", this, "cope&seethe", "Penis", "too easy", "you suck ballz", "gg");
	public BooleanSetting greenText = new BooleanSetting("greenText", this, true);
	
	public AutoCope() {
		super("MessageAfterKill", "Displays a message after you kill someone.", Keyboard.KEY_NONE, Category.PVP);
		this.addSettings(msg, greenText);
	}
	int delay = 0;
    private static final ConcurrentHashMap<Object, Integer> targetedPlayers = new ConcurrentHashMap<Object, Integer>();

    @Override
    public void onEnable() {
    	Main.EVENT_BUS.subscribe(this);
    	MinecraftForge.EVENT_BUS.register(this);
    }
    
    @Override
    public void onDisable() {
    	Main.EVENT_BUS.unsubscribe(this);
    	MinecraftForge.EVENT_BUS.unregister(this);
    }

    @Override
    public void onUpdate() {

        for (Entity entity : mc.world.getLoadedEntityList()) {
            if (entity instanceof EntityPlayer) {
            	EntityPlayer player = (EntityPlayer) entity;
                if (player.getHealth() <= 0) {
                    if (targetedPlayers.containsKey(player.getName())) {
                        announce(player.getName());
                    }
                }
            }
        }

        targetedPlayers.forEach((name, timeout) -> {
            if ((int)timeout <= 0) {
            	targetedPlayers.remove(name);
            } else {
            	targetedPlayers.put(name, (int)timeout - 1);
            }

        });

        delay++;

    } 
    
    @EventHandler
    private Listener<PacketEvent.Send> sendListener = new Listener<>(event -> {

        if (mc.player == null) return;

        if (event.getPacket() instanceof CPacketUseEntity) {
            CPacketUseEntity cPacketUseEntity = (CPacketUseEntity) event.getPacket();
            if (cPacketUseEntity.getAction().equals(CPacketUseEntity.Action.ATTACK)) {
                Entity targetEntity = cPacketUseEntity.getEntityFromWorld(mc.world);
                if (targetEntity instanceof EntityPlayer) {
                    addTarget(targetEntity.getName());
                }
            }
        }

    });

    @EventHandler
    private Listener<LivingDeathEvent> livingDeathListener = new Listener<>(event -> {

        if (mc.player == null) return;

        EntityLivingBase e = event.getEntityLiving();
        if (e == null) return;

        if (e instanceof EntityPlayer) {
        	EntityPlayer player = (EntityPlayer) e;

            if (player.getHealth() <= 0) {
                if (targetedPlayers.containsKey(player.getName())) {
                    announce(player.getName());
                }
            }
        }

    });

    public void announce(String name) {
        if (delay < 150) {
            return;
        }
        delay = 0;
        targetedPlayers.remove(name);
        
        String starter = "";
        if(greenText.isEnabled()) starter = "> ";

        String message = "";
        if(msg.is("Penis")) message = starter + "You...Like...Penis";
        if(msg.is("you suck ballz")) message = starter + "wowowow u suck ballz, SpectClient Rise up!";
        if(msg.is("too easy")) message = starter + "ez pz lemon squeeez";
        if(msg.is("gg")) message = starter + "gg, you did your best";
        
        mc.player.connection.sendPacket(new CPacketChatMessage(message));
    }

    public static void addTarget(String name) {
        if (!Objects.equals(name, mc.player.getName())) {
            targetedPlayers.put(name, 20);
        }
    }
	
}
