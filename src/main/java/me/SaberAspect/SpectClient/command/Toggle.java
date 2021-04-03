package me.SaberAspect.SpectClient.command;

import me.SaberAspect.SpectClient.managers.HackManager;
import me.SaberAspect.SpectClient.utils.LoginUtils;
import me.SaberAspect.SpectClient.utils.visual.ChatUtils;
import me.SaberAspect.SpectClient.wrappers.Wrapper;
import net.minecraft.item.ItemStack;
import net.minecraft.network.play.client.CPacketChatMessage;

public class Toggle extends Command
{
	public Toggle()
	{
		super("toggle");
	}

	@Override
	public void runCommand(String s, String[] args)
	{
		try
		{
			HackManager.getHack(args[0]).toggle();
		}
		catch(Exception e)
		{
			ChatUtils.error("Usage: " + getSyntax());
		}
	}

	@Override
	public String getDescription()
	{
		return "Toggling selected hack.";
	}

	@Override
	public String getSyntax()
	{
		return "toggle <hackname>";
	}
}