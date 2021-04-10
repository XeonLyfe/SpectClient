package me.SaberAspect.SpectClient.client.command.commands;

import org.lwjgl.input.Keyboard;

import com.mojang.realmsclient.gui.ChatFormatting;

import me.SaberAspect.SpectClient.client.command.Command;
import me.SaberAspect.SpectClient.client.command.CommandManager;
import me.SaberAspect.SpectClient.client.module.Module;
import me.SaberAspect.SpectClient.client.module.ModuleManager;

public class Bind extends Command {
	
	public Bind() {
		super("bind", "bind modules to specific keys.", "bind <name> <key> | bind clear", "b");
	}

	@Override
	public void onCommand(String[] args, String command) {
		if(args.length == 2) {
			String moduleName = args[0];
			String keyName = args[1];
			boolean moduleFound = false;
			
			for(Module module : ModuleManager.modules) {
				if(module.name.equalsIgnoreCase(moduleName)) {
					module.keyCode.setKeyCode(Keyboard.getKeyIndex(keyName.toUpperCase()));
					
					ModuleManager.addChatMessage(String.format(ChatFormatting.GREEN + "%s " + ChatFormatting.GRAY + "was bound to" + ChatFormatting.GREEN + " %s", module.name, Keyboard.getKeyName(module.getKey())));;
					moduleFound = true;
					break;
				}
			}
			if(!moduleFound) {
				ModuleManager.addChatMessage(ChatFormatting.DARK_RED + "module not found.");
			}
		}
		
		if(args.length == 1) {
			String clear = args[0];
			if(clear.equalsIgnoreCase("clear")) {
				for(Module module : ModuleManager.modules) {
					module.keyCode.setKeyCode(Keyboard.KEY_NONE);
				}
				ModuleManager.addChatMessage("cleared all binds.");
			} else CommandManager.correctUsageMsg("", getName(), getSyntax());
		}
		if(args.length == 0) CommandManager.correctUsageMsg("", getName(), getSyntax());
	}

}
