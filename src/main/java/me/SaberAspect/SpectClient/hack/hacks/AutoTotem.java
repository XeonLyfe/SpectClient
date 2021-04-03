package me.SaberAspect.SpectClient.hack.hacks;

import me.SaberAspect.SpectClient.hack.Hack;
import me.SaberAspect.SpectClient.hack.HackCategory;
import me.SaberAspect.SpectClient.utils.Utils;
import me.SaberAspect.SpectClient.utils.system.Connection.Side;
import me.SaberAspect.SpectClient.value.types.BooleanValue;
import me.SaberAspect.SpectClient.value.types.IntegerValue;
import me.SaberAspect.SpectClient.wrappers.Wrapper;
import net.minecraft.init.Items;
import net.minecraft.inventory.ClickType;
import net.minecraft.inventory.ContainerPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.network.play.client.CPacketClickWindow;
import net.minecraft.util.NonNullList;
import net.minecraftforge.fml.common.gameevent.TickEvent.ClientTickEvent;

public class AutoTotem extends Hack{

	public BooleanValue swapWhileMoving;
	
	public IntegerValue delay;
	
	private int timer;
	
	public AutoTotem() {
		super("AutoTotem", HackCategory.COMBAT);
		
		swapWhileMoving = new BooleanValue("SwapWhileMoving", false);
		delay = new IntegerValue("SwipeDelay", 2, 0, 20);
		
		this.addValue(swapWhileMoving, delay);
	}
	
	@Override
	public String getDescription() {
		return "Automatically takes a totem in offhand.";
	}
	
	@Override
	public void onEnable() {
		this.timer = 0;
		super.onEnable();
	}
	
	@Override
	public boolean onPacket(Object packet, Side side) {
		if(side == Side.OUT && packet instanceof CPacketClickWindow) {
			this.timer = this.delay.getValue();
		}
		return true;
	}
	
	@Override
	public void onClientTick(ClientTickEvent event) {
		if(timer > 0){
			timer--;
			return;
		}
		
		if(!swapWhileMoving.getValue()
				&& (Wrapper.INSTANCE.player().movementInput.moveForward != 0
					|| Wrapper.INSTANCE.player().movementInput.moveStrafe != 0))
				return;
		
		NonNullList<ItemStack> inv;
        ItemStack offhand = Wrapper.INSTANCE.player().getItemStackFromSlot(EntityEquipmentSlot.OFFHAND);

        int inventoryIndex;
        inv = Wrapper.INSTANCE.inventory().mainInventory;
         
        for(inventoryIndex = 0; inventoryIndex < inv.size(); inventoryIndex++) {
            if (inv.get(inventoryIndex) != ItemStack.EMPTY) {
         		if ((offhand == null) || (offhand.getItem() != Items.TOTEM_OF_UNDYING)) {
         			if (inv.get(inventoryIndex).getItem() == Items.TOTEM_OF_UNDYING) {
         				replace(inventoryIndex);
         	            break;
         			}
         		}
             }
         }
		super.onClientTick(event);
	}
	
	public void replace(int inventoryIndex) {
        if (Wrapper.INSTANCE.player().openContainer instanceof ContainerPlayer) {
        	Utils.windowClick(0, inventoryIndex < 9 ? inventoryIndex + 36 : inventoryIndex, 0, ClickType.PICKUP);
        	Utils.windowClick(0, 45, 0, ClickType.PICKUP);
        	Utils.windowClick(0, inventoryIndex < 9 ? inventoryIndex + 36 : inventoryIndex, 0, ClickType.PICKUP);
        }
    }
}
