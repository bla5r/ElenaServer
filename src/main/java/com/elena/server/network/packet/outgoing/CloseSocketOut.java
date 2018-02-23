package com.elena.server.network.packet.outgoing;

import com.elena.protocol.ElenaProtocol;
import com.elena.server.network.client.Client;
import io.netty.channel.ChannelHandlerContext;

/**
 * Created by bla5r on 15/03/2017.
 */

public class CloseSocketOut {

    public CloseSocketOut() {

    }

    public static void execute(ChannelHandlerContext ctx, Client client, int socketId) {
        ElenaProtocol.CloseSocketRequest.Builder request = ElenaProtocol.CloseSocketRequest.newBuilder();
        request.setSocketId(socketId);
        if (client.removeSocket(socketId) != null) {
            ctx.channel().writeAndFlush(MessageEnvelop.get(ElenaProtocol.PacketId.CLOSESOCKET_REQUEST, request.build().toByteString()));
        }
    }

}
