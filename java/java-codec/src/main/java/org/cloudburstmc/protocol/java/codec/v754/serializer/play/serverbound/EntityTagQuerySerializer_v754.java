package org.cloudburstmc.protocol.java.codec.v754.serializer.play.serverbound;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.common.exception.PacketSerializeException;
import org.cloudburstmc.protocol.java.codec.JavaCodecHelper;
import org.cloudburstmc.protocol.java.codec.JavaPacketSerializer;
import org.cloudburstmc.protocol.java.packet.play.serverbound.EntityTagQueryPacket;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class EntityTagQuerySerializer_v754 implements JavaPacketSerializer<EntityTagQueryPacket> {
    public static final EntityTagQuerySerializer_v754 INSTANCE = new EntityTagQuerySerializer_v754();

    @Override
    public void serialize(ByteBuf buffer, JavaCodecHelper helper, EntityTagQueryPacket packet) throws PacketSerializeException {
        helper.writeVarInt(buffer, packet.getTransactionId());
        helper.writeVarInt(buffer, packet.getEntityId());
    }

    @Override
    public void deserialize(ByteBuf buffer, JavaCodecHelper helper, EntityTagQueryPacket packet) throws PacketSerializeException {
        packet.setTransactionId(helper.readVarInt(buffer));
        packet.setEntityId(helper.readVarInt(buffer));
    }
}
