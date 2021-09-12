package org.cloudburstmc.protocol.java.codec.v754.serializer.play.serverbound;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.common.exception.PacketSerializeException;
import org.cloudburstmc.protocol.java.codec.JavaCodecHelper;
import org.cloudburstmc.protocol.java.codec.JavaPacketSerializer;
import org.cloudburstmc.protocol.java.data.Hand;
import org.cloudburstmc.protocol.java.packet.play.serverbound.UseItemPacket;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UseItemSerializer_v754 implements JavaPacketSerializer<UseItemPacket> {
    public static final UseItemSerializer_v754 INSTANCE = new UseItemSerializer_v754();

    @Override
    public void serialize(ByteBuf buffer, JavaCodecHelper helper, UseItemPacket packet) throws PacketSerializeException {
        helper.writeVarInt(buffer, packet.getHand().ordinal());
    }

    @Override
    public void deserialize(ByteBuf buffer, JavaCodecHelper helper, UseItemPacket packet) throws PacketSerializeException {
        packet.setHand(Hand.getById(helper.readVarInt(buffer)));
    }
}
