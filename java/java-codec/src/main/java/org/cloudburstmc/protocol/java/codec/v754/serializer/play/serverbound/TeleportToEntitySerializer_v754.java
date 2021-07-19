package org.cloudburstmc.protocol.java.codec.v754.serializer.play.serverbound;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.common.exception.PacketSerializeException;
import org.cloudburstmc.protocol.java.codec.JavaCodecHelper;
import org.cloudburstmc.protocol.java.codec.JavaPacketSerializer;
import org.cloudburstmc.protocol.java.packet.play.serverbound.TeleportToEntityPacket;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TeleportToEntitySerializer_v754 implements JavaPacketSerializer<TeleportToEntityPacket> {
    public static final TeleportToEntitySerializer_v754 INSTANCE = new TeleportToEntitySerializer_v754();

    @Override
    public void serialize(ByteBuf buffer, JavaCodecHelper helper, TeleportToEntityPacket packet) throws PacketSerializeException {
        helper.writeUUID(buffer, packet.getEntityUuid());
    }

    @Override
    public void deserialize(ByteBuf buffer, JavaCodecHelper helper, TeleportToEntityPacket packet) throws PacketSerializeException {
        packet.setEntityUuid(helper.readUUID(buffer));
    }
}
