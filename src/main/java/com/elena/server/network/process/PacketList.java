package com.elena.server.network.process;

import com.elena.protocol.ElenaProtocol;
import com.elena.server.network.packet.incoming.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by bla5r on 14/03/2017.
 */

public class PacketList {

    private Map<Integer, IPacketIn> packetList = new HashMap<Integer, IPacketIn>();

    public PacketList() {
        this.packetList.put(ElenaProtocol.PacketId.HELLO_REQUEST.getNumber(), new HelloIn());
        this.packetList.put(ElenaProtocol.PacketId.OPENSOCKET_RESPONSE.getNumber(), new OpenSocketIn());
        this.packetList.put(ElenaProtocol.PacketId.CLOSESOCKET_RESPONSE.getNumber(), new CloseSocketIn());
        this.packetList.put(ElenaProtocol.PacketId.FORWARD_PACKET.getNumber(), new ForwardIn());
    }

    public IPacketIn get(Integer packetId) {
        return (this.packetList.get(packetId));
    }

}
