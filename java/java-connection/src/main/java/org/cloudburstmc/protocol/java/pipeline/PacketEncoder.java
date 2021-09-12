package org.cloudburstmc.protocol.java.pipeline;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.InternalLoggerFactory;
import org.cloudburstmc.protocol.common.util.VarInts;
import org.cloudburstmc.protocol.java.JavaServerSession;
import org.cloudburstmc.protocol.java.JavaSession;
import org.cloudburstmc.protocol.java.codec.JavaCodec;
import org.cloudburstmc.protocol.java.packet.JavaPacket;

public class PacketEncoder extends MessageToByteEncoder<JavaPacket<?>> {
    private static final InternalLogger log = InternalLoggerFactory.getInstance(PacketEncoder.class);

    private final JavaSession session;
    private final boolean clientbound;

    public PacketEncoder(JavaSession session) {
        this.session = session;
        // A server sends packets that a client has to handle
        clientbound = session instanceof JavaServerSession;
    }

    @Override
    protected void encode(ChannelHandlerContext ctx, JavaPacket<?> packet, ByteBuf out) {
        JavaCodec.JavaStateCodec stateCodec = this.session.getPacketCodec().getCodec(packet.getPacketType().getState());
        //todo the following line to tryEncode
        int packetId = stateCodec.getPacketDefinition((Class<? extends JavaPacket<?>>) packet.getClass(), clientbound).getId();
        try {
            VarInts.writeUnsignedInt(out, packetId);
            stateCodec.tryEncode(helper, out, packet, clientbound);
        } catch (Throwable ex) {
            log.error("Error encoding packet: {}", packetId, ex);
        }
    }
}
