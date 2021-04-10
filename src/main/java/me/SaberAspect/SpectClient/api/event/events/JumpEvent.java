package me.SaberAspect.SpectClient.api.event.events;

import me.SaberAspect.SpectClient.api.event.Event;
import me.SaberAspect.SpectClient.api.util.world.Location;

public class JumpEvent extends Event {

	private Location location;

	public JumpEvent(Location location) {
		this.location = location;
	}

	public Location getLocation() {
		return this.location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}
}