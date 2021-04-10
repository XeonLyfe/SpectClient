package me.SaberAspect.SpectClient.api.util.world;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntityGolem;
import net.minecraft.entity.passive.EntityAmbientCreature;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.passive.EntityWaterMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.MathHelper;

public class EntityUtils {

	private static Minecraft mc = Minecraft.getMinecraft();
	
	public static List<Entity> getLoadedEntities() {
		return mc.world.getEntitiesWithinAABBExcludingEntity(
				null, new AxisAlignedBB(
						mc.player.posX - 128, 0,
						mc.player.posZ - 128,
						mc.player.posX + 128, 256,
						mc.player.posZ + 128));
	}
	
	public static List<EntityPlayer> getPlayerEntites() {
		List<EntityPlayer> entities = new ArrayList<>();
		for (Entity e: getLoadedEntities()) if (e instanceof EntityPlayer) entities.add((EntityPlayer)e);
		return entities;
	}
	
	public static boolean isAnimal(Entity e) {
		return e instanceof EntityAnimal || e instanceof EntityAmbientCreature || e instanceof EntityWaterMob ||
				e instanceof EntityGolem || e instanceof EntityVillager;
	}

	
	
	public static void facePos(double x, double y, double z) {
		double diffX = x - mc.player.posX;
		double diffY = y - (mc.player.posY + mc.player.getEyeHeight());
		double diffZ = z - mc.player.posZ;
			
		double diffXZ = Math.sqrt(diffX * diffX + diffZ * diffZ);
			
		float yaw = (float)Math.toDegrees(Math.atan2(diffZ, diffX)) - 90F;
		float pitch = (float)-Math.toDegrees(Math.atan2(diffY, diffXZ));
			
		mc.player.rotationYaw += MathHelper.wrapDegrees(yaw - mc.player.rotationYaw);
		mc.player.rotationPitch += MathHelper.wrapDegrees(pitch - mc.player.rotationPitch);
	}
	
	public static int getDirectionFacing(Entity e) {
		int yaw = (int)e.rotationYaw;
		
		if (yaw > 180) while(yaw > 180) yaw -= 360; 
		else if (yaw < -180) while(yaw < -180) yaw += 360;
		
		if (yaw >= 135 || yaw <= -135) return 0;
		if (yaw <= -45 && yaw >= -135) return 1;
		if (yaw <= 45 && yaw >= -45) return 2;
		if (yaw <= 135 && yaw >= 45) return 3;
		
		return 0;
	}
	
}