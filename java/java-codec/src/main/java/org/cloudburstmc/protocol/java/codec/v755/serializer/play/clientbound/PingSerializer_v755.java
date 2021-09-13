package org.cloudburstmc.protocol.java.codec.v755.serializer.play.clientbound;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.common.exception.PacketSerializeException;
import org.cloudburstmc.protocol.java.codec.JavaCodecHelper;
import org.cloudburstmc.protocol.java.codec.JavaPacketSerializer;
import org.cloudburstmc.protocol.java.packet.play.clientbound.PingPacket;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PingSerializer_v755 implements JavaPacketSerializer<PingPacket> {
    public static final PingSerializer_v755 INSTANCE = new PingSerializer_v755();

    @Override
    public void serialize(ByteBuf buffer, JavaCodecHelper helper, PingPacket packet) throws PacketSerializeException {
        buffer.writeInt(packet.getId());
    }

    @Override
    public void deserialize(ByteBuf buffer, JavaCodecHelper helper, PingPacket packet) throws PacketSerializeException {
        packet.setId(buffer.readInt());
    }
}
