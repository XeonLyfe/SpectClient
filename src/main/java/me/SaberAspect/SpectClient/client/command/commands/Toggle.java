package me.SaberAspect.SpectClient.client.command.commands;

import com.mojang.realmsclient.gui.ChatFormatting;

import me.SaberAspect.SpectClient.client.command.Command;
import me.SaberAspect.SpectClient.client.command.CommandManager;
import me.SaberAspect.SpectClient.client.module.Module;
import me.SaberAspect.SpectClient.client.module.ModuleManager;

public class Toggle extends Command {
	
	public Toggle() {
		super("toggle", "toggles a module on or off.", "toggle <module>", "t");
	}

	@Override
	public void onCommand(String[] args, String command) {
		if(args.length > 0) {
			String moduleName = args[0];
			boolean moduleFound = false;
			for(Module module : ModuleManager.modules) {
				if(module.name.equalsIgnoreCase(moduleName)) {
					module.toggle();
					ModuleManager.addChatMessage(module.name + " " + (module.isToggled() ? ChatFormatting.GREEN + "enabled" + ChatFormatting.GRAY + "." : ChatFormatting.RED + "disabled" + ChatFormatting.GRAY + "."));
					moduleFound = true;
					break;
				}
			}
			if(!moduleFound) {
				ModuleManager.addChatMessage(ChatFormatting.DARK_RED + "module not found.");
			}
		}else CommandManager.correctUsageMsg("", getName(), getSyntax());
	}

}
