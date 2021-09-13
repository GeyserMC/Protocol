package org.cloudburstmc.protocol.java.codec.v755.serializer.play.clientbound;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.common.exception.PacketSerializeException;
import org.cloudburstmc.protocol.java.codec.JavaCodecHelper;
import org.cloudburstmc.protocol.java.codec.JavaPacketSerializer;
import org.cloudburstmc.protocol.java.packet.play.clientbound.ClearTitlesPacket;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ClearTitlesSerializer_v755 implements JavaPacketSerializer<ClearTitlesPacket> {
    public static final ClearTitlesSerializer_v755 INSTANCE = new ClearTitlesSerializer_v755();

    @Override
    public void serialize(ByteBuf buffer, JavaCodecHelper helper, ClearTitlesPacket packet) throws PacketSerializeException {
        buffer.writeBoolean(packet.isResetTimes());
    }

    @Override
    public void deserialize(ByteBuf buffer, JavaCodecHelper helper, ClearTitlesPacket packet) throws PacketSerializeException {
        packet.setResetTimes(buffer.readBoolean());
    }
}
