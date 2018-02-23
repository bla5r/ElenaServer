package com.elena.server.network.packet.incoming;

import com.elena.server.event.EventHandler;
import com.elena.server.network.client.Client;
import io.netty.channel.ChannelHandlerContext;

/**
 * Created by bla5r on 14/03/2017.
 */

public interface IPacketIn {
    boolean execute(EventHandler eventHandler, ChannelHandlerContext ctx, Client client, byte[] data);
}
