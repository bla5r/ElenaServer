package com.elena.server.network.packet.incoming;

import com.elena.protocol.ElenaProtocol;
import com.elena.server.event.EventHandler;
import com.elena.server.log.Logger;
import com.elena.server.network.socket.Socket;
import com.elena.server.network.client.Client;
import com.google.protobuf.InvalidProtocolBufferException;
import io.netty.channel.ChannelHandlerContext;

/**
 * Created by bla5r on 14/03/2017.
 */

public class ForwardIn implements IPacketIn {

    public ForwardIn() {

    }

    public boolean execute(EventHandler eventHandler, ChannelHandlerContext ctx, Client client, byte[] data) {
        try {
            ElenaProtocol.ForwardPacket packet = ElenaProtocol.ForwardPacket.parseFrom(data);
            Socket socket = client.getSocket(packet.getSocketId());
            if (socket == null) {
                Logger.error("Unable to get socket #" + packet.getSocketId());
            }
            else {
                eventHandler.onRecv(client, socket, packet.getData().toByteArray());
            }
        }
        catch (InvalidProtocolBufferException e) {
            Logger.error("Unable to unpack ForwardIn");
            return (false);
        }
        return (true);
    }

}
