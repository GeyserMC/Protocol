package org.cloudburstmc.protocol.java.codec.v754.serializer.play.clientbound;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.common.exception.PacketSerializeException;
import org.cloudburstmc.protocol.java.codec.JavaCodecHelper;
import org.cloudburstmc.protocol.java.codec.JavaPacketSerializer;
import org.cloudburstmc.protocol.java.packet.play.clientbound.SetExperiencePacket;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SetExperienceSerializer_v754 implements JavaPacketSerializer<SetExperiencePacket> {
    public static final SetExperienceSerializer_v754 INSTANCE = new SetExperienceSerializer_v754();

    @Override
    public void serialize(ByteBuf buffer, JavaCodecHelper helper, SetExperiencePacket packet) throws PacketSerializeException {
        buffer.writeFloat(packet.getExperienceProgress());
        helper.writeVarInt(buffer, packet.getTotalExperience());
        helper.writeVarInt(buffer, packet.getExperienceLevel());
    }

    @Override
    public void deserialize(ByteBuf buffer, JavaCodecHelper helper, SetExperiencePacket packet) throws PacketSerializeException {
        packet.setExperienceProgress(buffer.readFloat());
        packet.setTotalExperience(helper.readVarInt(buffer));
        packet.setExperienceLevel(helper.readVarInt(buffer));
    }
}
