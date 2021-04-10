package me.SaberAspect.SpectClient.api.event.events;

import me.SaberAspect.SpectClient.api.event.Event;
import net.minecraft.network.Packet;

public class PacketEvent extends Event {

	private final Packet packet;

	public PacketEvent(Packet packet) {
		super();
		this.packet = packet;
	}

	public Packet getPacket() {
		return this.packet;
	}

	public static class Receive extends PacketEvent {

		public Receive(Packet packet) {
			super(packet);
		}
	}

	public static class Send extends PacketEvent {
		public Send(Packet packet) {
			super(packet);
		}
	}

	public static class PostReceive extends PacketEvent {
		public PostReceive(Packet packet) {
			super(packet);
		}
	}

	public static class PostSend extends PacketEvent {
		public PostSend(Packet packet) {
			super(packet);
		}
	}
}