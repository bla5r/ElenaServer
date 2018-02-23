package com.elena.server.network.packet.outgoing;

import com.elena.protocol.ElenaProtocol;
import com.google.protobuf.ByteString;
import io.netty.channel.ChannelHandlerContext;

/**
 * Created by bla5r on 14/03/2017.
 */

public class ForwardOut {

    public ForwardOut() {

    }

    public static boolean execute(ChannelHandlerContext ctx, int socketId, byte[] data) {
        ElenaProtocol.ForwardPacket.Builder packet = ElenaProtocol.ForwardPacket.newBuilder();
        packet.setSocketId(socketId);
        packet.setData(ByteString.copyFrom(data));
        ctx.channel().writeAndFlush(MessageEnvelop.get(ElenaProtocol.PacketId.FORWARD_PACKET, packet.build().toByteString()));
        return (true);
    }

}
