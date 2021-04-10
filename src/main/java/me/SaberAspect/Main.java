package me.SaberAspect;

import java.awt.Font;
import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import me.SaberAspect.SpectClient.api.event.EventProcessor;
import me.SaberAspect.SpectClient.api.proxy.CommonProxy;
import me.SaberAspect.SpectClient.api.save.ClickGuiLoad;
import me.SaberAspect.SpectClient.api.save.ClickGuiSave;
import me.SaberAspect.SpectClient.api.save.ConfigStopper;
import me.SaberAspect.SpectClient.api.save.SaveLoad;
import me.SaberAspect.SpectClient.api.util.font.CustomFontRenderer;
import me.SaberAspect.SpectClient.api.util.render.Cape;
import me.SaberAspect.SpectClient.client.command.CommandManager;
import me.SaberAspect.SpectClient.client.friend.FriendManager;
import me.SaberAspect.SpectClient.client.module.Module;
import me.SaberAspect.SpectClient.client.module.ModuleManager;
import me.SaberAspect.SpectClient.client.setting.SettingManager;
import me.SaberAspect.SpectClient.client.ui.TabGui;
import me.SaberAspect.SpectClient.client.ui.clickgui.ClickGui;
import me.zero.alpine.EventBus;
import me.zero.alpine.EventManager;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;

/*
 * Written by @SrgantMooMoo on 11/17/20.
 * Multithreading done by techale.
 */


@Mod(modid = Reference.MOD_ID, name = Reference.NAME, version = Reference.VERSION)
public class Main {
	public static ArrayList<Module> modules;
	
	int weak;
	int SpectClient = weak;
	
	public static ModuleManager moduleManager;
	public static SettingManager settingManager;
	public static CommandManager commandManager;
	public static FriendManager friendManager;
	public static SaveLoad saveLoad;
	public static TabGui tabGui;
	public static Cape cape;
	public static ClickGui clickGui;
	public static EventProcessor eventProcessor;
	public static CustomFontRenderer customFontRenderer;
	public static ClickGuiSave clickGuiSave;
	public static ClickGuiLoad clickGuiLoad;
	
	public static final Logger log = LogManager.getLogger("SpectClient");
	
	public static final EventBus EVENT_BUS = new EventManager();
	
	@Instance
	public static Main instance;
	
	@SidedProxy(clientSide = Reference.CLIENT_PROXY_CLASS, serverSide = Reference.COMMON_PROXY_CLASS)
	public static CommonProxy proxy;

	public Object syncronize = new Object();

	public void fontInit() {

		customFontRenderer = new CustomFontRenderer(new Font("Comic Sans MS", Font.PLAIN, 18), false,false);
		printLog("custom font initialized.");
	}

	private void loadCfg() {
		saveLoad = new SaveLoad();
		printLog("configs initialized.");
	}

	public void extClientInit() {
		MinecraftForge.EVENT_BUS.register(this);

		eventProcessor = new EventProcessor();
		printLog("SpectClient event system initialized.");

		settingManager = new SettingManager();
		printLog("settings system initialized.");

		moduleManager = new ModuleManager();
		printLog("module system initialized.");

		commandManager = new CommandManager();
		printLog("command system initialized.");
		
		friendManager = new FriendManager();
		printLog("friend system initialized.");

		cape = new Cape();
		printLog("capes initialized.");

		tabGui = new TabGui();
		printLog("tabgui initialized.");

		clickGui = new ClickGui();
		printLog("clickGui initialized.");

		clickGuiSave = new ClickGuiSave();
		clickGuiLoad = new ClickGuiLoad();
		Runtime.getRuntime().addShutdownHook(new ConfigStopper());
		printLog("gui config initialized.");
		
		printLog("SpectClient finished Loading.");

	}


	@EventHandler
	public void init (FMLInitializationEvent event) {
		Thread t = new Thread(this::extClientInit);
		t.start();
		fontInit();
		try {
			t.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		log.info("SpectClient Loader finished.");
		new Thread(this::loadCfg).start();

	}
	
	public void printLog(String text) {
		synchronized (syncronize) {
			log.info(text);
		}
	}
}

