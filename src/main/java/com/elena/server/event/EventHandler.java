package com.elena.server.event;

import com.elena.server.log.Logger;
import com.elena.server.network.socket.Socket;
import com.elena.server.network.client.Client;

/**
 * Created by bla5r on 14/03/2017.
 */

public abstract class EventHandler {

    public void onHello(Client client, long timestamp, String hostname, String username) {
        Logger.info("Received HelloRequest");
    }

    public void onSocketOpen(Client client, Socket socket, int errorCode) {
        Logger.info("[" + socket.getSocketId() +"] Received OpenSocketResponse");
    }

    public void onSocketClose(Client client, Socket socket, int errorCode) {
        Logger.info("[" + socket.getSocketId() +"] Received CloseSocketResponse");
    }

    public void onRecv(Client client, Socket socket, byte[] data) {
        Logger.info("[" + socket.getSocketId() +"] Received ForwardPacket");
    }

}
