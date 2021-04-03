package me.SaberAspect.SpectClient.hack.hacks;

import me.SaberAspect.SpectClient.hack.Hack;
import me.SaberAspect.SpectClient.hack.HackCategory;
import me.SaberAspect.SpectClient.value.Mode;
import me.SaberAspect.SpectClient.value.types.DoubleValue;
import me.SaberAspect.SpectClient.value.types.ModeValue;
import me.SaberAspect.SpectClient.wrappers.Wrapper;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraftforge.fml.common.gameevent.TickEvent.ClientTickEvent;

public class AutoStep extends Hack{
	
	public ModeValue mode;
	public DoubleValue height;
	public float tempHeight;
	public int ticks = 0;
	
	public AutoStep() {
		super("AutoStep", HackCategory.PLAYER);
		
		this.mode = new ModeValue("Mode", new Mode("Simple", true), new Mode("AAC", false));
		height = new DoubleValue("Height", 0.5D, 0D, 10D);
		
		this.addValue(mode, height);
	}
	
	@Override
    public String getDescription() {
        return "Allows you to walk on value blocks height.";
    }
	
	@Override
	public void onEnable() {
		ticks = 0;
		super.onEnable();
	}
	
	@Override
	public void onDisable() {
		Wrapper.INSTANCE.player().stepHeight = 0.5f;
		super.onDisable();
	}
	
	@Override
	public void onClientTick(ClientTickEvent event) {
		if(mode.getMode("AAC").isToggled()) {
			EntityPlayerSP player = Wrapper.INSTANCE.player();
			if(player.collidedHorizontally) {
				switch(ticks) {
					case 0:
					if(player.onGround)
						player.jump();
						break;
					case 7:
						player.motionY = 0;
						break;
					case 8:
					if(!player.onGround)
						player.setPosition(player.posX, player.posY + 1, player.posZ);
						break;
				}
				ticks++;
			} else {
				ticks = 0;
			}
		} else if(mode.getMode("Simple").isToggled()) {
			Wrapper.INSTANCE.player().stepHeight = height.getValue().floatValue();
		}
		
		super.onClientTick(event);
	}
	
}
