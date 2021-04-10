package me.SaberAspect.SpectClient.api.util.Login;

import me.SaberAspect.Reference;
import me.SaberAspect.SpectClient.api.util.Wrapper;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;

public class ChatUtils{	// TODO Rewrite to LogManager 
	
	public static void component(ITextComponent component)
	{
		if(Wrapper.getPlayer() == null || Wrapper.getMinecraft().ingameGUI.getChatGUI() == null) 
			return;
			Wrapper.getMinecraft().ingameGUI.getChatGUI()
				.printChatMessage(new TextComponentTranslation("")
					.appendSibling(component));
	}
	
	public static void message(Object message)
	{
		component(new TextComponentTranslation("\u00a78" + Reference.NAME + "\u00a77 " + message));
	}
	
	public static void warning(Object message)
	{
		message("\u00a78[\u00a7eWARNING\u00a78]\u00a7e " + message);
	}
	
	public static void error(Object message)
	{
		message("\u00a78[\u00a74ERROR\u00a78]\u00a7c " + message);
	}
}

