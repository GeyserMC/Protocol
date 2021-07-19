package org.cloudburstmc.protocol.java.codec.v754.serializer.play.clientbound;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.java.codec.JavaCodecHelper;
import org.cloudburstmc.protocol.java.codec.JavaPacketSerializer;
import org.cloudburstmc.protocol.java.packet.play.clientbound.ForgetLevelChunkPacket;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ForgetLevelChunkSerializer_v754 implements JavaPacketSerializer<ForgetLevelChunkPacket> {
    public static ForgetLevelChunkSerializer_v754 INSTANCE = new ForgetLevelChunkSerializer_v754();

    @Override
    public void serialize(ByteBuf buffer, JavaCodecHelper helper, ForgetLevelChunkPacket packet) {
        buffer.writeInt(packet.getX());
        buffer.writeInt(packet.getZ());
    }

    @Override
    public void deserialize(ByteBuf buffer, JavaCodecHelper helper, ForgetLevelChunkPacket packet) {
        packet.setX(buffer.readInt());
        packet.setZ(buffer.readInt());
    }
}
