package org.cloudburstmc.protocol.java.codec.v754.serializer.play;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.common.exception.PacketSerializeException;
import org.cloudburstmc.protocol.java.codec.BidirectionalJavaPacketSerializer;
import org.cloudburstmc.protocol.java.codec.JavaCodecHelper;
import org.cloudburstmc.protocol.java.packet.play.KeepAlivePacket;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class KeepAliveSerializer_v754 extends BidirectionalJavaPacketSerializer<KeepAlivePacket> {
    public static final KeepAliveSerializer_v754 INSTANCE = new KeepAliveSerializer_v754();

    @Override
    public void serialize(ByteBuf buffer, JavaCodecHelper helper, KeepAlivePacket packet) throws PacketSerializeException {
        buffer.writeLong(packet.getId());
    }

    @Override
    public void deserialize(ByteBuf buffer, JavaCodecHelper helper, KeepAlivePacket packet) throws PacketSerializeException {
        packet.setId(buffer.readLong());
    }
}
