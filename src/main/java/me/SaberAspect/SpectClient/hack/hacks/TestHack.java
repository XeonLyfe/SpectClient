package me.SaberAspect.SpectClient.hack.hacks;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import com.mojang.authlib.minecraft.MinecraftProfileTexture.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

import me.SaberAspect.SpectClient.hack.Hack;
import me.SaberAspect.SpectClient.hack.HackCategory;
import me.SaberAspect.SpectClient.managers.HackManager;
import me.SaberAspect.SpectClient.utils.Utils;
import me.SaberAspect.SpectClient.utils.system.Mapping;
import me.SaberAspect.SpectClient.utils.system.Connection.Side;
import me.SaberAspect.SpectClient.utils.visual.ChatUtils;
import me.SaberAspect.SpectClient.utils.visual.RenderUtils;
import me.SaberAspect.SpectClient.wrappers.Wrapper;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.gui.inventory.GuiContainerCreative;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.client.network.NetworkPlayerInfo;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.Entity;
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
import net.minecraft.network.play.client.CPacketPlayer.Rotation;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StringUtils;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.GameType;
import net.minecraftforge.client.event.GuiContainerEvent;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.event.entity.player.EntityItemPickupEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.gameevent.TickEvent.ClientTickEvent;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.relauncher.ReflectionHelper;

public class TestHack extends Hack{
	
	public TestHack() {
		super("TestHack", HackCategory.ANOTHER);
	}
	
	@Override
	public String getDescription() {
		return "I do not recommend enabling this!";
	}
	
	@Override
	public void onEnable() {
		ChatUtils.warning("Why ?");
		super.onEnable();
	}
	
	
}
