package me.SaberAspect.SpectClient.command;

import org.lwjgl.input.Keyboard;

import me.SaberAspect.SpectClient.hack.Hack;
import me.SaberAspect.SpectClient.managers.CommandManager;
import me.SaberAspect.SpectClient.managers.HackManager;
import me.SaberAspect.SpectClient.utils.visual.ChatUtils;

public class Bind extends Command {
	public Bind() {
		super("bind");
	}

	@Override
	public void runCommand(String s, String[] args) {
		try {
			String key = args[0];
			for (Command cmd : CommandManager.getInstance().commands) {
				if (args[1].startsWith(cmd.getCommand())) {
					String s1 = cmd.getCommand();
					for (int i = 2; i < args.length; i++)
						s1 += (" " + args[i]);
					cmd.setExecute(s1);
					cmd.setKey(Keyboard.getKeyIndex((key.toUpperCase())));
					ChatUtils.message(cmd.getCommand() + " binded to \u00a79" + Keyboard.getKeyName(cmd.getKey()));
					return;
				}
			}
			for (Hack hack : HackManager.getHacks()) {
				if (hack.getName().equalsIgnoreCase(args[1])) {
					hack.setKey(Keyboard.getKeyIndex((key.toUpperCase())));
					ChatUtils.message(hack.getName() + " binded to \u00a79" + Keyboard.getKeyName(hack.getKey()));
					break;
				}
			}
		} catch (Exception e) {
			ChatUtils.error("Usage: " + getSyntax());
		}
	}

	@Override
	public String getDescription() {
		return "Change key for hacks/commands.";
	}

	@Override
	public String getSyntax() {
		return "bind <key> <command/hack>";
	}
}