package me.SaberAspect.SpectClient.hack.hacks;

import java.util.ArrayList;

import me.SaberAspect.SpectClient.hack.Hack;
import me.SaberAspect.SpectClient.hack.HackCategory;
import me.SaberAspect.SpectClient.managers.HackManager;
import me.SaberAspect.SpectClient.managers.PickupFilterManager;
import me.SaberAspect.SpectClient.utils.Utils;
import me.SaberAspect.SpectClient.utils.system.Connection.Side;
import me.SaberAspect.SpectClient.utils.visual.ChatUtils;
import me.SaberAspect.SpectClient.utils.visual.RenderUtils;
import me.SaberAspect.SpectClient.value.types.BooleanValue;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraftforge.event.entity.player.EntityItemPickupEvent;

public class PickupFilter extends Hack{
	
	public BooleanValue all;
	
	public PickupFilter() {
		super("PickupFilter", HackCategory.ANOTHER);
		
		this.all = new BooleanValue("IgnoreAll", false);
		this.addValue(all);
	}
	
	@Override
	public String getDescription() {
		return "Filters item picking.";
	}
	
	@Override
	public void onItemPickup(EntityItemPickupEvent event) {
		if(this.all.getValue()) {
			event.setCanceled(true);
			return;
		}
		for(int itemId : PickupFilterManager.items) {
			Item item = Item.getItemById(itemId);
			if(item == null) continue;
			if(event.getItem().getItem().getItem() == item)
				event.setCanceled(true);
		}
		super.onItemPickup(event);
	}
}
