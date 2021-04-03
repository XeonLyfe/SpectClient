package me.SaberAspect.SpectClient.utils.system;

import me.SaberAspect.SpectClient.EventsHandler;
import me.SaberAspect.SpectClient.utils.visual.ChatUtils;
import me.SaberAspect.SpectClient.wrappers.Wrapper;
import io.netty.channel.ChannelDuplexHandler;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.ChannelPromise;

public class Connection extends ChannelDuplexHandler {

	public static enum Side { IN, OUT; }
    private EventsHandler eventHandler;

    public Connection(EventsHandler eventHandler) {
        this.eventHandler = eventHandler;
        try {
            ChannelPipeline pipeline = Wrapper.INSTANCE.mc().getConnection().getNetworkManager().channel().pipeline();
            pipeline.addBefore("packet_handler", "PacketHandler", (ChannelHandler) this);
        } catch (Exception exception) {
        	ChatUtils.error("Connection: Error on attaching");
            exception.printStackTrace();
        }
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object packet) throws Exception {
        if (!eventHandler.onPacket(packet, Side.IN)) return;
        super.channelRead(ctx, packet);
    }

    @Override
    public void write(ChannelHandlerContext ctx, Object packet, ChannelPromise promise) throws Exception {
        if (!eventHandler.onPacket(packet, Side.OUT)) return;
        super.write(ctx, packet, promise);
    }
}
