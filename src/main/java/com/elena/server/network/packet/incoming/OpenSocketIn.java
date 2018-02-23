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

public class OpenSocketIn implements IPacketIn {

    public OpenSocketIn() {

    }

    private Socket manageSocket(ElenaProtocol.OpenSocketResponse response, Client client) {
        Socket socket = null;

        if (response.getError() == ElenaProtocol.SocketError.NONE) {
            socket = client.getSocket(response.getSocketId());
            if (socket != null) {
                socket.activate();
            }
        }
        else {
            client.removeSocket(response.getSocketId());
        }
        return (socket);
    }

    public boolean execute(EventHandler eventHandler, ChannelHandlerContext ctx, Client client, byte[] data) {
        try {
            ElenaProtocol.OpenSocketResponse response = ElenaProtocol.OpenSocketResponse.parseFrom(data);
            Socket socket = this.manageSocket(response, client);
            eventHandler.onSocketOpen(client, socket, response.getError().getNumber());
        }
        catch (InvalidProtocolBufferException e) {
            Logger.error("Unable to unpack OpenSocketResponse");
            return (false);
        }
        return (true);
    }

}