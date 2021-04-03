package me.SaberAspect.SpectClient.hack.hacks;

import org.lwjgl.opengl.GL11;

import me.SaberAspect.SpectClient.hack.Hack;
import me.SaberAspect.SpectClient.hack.HackCategory;
import me.SaberAspect.SpectClient.managers.EnemyManager;
import me.SaberAspect.SpectClient.managers.FriendManager;
import me.SaberAspect.SpectClient.managers.HackManager;
import me.SaberAspect.SpectClient.utils.Utils;
import me.SaberAspect.SpectClient.utils.visual.RenderUtils;
import me.SaberAspect.SpectClient.value.types.BooleanValue;
import me.SaberAspect.SpectClient.wrappers.Wrapper;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;

public class ItemESP extends Hack{
	
	public ItemESP() {
		super("ItemESP", HackCategory.VISUAL);
	}
	
	@Override
	public String getDescription() {
		return "Highlights nearby items.";
	}
	
	@Override
	public void onRenderWorldLast(RenderWorldLastEvent event) {
		for (Object object : Utils.getEntityList()) {
			if(object instanceof EntityItem || object instanceof EntityArrow) {
				Entity item = (Entity)object;
				RenderUtils.drawESP(item, 1.0f, 1.0f, 1.0f, 1.0f, event.getPartialTicks());
			}
		}
		super.onRenderWorldLast(event);
	}
}
