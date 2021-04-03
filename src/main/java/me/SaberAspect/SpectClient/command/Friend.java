package me.SaberAspect.SpectClient.command;

import me.SaberAspect.SpectClient.managers.EnemyManager;
import me.SaberAspect.SpectClient.managers.FriendManager;
import me.SaberAspect.SpectClient.utils.EntityBot;
import me.SaberAspect.SpectClient.utils.LoginUtils;
import me.SaberAspect.SpectClient.utils.Utils;
import me.SaberAspect.SpectClient.utils.visual.ChatUtils;
import me.SaberAspect.SpectClient.wrappers.Wrapper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class Friend extends Command
{
	public Friend()
	{
		super("friend");
	}

	@Override
	public void runCommand(String s, String[] args)
	{
		try
		{	
			if(args[0].equalsIgnoreCase("add")) {
				if(args[1].equalsIgnoreCase("all")) {
					for(Object object : Utils.getEntityList()) {
						if(object instanceof EntityPlayer) {
							EntityPlayer player = (EntityPlayer) object;
							if(!player.isInvisible()) {
								FriendManager.addFriend(Utils.getPlayerName(player));
							}
						}
					}
				} else {
					FriendManager.addFriend(args[1]);
				}
			}
			else
			if(args[0].equalsIgnoreCase("remove")) {
				FriendManager.removeFriend(args[1]);
			}
			else
			if(args[0].equalsIgnoreCase("clear")) {
				FriendManager.clear();
			}
		}
		catch(Exception e)
		{
			ChatUtils.error("Usage: " + getSyntax());
		}
	}

	@Override
	public String getDescription()
	{
		return "Friend manager.";
	}

	@Override
	public String getSyntax()
	{
		return "friend <add/remove/clear> <nick>";
	}
}