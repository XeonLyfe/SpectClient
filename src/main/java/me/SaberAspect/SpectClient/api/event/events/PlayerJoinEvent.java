package me.SaberAspect.SpectClient.api.event.events;

import me.SaberAspect.SpectClient.api.event.Event;

public class PlayerJoinEvent extends Event {

	private final String name;

	public PlayerJoinEvent(String name) {
		super();
		this.name = name;
	}

	public String getName() {
		return this.name;
	}
}