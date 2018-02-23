package com.elena.server.network.transfer;

import com.elena.protocol.ElenaProtocol;
import com.elena.server.event.EventHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender;

/**
 * Created by bla5r on 13/03/2017.
 */

public class ServerInitializer extends ChannelInitializer<SocketChannel> {

    private EventHandler eventHandler;

    public ServerInitializer(EventHandler eventHandler) {
        this.eventHandler = eventHandler;
    }

    @Override
    public void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline p = ch.pipeline();

        p.addLast(new ProtobufVarint32FrameDecoder());
        p.addLast(new ProtobufDecoder(ElenaProtocol.MessageEnvelop.getDefaultInstance()));
        p.addLast(new ProtobufVarint32LengthFieldPrepender());
        p.addLast(new ProtobufEncoder());
        p.addLast(new ServerHandler(this.eventHandler));
    }

}
