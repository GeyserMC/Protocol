package org.cloudburstmc.protocol.java.codec.v754.serializer.play.clientbound;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.common.exception.PacketSerializeException;
import org.cloudburstmc.protocol.java.codec.JavaCodecHelper;
import org.cloudburstmc.protocol.java.codec.JavaPacketSerializer;
import org.cloudburstmc.protocol.java.packet.play.clientbound.RotateHeadPacket;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RotateHeadSerializer_v754 implements JavaPacketSerializer<RotateHeadPacket> {
    public static final RotateHeadSerializer_v754 INSTANCE = new RotateHeadSerializer_v754();

    @Override
    public void serialize(ByteBuf buffer, JavaCodecHelper helper, RotateHeadPacket packet) throws PacketSerializeException {
        helper.writeVarInt(buffer, packet.getEntityId());
        buffer.writeByte(packet.getHeadYaw());
    }

    @Override
    public void deserialize(ByteBuf buffer, JavaCodecHelper helper, RotateHeadPacket packet) throws PacketSerializeException {
        packet.setEntityId(helper.readVarInt(buffer));
        packet.setHeadYaw(buffer.readByte());
    }
}
