package me.SaberAspect.SpectClient.hack.hacks;

import java.util.ArrayList;

import me.SaberAspect.SpectClient.hack.Hack;
import me.SaberAspect.SpectClient.hack.HackCategory;
import me.SaberAspect.SpectClient.managers.HackManager;
import me.SaberAspect.SpectClient.utils.Utils;
import me.SaberAspect.SpectClient.utils.system.Connection.Side;
import me.SaberAspect.SpectClient.utils.visual.ChatUtils;
import me.SaberAspect.SpectClient.utils.visual.RenderUtils;
import me.SaberAspect.SpectClient.value.types.BooleanValue;
import me.SaberAspect.SpectClient.wrappers.Wrapper;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.gui.inventory.GuiContainerCreative;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemSword;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.client.CPacketCloseWindow;
import net.minecraft.network.play.client.CPacketConfirmTeleport;
import net.minecraft.network.play.client.CPacketCustomPayload;
import net.minecraft.network.play.client.CPacketEntityAction;
import net.minecraft.network.play.client.CPacketPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.GameType;
import net.minecraftforge.client.event.GuiContainerEvent;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.event.entity.player.EntityItemPickupEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.ClientTickEvent;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.relauncher.ReflectionHelper;

public class FakeCreative extends Hack {
	
	public GameType gameType;
	public BooleanValue showItemsId;
	
	public FakeCreative() {
		super("FakeCreative", HackCategory.ANOTHER);
		
		showItemsId = new BooleanValue("ShowItemsID", true);
		this.addValue(showItemsId);
	}
	
	@Override
	public void onGuiOpen(GuiOpenEvent event) {
		if(event.getGui() instanceof GuiContainerCreative)
			event.setGui(new me.SaberAspect.SpectClient.gui.GuiContainerCreative(Wrapper.INSTANCE.player()));
		super.onGuiOpen(event);
	}
	
	@Override
	public void onDisable() {
		if(gameType == null) return;
		Wrapper.INSTANCE.controller().setGameType(gameType);
		gameType = null;
		super.onDisable();
	}
	
	@Override
	public void onClientTick(ClientTickEvent event) {
		if(Wrapper.INSTANCE.controller().getCurrentGameType() == GameType.CREATIVE) return;
		gameType = Wrapper.INSTANCE.controller().getCurrentGameType();
		Wrapper.INSTANCE.controller().setGameType(GameType.CREATIVE);
		super.onClientTick(event);
	}
}
