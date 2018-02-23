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

public class CloseSocketIn implements IPacketIn {

    public CloseSocketIn() {

    }

    public boolean execute(EventHandler eventHandler, ChannelHandlerContext ctx, Client client, byte[] data) {
        try {
            ElenaProtocol.CloseSocketResponse response = ElenaProtocol.CloseSocketResponse.parseFrom(data);
            Socket socket = client.removeSocket(response.getSocketId());
            eventHandler.onSocketClose(client, socket, response.getError().getNumber());
        }
        catch (InvalidProtocolBufferException e) {
            Logger.error("Unable to unpack CloseSocketIn");
            return (false);
        }
        return (true);
    }

}
