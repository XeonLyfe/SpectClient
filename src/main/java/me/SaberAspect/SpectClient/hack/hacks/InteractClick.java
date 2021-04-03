package me.SaberAspect.SpectClient.hack.hacks;

import org.lwjgl.input.Mouse;

import me.SaberAspect.SpectClient.hack.Hack;
import me.SaberAspect.SpectClient.hack.HackCategory;
import me.SaberAspect.SpectClient.managers.EnemyManager;
import me.SaberAspect.SpectClient.managers.FriendManager;
import me.SaberAspect.SpectClient.managers.HackManager;
import me.SaberAspect.SpectClient.managers.XRayManager;

import me.SaberAspect.SpectClient.utils.Utils;
import me.SaberAspect.SpectClient.wrappers.Wrapper;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityArmorStand;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraftforge.fml.common.gameevent.TickEvent.ClientTickEvent;

public class InteractClick extends Hack{

	public InteractClick() {
		super("InteractClick", HackCategory.COMBAT);
	}
	
	@Override
	public String getDescription() {
		return "Left - Add to Enemys, Rigth - Add to Friends, Wheel - Remove from All.";
	}
	
	@Override
	public void onClientTick(ClientTickEvent event) {
		RayTraceResult object = Wrapper.INSTANCE.mc().objectMouseOver;
		if(object == null) {
			return;
		}
		if(object.typeOfHit == RayTraceResult.Type.ENTITY) {
			Entity entity = object.entityHit;
			if(entity instanceof EntityPlayer && !(entity instanceof EntityArmorStand) && !Wrapper.INSTANCE.player().isDead && Wrapper.INSTANCE.player().canEntityBeSeen(entity)){
				EntityPlayer player = (EntityPlayer)entity;
				String ID = Utils.getPlayerName(player);
				if(Mouse.isButtonDown(1) && Wrapper.INSTANCE.mc().currentScreen == null) 
				{
					FriendManager.addFriend(ID);
				}
				else if(Mouse.isButtonDown(0) && Wrapper.INSTANCE.mc().currentScreen == null) 
				{
					EnemyManager.addEnemy(ID);
				}
				else if(Mouse.isButtonDown(2) && Wrapper.INSTANCE.mc().currentScreen == null) 
				{
					EnemyManager.removeEnemy(ID);
					FriendManager.removeFriend(ID);
				}
			}
    	}
		super.onClientTick(event);
	}

}
