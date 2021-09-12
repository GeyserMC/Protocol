package org.cloudburstmc.protocol.java.codec.v754.serializer.play.clientbound;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.java.codec.JavaCodecHelper;
import org.cloudburstmc.protocol.java.codec.JavaPacketSerializer;
import org.cloudburstmc.protocol.java.packet.play.clientbound.AddExperienceOrbPacket;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AddExperienceOrbSerializer_v754 implements JavaPacketSerializer<AddExperienceOrbPacket> {
    public static final AddExperienceOrbSerializer_v754 INSTANCE = new AddExperienceOrbSerializer_v754();

    @Override
    public void serialize(ByteBuf buffer, JavaCodecHelper helper, AddExperienceOrbPacket packet) {
        helper.writeVarInt(buffer, packet.getEntityId());
        helper.writePosition(buffer, packet.getPosition());
        buffer.writeShort(packet.getAmount());
    }

    @Override
    public void deserialize(ByteBuf buffer, JavaCodecHelper helper, AddExperienceOrbPacket packet) {
        packet.setEntityId(helper.readVarInt(buffer));
        packet.setPosition(helper.readPosition(buffer));
        packet.setAmount(buffer.readShort());
    }
}
