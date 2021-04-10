package me.SaberAspect.SpectClient.client.command.commands;

import me.SaberAspect.SpectClient.api.util.Wrapper;
import me.SaberAspect.SpectClient.api.util.Login.ChatUtils;
import me.SaberAspect.SpectClient.api.util.Login.LoginUtils;
import me.SaberAspect.SpectClient.client.command.Command;

public class Login extends Command{

	public Login() {
		super("Login", "Allows you to login to your profile (for devs)", "login", "Log");
	}

	@Override
	public void onCommand(String[] args, String command) {
		{
			try
			{
				if(args.length > 1 || args[0].contains(":")) {
					String email = "";
					String password = "";
					if(args[0].contains(":")) {
						String[] split = args[0].split(":", 2);
						email = split[0];
						password = split[1];
					}
					else
					{
						email = args[0];
						password = args[1];
					}
					String log = LoginUtils.loginAlt(email, password);
					ChatUtils.warning(log);
				} 
				else 
				{
					LoginUtils.changeCrackedName(args[0]);
					ChatUtils.warning("Logged [Cracked]: " + Wrapper.getMinecraft().getSession().getUsername());
				}
			}
			catch(Exception e)
			{
				ChatUtils.error("Usage: " + getSyntax());
			}
		}
	}

}
