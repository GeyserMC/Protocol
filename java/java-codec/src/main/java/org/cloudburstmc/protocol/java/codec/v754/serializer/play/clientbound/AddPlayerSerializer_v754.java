package org.cloudburstmc.protocol.java.codec.v754.serializer.play.clientbound;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.common.util.VarInts;
import org.cloudburstmc.protocol.java.codec.JavaCodecHelper;
import org.cloudburstmc.protocol.java.codec.JavaPacketSerializer;
import org.cloudburstmc.protocol.java.packet.play.clientbound.AddPlayerPacket;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AddPlayerSerializer_v754 implements JavaPacketSerializer<AddPlayerPacket> {
    public static final AddPlayerSerializer_v754 INSTANCE = new AddPlayerSerializer_v754();

    @Override
    public void serialize(ByteBuf buffer, JavaCodecHelper helper, AddPlayerPacket packet) {
        VarInts.writeUnsignedInt(buffer, packet.getEntityId());
        helper.writeUUID(buffer, packet.getUuid());
        helper.writePosition(buffer, packet.getPosition());
        helper.writeRotation2f(buffer, packet.getRotation());
    }

    @Override
    public void deserialize(ByteBuf buffer, JavaCodecHelper helper, AddPlayerPacket packet) {
        packet.setEntityId(VarInts.readUnsignedInt(buffer));
        packet.setUuid(helper.readUUID(buffer));
        packet.setPosition(helper.readPosition(buffer));
        packet.setRotation(helper.readRotation2f(buffer));
    }
}
