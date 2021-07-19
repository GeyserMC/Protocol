package org.cloudburstmc.protocol.java.codec.v754.serializer.play.clientbound;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.common.exception.PacketSerializeException;
import org.cloudburstmc.protocol.java.codec.JavaCodecHelper;
import org.cloudburstmc.protocol.java.codec.JavaPacketSerializer;
import org.cloudburstmc.protocol.java.packet.play.clientbound.TakeItemEntityPacket;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TakeItemEntitySerializer_v754 implements JavaPacketSerializer<TakeItemEntityPacket> {
    public static final TakeItemEntitySerializer_v754 INSTANCE = new TakeItemEntitySerializer_v754();

    @Override
    public void serialize(ByteBuf buffer, JavaCodecHelper helper, TakeItemEntityPacket packet) throws PacketSerializeException {
        helper.writeVarInt(buffer, packet.getEntityId());
        helper.writeVarInt(buffer, packet.getTakerId());
        helper.writeVarInt(buffer, packet.getAmount());
    }

    @Override
    public void deserialize(ByteBuf buffer, JavaCodecHelper helper, TakeItemEntityPacket packet) throws PacketSerializeException {
        packet.setEntityId(helper.readVarInt(buffer));
        packet.setTakerId(helper.readVarInt(buffer));
        packet.setAmount(helper.readVarInt(buffer));
    }
}
