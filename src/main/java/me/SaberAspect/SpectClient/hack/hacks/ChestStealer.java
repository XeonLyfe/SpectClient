package me.SaberAspect.SpectClient.hack.hacks;

import me.SaberAspect.SpectClient.hack.Hack;
import me.SaberAspect.SpectClient.hack.HackCategory;
import me.SaberAspect.SpectClient.utils.system.Connection.Side;
import me.SaberAspect.SpectClient.value.types.IntegerValue;
import me.SaberAspect.SpectClient.wrappers.Wrapper;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.gui.inventory.GuiChest;
import net.minecraft.inventory.ClickType;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.network.play.server.SPacketWindowItems;
import net.minecraftforge.fml.common.gameevent.TickEvent.ClientTickEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.Phase;

public class ChestStealer extends Hack{

	public IntegerValue delay;
	
	public SPacketWindowItems packet;
	public int ticks;
	
	public ChestStealer() {
		super("ChestStealer", HackCategory.PLAYER);
		
		delay = new IntegerValue("Delay", 4, 0, 20);
		
		this.addValue(delay);
		this.ticks = 0;
	}
	
	@Override
    public String getDescription() {
        return "Steals all stuff from chest.";
    }
	
	@Override
	public boolean onPacket(Object packet, Side side) {
		if(side == Side.IN && packet instanceof SPacketWindowItems) {
			this.packet = (SPacketWindowItems)packet;
		}
		return true;
	}
	
	boolean isContainerEmpty(Container container) {
		boolean temp = true;
	    int i = 0;
	    for(int slotAmount = container.inventorySlots.size() == 90 ? 54 : 35; i < slotAmount; i++) {
	    	if (container.getSlot(i).getHasStack()) {
	    		temp = false;
	    	}
	    }
	    return temp;
	}
	
	@Override
	public void onClientTick(ClientTickEvent event) {
		if(event.phase != Phase.START) return;
		EntityPlayerSP player = Wrapper.INSTANCE.player();
		if ((!Wrapper.INSTANCE.mc().inGameHasFocus) 
        		&& (this.packet != null) 
        		&& (player.openContainer.windowId == this.packet.getWindowId()) 
        		&& ((Wrapper.INSTANCE.mc().currentScreen instanceof GuiChest))) {
			if (!isContainerEmpty(player.openContainer)) {
				for (int i = 0; i < player.openContainer.inventorySlots.size() - 36; ++i) {
                    Slot slot = player.openContainer.getSlot(i);
                    if (slot.getHasStack() && slot.getStack() != null) {
                    	if (this.ticks >= this.delay.getValue()) {
        	            	Wrapper.INSTANCE.controller().windowClick(player.openContainer.windowId, i, 1, ClickType.QUICK_MOVE, player);
        	            	this.ticks = 0;
        	            }
                    }
                }
				this.ticks += 1;
			} 
			else 
			{
            	player.closeScreen();
            	this.packet = null;
            }
		}
	}
}
