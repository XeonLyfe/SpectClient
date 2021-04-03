package me.SaberAspect.SpectClient.hack.hacks;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import me.SaberAspect.SpectClient.hack.Hack;
import me.SaberAspect.SpectClient.hack.HackCategory;

import me.SaberAspect.SpectClient.utils.TimerUtils;
import me.SaberAspect.SpectClient.utils.system.Connection.Side;
import me.SaberAspect.SpectClient.utils.visual.ChatUtils;
import me.SaberAspect.SpectClient.wrappers.Wrapper;
import joptsimple.internal.Strings;
import net.minecraft.network.play.client.CPacketTabComplete;
import net.minecraft.network.play.server.SPacketTabComplete;
import net.minecraftforge.fml.common.gameevent.TickEvent.ClientTickEvent;

public class PluginsGetter extends Hack{
    
	public PluginsGetter() {
		super("PluginsGetter", HackCategory.ANOTHER);
	}
	
	@Override
	public String getDescription() {
		return "Show all plugins on current server.";
	}
	
	@Override
	public void onEnable() {
		if(Wrapper.INSTANCE.player() == null) {
            return;
		}
        Wrapper.INSTANCE.sendPacket(new CPacketTabComplete("/", null, false));
		super.onEnable();
	}
	
	@Override
	public boolean onPacket(Object packet, Side side) {
		 if(packet instanceof SPacketTabComplete) {
	            SPacketTabComplete s3APacketTabComplete = (SPacketTabComplete) packet;
	 
	            List<String> plugins = new ArrayList<String>();
	            String[] commands = s3APacketTabComplete.getMatches();
	 
	            for(int i = 0; i < commands.length; i++) {
	                String[] command = commands[i].split(":");
	 
	                if(command.length > 1) {
	                    String pluginName = command[0].replace("/", "");
	 
	                    if(!plugins.contains(pluginName)) {
	                        plugins.add(pluginName);
	                    }
	                }
	            }
	            
	            Collections.sort(plugins);
	            
	            if(!plugins.isEmpty()) {
	                ChatUtils.message("Plugins \u00a77(\u00a78" + plugins.size() + "\u00a77): \u00a79" + Strings.join(plugins.toArray(new String[0]), "\u00a77, \u00a79"));
	            }
	            else
	            {
	                ChatUtils.error("No plugins found.");
	            }
	            this.setToggled(false);   
	        }
		return true;
	}
}
