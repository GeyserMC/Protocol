package org.cloudburstmc.protocol.java.codec.v754.serializer.play.clientbound;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.common.exception.PacketSerializeException;
import org.cloudburstmc.protocol.java.codec.JavaCodecHelper;
import org.cloudburstmc.protocol.java.codec.JavaPacketSerializer;
import org.cloudburstmc.protocol.java.packet.play.clientbound.RemoveEntitiesPacket;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RemoveEntitiesSerializer_v754 implements JavaPacketSerializer<RemoveEntitiesPacket> {
    public static final RemoveEntitiesSerializer_v754 INSTANCE = new RemoveEntitiesSerializer_v754();

    @Override
    public void serialize(ByteBuf buffer, JavaCodecHelper helper, RemoveEntitiesPacket packet) throws PacketSerializeException {
        helper.writeVarInt(buffer, packet.getEntityIds().length);
        for (int id : packet.getEntityIds()) {
            helper.writeVarInt(buffer, id);
        }
    }

    @Override
    public void deserialize(ByteBuf buffer, JavaCodecHelper helper, RemoveEntitiesPacket packet) throws PacketSerializeException {
        int[] entityIds = new int[helper.readVarInt(buffer)];
        for (int i = 0; i < entityIds.length; i++) {
            entityIds[i] = helper.readVarInt(buffer);
        }
        packet.setEntityIds(entityIds);
    }
}
