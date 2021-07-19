package org.cloudburstmc.protocol.java.codec.v754.serializer.play.clientbound;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.common.exception.PacketSerializeException;
import org.cloudburstmc.protocol.java.codec.JavaCodecHelper;
import org.cloudburstmc.protocol.java.codec.JavaPacketSerializer;
import org.cloudburstmc.protocol.java.packet.play.clientbound.TagQueryPacket;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TagQuerySerializer_v754 implements JavaPacketSerializer<TagQueryPacket> {
    public static final TagQuerySerializer_v754 INSTANCE = new TagQuerySerializer_v754();

    @Override
    public void serialize(ByteBuf buffer, JavaCodecHelper helper, TagQueryPacket packet) throws PacketSerializeException {
        helper.writeVarInt(buffer, packet.getTransactionId());
        helper.writeTag(buffer, packet.getTag());
    }

    @Override
    public void deserialize(ByteBuf buffer, JavaCodecHelper helper, TagQueryPacket packet) throws PacketSerializeException {
        packet.setTransactionId(helper.readVarInt(buffer));
        packet.setTag(helper.readTag(buffer));
    }
}
