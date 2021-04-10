package me.SaberAspect.SpectClient.client.module.modules.render;

import org.lwjgl.input.Keyboard;

import me.SaberAspect.SpectClient.api.util.damagetilt.MessageUpdateAttackYaw;
import me.SaberAspect.SpectClient.api.util.damagetilt.PacketHandler;
import me.SaberAspect.SpectClient.client.module.Category;
import me.SaberAspect.SpectClient.client.module.Module;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingKnockBackEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class DamageTiltCorrection extends Module {
	
	public DamageTiltCorrection() {
		super ("damageTilt", "fixes minecraft's age old damage tilt bug.", Keyboard.KEY_NONE, Category.RENDER);
	}
	
	@SubscribeEvent
	public void onKnockback(LivingKnockBackEvent event) {
	    if (event.getEntityLiving() instanceof EntityPlayer) {
	    	EntityPlayer player = (EntityPlayer)event.getEntityLiving();
	    	if (player.world.isRemote)
	    		return; 
	    	PacketHandler.instance.sendTo(new MessageUpdateAttackYaw((EntityLivingBase)player), (EntityPlayerMP)player);
	    }
	}
	
	public void onEnable() {
		PacketHandler.init();
		MinecraftForge.EVENT_BUS.register(this);
	}
	
	public void onDisable() {
		MinecraftForge.EVENT_BUS.unregister(this);
	}
	
}
