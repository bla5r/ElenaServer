package com.elena.server.network.transfer;

import com.elena.protocol.ElenaProtocol;
import com.elena.server.event.EventHandler;
import com.elena.server.network.process.PacketProcesser;
import com.elena.server.network.client.Client;
import com.elena.server.log.Logger;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.AttributeKey;

/**
 * Created by bla5r on 13/03/2017.
 */

public class ServerHandler extends SimpleChannelInboundHandler<ElenaProtocol.MessageEnvelop> {

    private EventHandler eventHandler;
    private static final AttributeKey<Client> client = AttributeKey.valueOf("client");

    public ServerHandler(EventHandler eventHandler) {
        this.eventHandler = eventHandler;
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        Logger.info("Client connected");
        ctx.channel().attr(client).set(new Client(ctx));
    }

    @Override
    public void channelRead0(ChannelHandlerContext ctx, ElenaProtocol.MessageEnvelop envelop) throws Exception {
        PacketProcesser.getInstance().execute(this.eventHandler, ctx, ctx.channel().attr(client).get(), envelop);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) {
        ctx.flush();
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        Logger.info("Client disconnected");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        Logger.error("Exception caught");
        cause.printStackTrace();
        ctx.close();
    }

}