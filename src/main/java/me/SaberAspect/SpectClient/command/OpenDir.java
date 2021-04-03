package me.SaberAspect.SpectClient.command;

import java.awt.Desktop;

import me.SaberAspect.SpectClient.managers.FileManager;
import me.SaberAspect.SpectClient.utils.LoginUtils;
import me.SaberAspect.SpectClient.utils.visual.ChatUtils;
import me.SaberAspect.SpectClient.wrappers.Wrapper;
import net.minecraft.item.ItemStack;
import net.minecraft.network.play.client.CPacketChatMessage;

public class OpenDir extends Command
{
	public OpenDir()
	{
		super("opendir");
	}

	@Override
	public void runCommand(String s, String[] args)
	{
		try
		{
			Desktop.getDesktop().open(FileManager.SpectClient_DIR);
		}
		catch(Exception e)
		{
			ChatUtils.error("Usage: " + getSyntax());
		}
	}

	@Override
	public String getDescription()
	{
		return "Opening directory of config.";
	}

	@Override
	public String getSyntax()
	{
		return "opendir";
	}
}