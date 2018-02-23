package com.elena.server.network.client;

import com.elena.protocol.ElenaProtocol;
import com.elena.server.network.packet.outgoing.OpenSocketOut;
import com.elena.server.network.socket.Socket;
import io.netty.channel.ChannelHandlerContext;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by bla5r on 13/03/2017.
 */

public class Client {

    private boolean auth = false;
    private ChannelHandlerContext ctx;
    private Map<Integer, Socket> socketList = new HashMap<Integer, Socket>();

    public Client(ChannelHandlerContext ctx) {
        this.ctx = ctx;
    }

    public void setAuth(boolean value) {
        this.auth = value;
    }

    public boolean isAuth() {
        return (this.auth);
    }

    public Socket addSocket(Integer socketId, Socket socket) {
        return (this.socketList.put(socketId, socket));
    }

    public Socket getSocket(Integer socketId) {
        return (this.socketList.get(socketId));
    }

    public Socket removeSocket(Integer socketId) {
        return (this.socketList.remove(socketId));
    }

    public Socket openSocket(ElenaProtocol.ProtocolType type, String host, int port) {
        return (OpenSocketOut.execute(this.ctx, this, type, host, port));
    }

}
