package com.elena.server.network.socket;

import com.elena.server.network.packet.outgoing.CloseSocketOut;
import com.elena.server.network.packet.outgoing.ForwardOut;
import com.elena.server.network.client.Client;
import io.netty.channel.ChannelHandlerContext;

/**
 * Created by bla5r on 14/03/2017.
 */

public class Socket {

    private ChannelHandlerContext ctx;
    private Client client;
    private int socketId;
    private boolean active;
    private String host;
    private int port;

    public Socket(ChannelHandlerContext ctx, Client client, Integer socketId, String host, int port) {
        this.ctx = ctx;
        this.client = client;
        this.socketId = socketId;
        this.host = host;
        this.port = port;
        this.active = false;
    }

    public int getSocketId() {
        return (this.socketId);
    }

    public String getHost() {
        return (this.host);
    }

    public int getPort() {
        return (this.port);
    }

    public boolean isActive() {
        return (this.active);
    }

    public void activate() {
        this.active = true;
    }

    public void write(byte[] data) {
        if (this.active) {
            ForwardOut.execute(this.ctx, this.socketId, data);
        }
    }

    public void close() {
        CloseSocketOut.execute(this.ctx, this.client, socketId);
    }

}
