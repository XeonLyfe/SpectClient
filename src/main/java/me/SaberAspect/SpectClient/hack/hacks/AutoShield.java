package me.SaberAspect.SpectClient.hack.hacks;

import me.SaberAspect.SpectClient.hack.Hack;
import me.SaberAspect.SpectClient.hack.HackCategory;
import me.SaberAspect.SpectClient.managers.HackManager;
import me.SaberAspect.SpectClient.utils.RobotUtils;
import me.SaberAspect.SpectClient.utils.Utils;
import me.SaberAspect.SpectClient.utils.ValidUtils;
import me.SaberAspect.SpectClient.utils.system.Connection.Side;
import me.SaberAspect.SpectClient.value.types.BooleanValue;
import me.SaberAspect.SpectClient.wrappers.Wrapper;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.inventory.ClickType;
import net.minecraft.inventory.ContainerPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.network.play.client.CPacketClickWindow;
import net.minecraft.util.NonNullList;
import net.minecraftforge.fml.common.gameevent.TickEvent.ClientTickEvent;

public class AutoShield extends Hack{
	
	public AutoShield() {
		super("AutoShield", HackCategory.COMBAT);
	}
	
	@Override
	public String getDescription() {
		return "Manages your shield automatically.";
	}
	
	@Override
	public void onClientTick(ClientTickEvent event) {
		if(!Utils.screenCheck()) { this.blockByShield(false); }
		super.onClientTick(event);
	}
	
	@Override
	public void onDisable() {
		this.blockByShield(false);
		super.onDisable();
	}
	
	 public void blockByShield(boolean state) {
		if(Wrapper.INSTANCE.player().getHeldItemOffhand().getItem() != Items.SHIELD) return;
			RobotUtils.setMouse(1, state);
	}
	 
	 public static void block(boolean state) {
		AutoShield hack = (AutoShield)HackManager.getHack("AutoShield");
		if(hack.isToggled()) hack.blockByShield(state);
	}
}
