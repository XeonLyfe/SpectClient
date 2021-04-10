package me.SaberAspect.SpectClient.api.util.misc;

import club.minnced.discord.rpc.DiscordEventHandlers;
import club.minnced.discord.rpc.DiscordRPC;
import club.minnced.discord.rpc.DiscordRichPresence;
import me.SaberAspect.Reference;
import net.minecraft.client.Minecraft;

public class Discord {

    private static String discordID = "810045143142367262";
    private static DiscordRichPresence discordRichPresence = new DiscordRichPresence();
    private static DiscordRPC discordRPC = DiscordRPC.INSTANCE;

    public static void startRPC(){
        DiscordEventHandlers eventHandlers = new DiscordEventHandlers();
        eventHandlers.disconnected = ((var1, var2) -> System.out.println("Discord RPC disconnected, var1: " + var1 + ", var2: " + var2));

        discordRPC.Discord_Initialize(discordID, eventHandlers, true, null);

        discordRichPresence.startTimestamp = System.currentTimeMillis() / 1000L;
        discordRichPresence.details = Minecraft.getMinecraft().player.getName();
        discordRichPresence.largeImageKey = "large";
        discordRichPresence.largeImageText = "Spect Gang Rise Up";
        discordRichPresence.state = "im on version " + Reference.VERSION + "!";
        discordRPC.Discord_UpdatePresence(discordRichPresence);
    }

    public static void stopRPC(){
        discordRPC.Discord_Shutdown();
        discordRPC.Discord_ClearPresence();
    }
}