package me.SaberAspect.SpectClient.client.command.commands;

import me.SaberAspect.SpectClient.client.command.Command;
import me.SaberAspect.SpectClient.client.command.CommandManager;
import me.SaberAspect.SpectClient.client.module.ModuleManager;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;

public class Vanish extends Command {
	private static Entity ridden;
	
    public Vanish() {
		super("vanish", "vanish ridden entities.", "vanish", "v");
	}

	@Override
	public void onCommand(String[] args, String command) {
		if(args.length == 0) {
			if (Minecraft.getMinecraft().player.getRidingEntity() != null && ridden == null) {
				ridden = Minecraft.getMinecraft().player.getRidingEntity();
				
			    Minecraft.getMinecraft().player.dismountRidingEntity();
			    Minecraft.getMinecraft().world.removeEntityFromWorld(ridden.getEntityId());
			    ModuleManager.addChatMessage("entity " + ridden.getName() + " removed.");
			}else {
			    if (ridden != null) {
			    	ridden.isDead = false;
			    	
			        Minecraft.getMinecraft().world.addEntityToWorld(ridden.getEntityId(), ridden);
			        Minecraft.getMinecraft().player.startRiding(ridden, true);
			        ModuleManager.addChatMessage("entity " + ridden.getName() + " created.");
			        ridden = null;
			    }else {
			    	 ModuleManager.addChatMessage("no entity is being ridden");
			    }
			}
		}else CommandManager.correctUsageMsg("", getName(), getSyntax());
	}
}