package org.cloudburstmc.protocol.java.codec.v754.serializer.play.clientbound;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.common.exception.PacketSerializeException;
import org.cloudburstmc.protocol.java.codec.JavaCodecHelper;
import org.cloudburstmc.protocol.java.codec.JavaPacketSerializer;
import org.cloudburstmc.protocol.java.packet.play.clientbound.TeleportEntityPacket;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TeleportEntitySerializer_v754 implements JavaPacketSerializer<TeleportEntityPacket> {
    public static final TeleportEntitySerializer_v754 INSTANCE = new TeleportEntitySerializer_v754();

    @Override
    public void serialize(ByteBuf buffer, JavaCodecHelper helper, TeleportEntityPacket packet) throws PacketSerializeException {
        helper.writeVarInt(buffer, packet.getEntityId());
        helper.writePosition(buffer, packet.getPosition());
        helper.writeRotation2f(buffer, packet.getRotation());
        buffer.writeBoolean(packet.isOnGround());
    }

    @Override
    public void deserialize(ByteBuf buffer, JavaCodecHelper helper, TeleportEntityPacket packet) throws PacketSerializeException {
        packet.setEntityId(helper.readVarInt(buffer));
        packet.setPosition(helper.readPosition(buffer));
        packet.setRotation(helper.readRotation2f(buffer));
        packet.setOnGround(buffer.readBoolean());
    }
}
