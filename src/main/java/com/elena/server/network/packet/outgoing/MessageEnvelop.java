package com.elena.server.network.packet.outgoing;

import com.elena.protocol.ElenaProtocol;
import com.google.protobuf.ByteString;

/**
 * Created by bla5r on 15/03/2017.
 */

public class MessageEnvelop {

    public static ElenaProtocol.MessageEnvelop get(ElenaProtocol.PacketId packetId, ByteString content) {
        ElenaProtocol.MessageEnvelop.Builder envelop = ElenaProtocol.MessageEnvelop.newBuilder();
        envelop.setPacketId(packetId.getNumber());
        envelop.setContent(content);
        return (envelop.build());
    }

}
