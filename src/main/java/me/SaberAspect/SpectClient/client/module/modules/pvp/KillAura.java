package me.SaberAspect.SpectClient.client.module.modules.pvp;

import java.util.List;
import java.util.stream.Collectors;

import org.lwjgl.input.Keyboard;

import me.SaberAspect.SpectClient.api.util.world.EntityUtils;
import me.SaberAspect.SpectClient.client.friend.FriendManager;
import me.SaberAspect.SpectClient.client.module.Category;
import me.SaberAspect.SpectClient.client.module.Module;
import me.SaberAspect.SpectClient.client.setting.settings.BooleanSetting;
import me.SaberAspect.SpectClient.client.setting.settings.NumberSetting;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityArmorStand;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumHand;

public class KillAura extends Module {
	

    
	public BooleanSetting players = new BooleanSetting("Players", this, true);
	public BooleanSetting hostileMobs = new BooleanSetting("Mobs", this, false);
	public BooleanSetting passiveMobs = new BooleanSetting("Animals/Passives", this, false);
	public BooleanSetting armorStands = new BooleanSetting("Armor Stands", this, false);
	public BooleanSetting aimBot = new BooleanSetting("Aimbot", this, false);
	public BooleanSetting throughWalls = new BooleanSetting("ThroughWalls", this, true);
	public BooleanSetting ddelay = new BooleanSetting("1.9 Delay", this, true);
	public NumberSetting range = new NumberSetting("Range", this, 4, 1, 6, 0.1);
	public NumberSetting CPS = new NumberSetting("CPS", this, 12, 1, 20, 1);


	
	private int delay = 0;

	
	public KillAura() {
		super ("killAura", "automatically hits anything near ya.", Keyboard.KEY_NONE, Category.PVP);
		this.addSettings( players, passiveMobs, hostileMobs, armorStands, aimBot, throughWalls, ddelay, range, CPS );
	}
		@Override
		public void onUpdate() {
			if (this.isToggled()) {
				delay++;
				int reqDelay =  (int) Math.round(50/CPS.getValue());
				if (ddelay.isOn()) reqDelay = (int)Math.ceil(mc.player.getCooldownPeriod());
				
				List<Entity> targets = EntityUtils.getLoadedEntities().stream()
						.filter(e -> (e instanceof EntityPlayer && (players.isOn() && !FriendManager.isFriend(e.getName())))  || (e instanceof IMob && (hostileMobs.isOn()) || (EntityUtils.isAnimal(e) && passiveMobs.isOn())
								|| (e instanceof EntityArmorStand && armorStands.isOn())))
						.collect(Collectors.toList());
				targets.sort((a,b) -> Float.compare(a.getDistance(mc.player), b.getDistance(mc.player)));
				
				for (Entity e: targets) {
					if (mc.player.getDistance(e) > range.getValue()
							|| !e.isEntityAlive()
							|| e == mc.player || e == mc.player.getRidingEntity() || e == mc.renderViewEntity
							|| (!mc.player.canEntityBeSeen(e) && !throughWalls.isOn())) continue;
					
					if (aimBot.isOn()) EntityUtils.facePos(e.posX, e.posY+e.getEyeHeight()/2, e.posZ);
						
					if (delay > reqDelay || reqDelay == 0) {
						mc.playerController.attackEntity(mc.player, e);
						mc.player.swingArm(EnumHand.MAIN_HAND);
						delay=0;
					}
				}
			}
		}
}