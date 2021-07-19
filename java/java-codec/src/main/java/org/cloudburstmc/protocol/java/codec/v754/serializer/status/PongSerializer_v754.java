package org.cloudburstmc.protocol.java.codec.v754.serializer.status;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.java.codec.JavaCodecHelper;
import org.cloudburstmc.protocol.java.codec.JavaPacketSerializer;
import org.cloudburstmc.protocol.java.packet.status.PongPacket;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PongSerializer_v754 implements JavaPacketSerializer<PongPacket> {
    public static final PongSerializer_v754 INSTANCE = new PongSerializer_v754();

    @Override
    public void serialize(ByteBuf buffer, JavaCodecHelper helper, PongPacket packet) {
        buffer.writeLong(packet.getTimestamp());
    }

    @Override
    public void deserialize(ByteBuf buffer, JavaCodecHelper helper, PongPacket packet) {
        packet.setTimestamp(buffer.readLong());
    }
}
