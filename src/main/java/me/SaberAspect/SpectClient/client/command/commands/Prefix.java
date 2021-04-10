package me.SaberAspect.SpectClient.client.command.commands;

import com.mojang.realmsclient.gui.ChatFormatting;

import me.SaberAspect.SpectClient.client.command.Command;
import me.SaberAspect.SpectClient.client.command.CommandManager;
import me.SaberAspect.SpectClient.client.module.ModuleManager;

public class Prefix extends Command {
	public Prefix() {
		super("prefix", "sets the command prefix.", "prefix <key>", "p");
	}
	
	@Override
	public void onCommand(String[] args, String command) {
		if(args.length == 1) {
			String key = args[0];
			CommandManager.setCommandPrefix(key);
			ModuleManager.addChatMessage(String.format(ChatFormatting.GREEN + "command prefix " + ChatFormatting.GRAY + "was set to " + ChatFormatting.GREEN + CommandManager.prefix));
		}
		
		if(args.length == 0) CommandManager.correctUsageMsg("", getName(), getSyntax());
	}

}