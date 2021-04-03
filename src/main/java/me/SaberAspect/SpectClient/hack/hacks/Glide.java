package me.SaberAspect.SpectClient.hack.hacks;

import me.SaberAspect.SpectClient.hack.Hack;
import me.SaberAspect.SpectClient.hack.HackCategory;
import me.SaberAspect.SpectClient.managers.HackManager;
import me.SaberAspect.SpectClient.utils.Utils;

import me.SaberAspect.SpectClient.utils.TimerUtils;
import me.SaberAspect.SpectClient.value.Mode;
import me.SaberAspect.SpectClient.value.types.BooleanValue;
import me.SaberAspect.SpectClient.value.types.ModeValue;
import me.SaberAspect.SpectClient.wrappers.Wrapper;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.network.play.client.CPacketPlayer;
import net.minecraft.util.EnumHand;
import net.minecraftforge.fml.common.gameevent.TickEvent.ClientTickEvent;

public class Glide extends Hack{

	public BooleanValue damage;
	public ModeValue mode;
	
    static int tick;
    static boolean fall;
    static int times;
	
	TimerUtils timer;
	
	public Glide() {
		super("Glide", HackCategory.PLAYER);
		
		damage = new BooleanValue("SelfDamage", false);
		this.mode = new ModeValue("Mode", new Mode("Falling", true), new Mode("Flat", false));
		
		this.addValue(mode, damage);
				
		timer = new TimerUtils();
	}
	
	@Override
	public String getDescription() {
		return "Makes you glide down slowly when falling.";
	}
	
	@Override
	public void onEnable() {
		if(damage.getValue())
			HackManager.getHack("SelfDamage").toggle();
		super.onEnable();
	}
	
	@Override
	public void onClientTick(ClientTickEvent event) {
		EntityPlayerSP player = Wrapper.INSTANCE.player();
		if(mode.getMode("Flat").isToggled()) {
			if (!player.capabilities.isFlying && player.fallDistance > 0.0f && !player.isSneaking()) {
				player.motionY = 0.0;
        	}
        	if (Wrapper.INSTANCE.mcSettings().keyBindSneak.isKeyDown()) {
        		player.motionY = -0.11;
        	}
        	if (Wrapper.INSTANCE.mcSettings().keyBindJump.isKeyDown()) {
        		player.motionY = 0.11;
        	}
        	if (timer.delay(50)) {
        		player.onGround = false;
        		timer.setLastMS();
        	}
		}
		else if(mode.getMode("Falling").isToggled())
		{
			if (player.onGround) {
                times = 0;
            }
            if (player.fallDistance > 0.0f && times <= 1) {
                if (tick > 0 && fall) {
                	player.motionY = 0.0;
                    tick = 0;
                } else {
                    ++tick;
                }
                if (player.fallDistance >= 0.1) {
                    fall = false;
                }
                if (player.fallDistance >= 0.4) {
                    fall = true;
                    player.fallDistance = 0.0f;
                    
                }
            }
		}
		super.onClientTick(event);
	}

}
