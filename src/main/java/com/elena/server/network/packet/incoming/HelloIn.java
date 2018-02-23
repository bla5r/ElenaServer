package com.elena.server.network.packet.incoming;

import com.elena.protocol.ElenaProtocol;
import com.elena.server.event.EventHandler;
import com.elena.server.log.Logger;
import com.elena.server.network.packet.outgoing.HelloOut;
import com.elena.server.network.client.Client;
import com.google.protobuf.InvalidProtocolBufferException;
import io.netty.channel.ChannelHandlerContext;

/**
 * Created by bla5r on 14/03/2017.
 */

public class HelloIn implements IPacketIn {

    public HelloIn() {

    }

    public boolean execute(EventHandler eventHandler, ChannelHandlerContext ctx, Client client, byte[] data) {
        try {
            ElenaProtocol.HelloRequest request = ElenaProtocol.HelloRequest.parseFrom(data);
            HelloOut.execute(ctx);
            eventHandler.onHello(client, request.getTimestamp(), request.getHostname(), request.getHostname());
        }
        catch (InvalidProtocolBufferException e) {
            Logger.error("Unable to unpack HelloRequest");
            return (false);
        }
        return (true);
    }

}
