package com.elena.server.network.packet.outgoing;

import com.elena.protocol.ElenaProtocol;
import com.elena.server.network.socket.Socket;
import com.elena.server.network.client.Client;
import io.netty.channel.ChannelHandlerContext;

import java.util.Random;

/**
 * Created by bla5r on 14/03/2017.
 */

public class OpenSocketOut {

    public OpenSocketOut() {

    }

    private static ElenaProtocol.RemoteHost getRemoteHost(String host, int port) {
        ElenaProtocol.RemoteHost.Builder remoteHost = ElenaProtocol.RemoteHost.newBuilder();
        remoteHost.setHost(host);
        remoteHost.setPort(port);
        return (remoteHost.build());
    }

    private static int randomId() {
        return ((new Random()).nextInt(65535));
    }

    private static Socket newSocketId(ChannelHandlerContext ctx, Client client, String host, int port) {
        int socketId = OpenSocketOut.randomId();
        Socket socket = client.getSocket(socketId);

        while (socket != null) {
            socketId = OpenSocketOut.randomId();
            socket = client.getSocket(socketId);
        }
        socket = new Socket(ctx, client, socketId, host, port);
        client.addSocket(socketId, socket);
        return (socket);
    }

    public static Socket execute(ChannelHandlerContext ctx, Client client, ElenaProtocol.ProtocolType type, String host, int port) {
        ElenaProtocol.OpenSocketRequest.Builder request = ElenaProtocol.OpenSocketRequest.newBuilder();
        Socket socket = OpenSocketOut.newSocketId(ctx, client, host, port);

        client.addSocket(socket.getSocketId(), socket);
        request.setSocketId(socket.getSocketId());
        request.setType(type);
        request.setDest(OpenSocketOut.getRemoteHost(host, port));
        ctx.channel().writeAndFlush(MessageEnvelop.get(ElenaProtocol.PacketId.OPENSOCKET_REQUEST, request.build().toByteString()));
        return (socket);
    }

}
