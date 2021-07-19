package org.cloudburstmc.protocol.java.codec.v754.serializer.play.clientbound;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.common.exception.PacketSerializeException;
import org.cloudburstmc.protocol.java.codec.JavaCodecHelper;
import org.cloudburstmc.protocol.java.codec.JavaPacketSerializer;
import org.cloudburstmc.protocol.java.data.SoundEvent;
import org.cloudburstmc.protocol.java.data.SoundSource;
import org.cloudburstmc.protocol.java.packet.play.clientbound.SoundEntityPacket;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SoundEntitySerializer_v754 implements JavaPacketSerializer<SoundEntityPacket> {
    public static final SoundEntitySerializer_v754 INSTANCE = new SoundEntitySerializer_v754();

    @Override
    public void serialize(ByteBuf buffer, JavaCodecHelper helper, SoundEntityPacket packet) throws PacketSerializeException {
        helper.writeVarInt(buffer, packet.getSound().ordinal());
        helper.writeVarInt(buffer, packet.getSource().ordinal());
        helper.writeVarInt(buffer, packet.getEntityId());
        buffer.writeFloat(packet.getVolume());
        buffer.writeFloat(packet.getPitch());
    }

    @Override
    public void deserialize(ByteBuf buffer, JavaCodecHelper helper, SoundEntityPacket packet) throws PacketSerializeException {
        packet.setSound(SoundEvent.getById(helper.readVarInt(buffer)));
        packet.setSource(SoundSource.getById(helper.readVarInt(buffer)));
        packet.setEntityId(helper.readVarInt(buffer));
        packet.setVolume(buffer.readFloat());
        packet.setPitch(buffer.readFloat());
    }
}
