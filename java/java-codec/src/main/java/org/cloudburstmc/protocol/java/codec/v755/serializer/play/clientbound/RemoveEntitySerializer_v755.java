package org.cloudburstmc.protocol.java.codec.v755.serializer.play.clientbound;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.common.exception.PacketSerializeException;
import org.cloudburstmc.protocol.java.codec.JavaCodecHelper;
import org.cloudburstmc.protocol.java.codec.JavaPacketSerializer;
import org.cloudburstmc.protocol.java.packet.play.clientbound.RemoveEntityPacket;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RemoveEntitySerializer_v755 implements JavaPacketSerializer<RemoveEntityPacket> {
    public static final RemoveEntitySerializer_v755 INSTANCE = new RemoveEntitySerializer_v755();

    @Override
    public void serialize(ByteBuf buffer, JavaCodecHelper helper, RemoveEntityPacket packet) throws PacketSerializeException {
        helper.writeVarInt(buffer, packet.getEntityId());
    }

    @Override
    public void deserialize(ByteBuf buffer, JavaCodecHelper helper, RemoveEntityPacket packet) throws PacketSerializeException {
        packet.setEntityId(helper.readVarInt(buffer));
    }
}
