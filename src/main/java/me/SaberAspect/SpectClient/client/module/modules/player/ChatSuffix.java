package me.SaberAspect.SpectClient.client.module.modules.player;

import java.util.Arrays;

import org.lwjgl.input.Keyboard;

import me.SaberAspect.SpectClient.client.module.Category;
import me.SaberAspect.SpectClient.client.module.Module;
import me.SaberAspect.SpectClient.client.setting.settings.BooleanSetting;
import me.SaberAspect.SpectClient.client.setting.settings.ModeSetting;
import net.minecraftforge.client.event.ClientChatEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ChatSuffix extends Module {
	public ModeSetting mode = new ModeSetting("mode", this, "normal", "normal", "Fricked");
	public BooleanSetting discludePercent = new BooleanSetting("disclude%", this, true);
	
	public ChatSuffix() {
		super ("chatSuffix", "adds SpectClient's suffix to all of your chat msg's.", Keyboard.KEY_NONE, Category.PLAYER);
		this.addSettings(mode);
	}

	@SubscribeEvent
	public void onChat(final ClientChatEvent event) {
		if(toggled) {
			for (final String s : Arrays.asList("/", ".", "-", ",", ":", ";", "'", "\"", "+", "\\", "@", "#")) {
				if (event.getMessage().startsWith(s)) return;
			}
			if(mode.is("Fricked")) event.setMessage(event.getMessage() + " " + "á•¦á´˜á´�Ñ•á´›àº•" + "\u1d00" + "Î· " + "Ñ•á´›Ñ�á´�Î·É¢á•¤");
			else if(mode.is("normal")) event.setMessage(event.getMessage() + " " + "\u1566\u0160\u0050\u2130\u2102\uFF34\u2102\u013D\u1D00\u03B7\u0020\u044F\u0130\u0160\u2130\u0020\u0075\u0050\u1564");
																					//(      S    P     E     c      T      C    L     A    N      spc   R     I      S   E    spc     G     )
			// \u1566 \u1D18 \u1D0F \u0455 \u1D1B \u0E95 \u1D00 \u03B7 \u0020 \u0455 \u1D1B \u044F \u1D0F \u03B7 \u0262 \u1564 á•¦á´˜á´�Ñ•á´›àº•á´€Î· Ñ•á´›Ñ�á´�Î·É¢á•¤
		}
	}
	
	public void onEnable() {
		MinecraftForge.EVENT_BUS.register(this);
	}
	
	public void onDisable() {
		MinecraftForge.EVENT_BUS.unregister(this);
	}
}
