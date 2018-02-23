package com.elena.server.event;

import com.elena.protocol.ElenaProtocol;
import com.elena.server.log.Logger;
import com.elena.server.network.socket.Socket;
import com.elena.server.network.client.Client;

/**
 * Created by bla5r on 14/03/2017.
 */

public class EventExample extends EventHandler {

    @Override
    public void onHello(Client client, long timestamp, String hostname, String username) {
        Logger.info("Received HelloRequest");
        Socket socket = client.openSocket(ElenaProtocol.ProtocolType.TCP, "test.rebex.net", 21);
    }

    @Override
    public void onSocketOpen(Client client, Socket socket, int errorCode) {
        Logger.info("Received OpenSocketResponse");
        if (errorCode == 0) {
            Logger.info("Send data to socket #" + socket.getSocketId());
            socket.write("USER nobody\n".getBytes());
        }
        else {
            Logger.error("Socket error code: " + errorCode);
        }
    }

    @Override
    public void onSocketClose(Client client, Socket socket, int errorCode) {
        Logger.info("Received CloseSocketIn");
    }

    @Override
    public void onRecv(Client client, Socket socket, byte[] data) {
        Logger.info("Received ForwardIn");
        Logger.info("Data received: " + new String(data));
        socket.close();
    }

}
