package com.elena.server.network.process;

import com.elena.protocol.ElenaProtocol;
import com.elena.server.event.EventHandler;
import com.elena.server.network.packet.incoming.IPacketIn;
import com.elena.server.network.client.Client;
import io.netty.channel.ChannelHandlerContext;

/**
 * Created by bla5r on 14/03/2017.
 */

public class PacketProcesser {

    private static PacketProcesser INSTANCE = new PacketProcesser();
    private PacketList packetList = new PacketList();

    private PacketProcesser() {

    }

    public static PacketProcesser getInstance() {
        return (INSTANCE);
    }

    public boolean execute(EventHandler eventHandler, ChannelHandlerContext ctx, Client client, ElenaProtocol.MessageEnvelop envelop) {
        IPacketIn packet;

        if ((packet = this.packetList.get(envelop.getPacketId())) == null) {
            return (false);
        }
        return (packet.execute(eventHandler, ctx, client, envelop.getContent().toByteArray()));
    }

}
