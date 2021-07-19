package org.cloudburstmc.protocol.java.codec.v754.serializer.play.clientbound;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.java.codec.JavaCodecHelper;
import org.cloudburstmc.protocol.java.codec.JavaPacketSerializer;
import org.cloudburstmc.protocol.java.data.entity.EntityEventType;
import org.cloudburstmc.protocol.java.packet.play.clientbound.EntityEventPacket;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class EntityEventSerializer_v754 implements JavaPacketSerializer<EntityEventPacket> {
    public static EntityEventSerializer_v754 INSTANCE = new EntityEventSerializer_v754();

    @Override
    public void serialize(ByteBuf buffer, JavaCodecHelper helper, EntityEventPacket packet) {
        buffer.writeInt(packet.getEntityId());
        buffer.writeByte(packet.getEventId().ordinal());
    }

    @Override
    public void deserialize(ByteBuf buffer, JavaCodecHelper helper, EntityEventPacket packet) {
        packet.setEntityId(buffer.readInt());
        packet.setEventId(EntityEventType.values()[buffer.readByte()]);
    }
}
