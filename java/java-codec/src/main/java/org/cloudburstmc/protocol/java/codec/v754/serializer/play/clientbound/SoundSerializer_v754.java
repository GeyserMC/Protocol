package org.cloudburstmc.protocol.java.codec.v754.serializer.play.clientbound;

import com.nukkitx.math.vector.Vector3i;
import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.common.exception.PacketSerializeException;
import org.cloudburstmc.protocol.java.codec.JavaCodecHelper;
import org.cloudburstmc.protocol.java.codec.JavaPacketSerializer;
import org.cloudburstmc.protocol.java.data.SoundEvent;
import org.cloudburstmc.protocol.java.data.SoundSource;
import org.cloudburstmc.protocol.java.packet.play.clientbound.SoundPacket;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SoundSerializer_v754 implements JavaPacketSerializer<SoundPacket> {
    public static final SoundSerializer_v754 INSTANCE = new SoundSerializer_v754();

    @Override
    public void serialize(ByteBuf buffer, JavaCodecHelper helper, SoundPacket packet) throws PacketSerializeException {
        helper.writeVarInt(buffer, packet.getSound().ordinal());
        helper.writeVarInt(buffer, packet.getSource().ordinal());
        buffer.writeInt(packet.getPosition().getX() * 8);
        buffer.writeInt(packet.getPosition().getY() * 8);
        buffer.writeInt(packet.getPosition().getZ() * 8);
        buffer.writeFloat(packet.getVolume());
        buffer.writeFloat(packet.getPitch());
    }

    @Override
    public void deserialize(ByteBuf buffer, JavaCodecHelper helper, SoundPacket packet) throws PacketSerializeException {
        packet.setSound(SoundEvent.getById(helper.readVarInt(buffer)));
        packet.setSource(SoundSource.getById(helper.readVarInt(buffer)));
        packet.setPosition(Vector3i.from(
                buffer.readInt() / 8,
                buffer.readInt() / 8,
                buffer.readInt() / 8
        ));
        packet.setVolume(buffer.readFloat());
        packet.setPitch(buffer.readFloat());
    }
}
