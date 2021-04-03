package me.SaberAspect.SpectClient.hack.hacks;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import me.SaberAspect.SpectClient.hack.Hack;
import me.SaberAspect.SpectClient.hack.HackCategory;
import me.SaberAspect.SpectClient.utils.Utils;
import me.SaberAspect.SpectClient.utils.ValidUtils;
import me.SaberAspect.SpectClient.utils.system.Mapping;
import me.SaberAspect.SpectClient.utils.visual.ChatUtils;
import me.SaberAspect.SpectClient.value.types.BooleanValue;
import me.SaberAspect.SpectClient.value.types.DoubleValue;
import me.SaberAspect.SpectClient.wrappers.Wrapper;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityArmorStand;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.gameevent.TickEvent.ClientTickEvent;

public class HitBox extends Hack{
	
	public DoubleValue width;
	public DoubleValue height;
	
	public HitBox() {
		super("HitBox", HackCategory.COMBAT);
		
		this.width = new DoubleValue("Width", 0.6D, 0.1D, 5.0D);
		this.height = new DoubleValue("Height", 1.8D, 0.1D, 5.0D);
		
		this.addValue(width, height);
	}
	
	@Override
	public String getDescription() {
		return "Change size hit box of players.";
	}
	
	@Override
	public void onClientTick(ClientTickEvent event) {
		for(EntityPlayer player : Utils.getPlayersList()) {
			if(!check(player)) continue;
			float width = (float)(this.width.getValue().floatValue());
			float height = (float)(this.height.getValue().floatValue());
			Utils.setEntityBoundingBoxSize(player, width, height);
		}
		super.onClientTick(event);
	}
	
	@Override
	public void onDisable() {
		for(EntityPlayer player : Utils.getPlayersList())
			Utils.setEntityBoundingBoxSize(player);
		super.onDisable();
	}
	
	public boolean check(EntityLivingBase entity) {
		if(entity instanceof EntityPlayerSP) { return false; }
		if(entity == Wrapper.INSTANCE.player()) { return false; }
		if(entity.isDead) { return false; }
		if(!ValidUtils.isFriendEnemy(entity)) { return false; }
		if(!ValidUtils.isTeam(entity)) { return false; }
		return true;
    }
}