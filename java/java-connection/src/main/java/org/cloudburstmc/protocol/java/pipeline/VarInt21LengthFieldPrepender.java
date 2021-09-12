package org.cloudburstmc.protocol.java.pipeline;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import org.cloudburstmc.protocol.common.util.VarInts;

public class VarInt21LengthFieldPrepender extends MessageToByteEncoder<ByteBuf> {

    @Override
    protected void encode(ChannelHandlerContext ctx, ByteBuf in, ByteBuf out) throws Exception {
        VarInts.writeUnsignedInt(out, in.readableBytes());
        out.writeBytes(in);
    }
}
