package me.SaberAspect.SpectClient.command;

import me.SaberAspect.SpectClient.utils.LoginUtils;
import me.SaberAspect.SpectClient.utils.Utils;
import me.SaberAspect.SpectClient.utils.visual.ChatUtils;
import me.SaberAspect.SpectClient.wrappers.Wrapper;
import net.minecraft.item.ItemStack;
import net.minecraft.network.play.client.CPacketChatMessage;

public class SelfDamage extends Command
{
	public SelfDamage()
	{
		super("selfdamage");
	}

	@Override
	public void runCommand(String s, String[] args)
	{
		try
		{   //0.0625D
			double damage = Double.parseDouble(args[0]);
			Utils.selfDamage(damage);
		}
		catch(Exception e)
		{
			ChatUtils.error("Usage: " + getSyntax());
		}
	}

	@Override
	public String getDescription()
	{
		return "Deals damage to you (useful for bypassing AC).";
	}

	@Override
	public String getSyntax()
	{
		return "selfdamage <damage>";
	}
}