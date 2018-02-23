package com.elena.server.network.packet.outgoing;

import com.elena.protocol.ElenaProtocol;
import io.netty.channel.ChannelHandlerContext;

/**
 * Created by bla5r on 14/03/2017.
 */

public class HelloOut {

    public HelloOut() {

    }

    public static boolean execute(ChannelHandlerContext ctx) {
        ElenaProtocol.HelloResponse.Builder response = ElenaProtocol.HelloResponse.newBuilder();
        response.setVersion(1);
        response.setAuthRequired(false);
        ctx.channel().writeAndFlush(MessageEnvelop.get(ElenaProtocol.PacketId.HELLO_RESPONSE, response.build().toByteString()));
        return (true);
    }

}
