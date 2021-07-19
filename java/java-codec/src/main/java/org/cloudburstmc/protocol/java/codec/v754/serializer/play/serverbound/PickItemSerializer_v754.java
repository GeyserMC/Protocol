package org.cloudburstmc.protocol.java.codec.v754.serializer.play.serverbound;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.common.exception.PacketSerializeException;
import org.cloudburstmc.protocol.java.codec.JavaCodecHelper;
import org.cloudburstmc.protocol.java.codec.JavaPacketSerializer;
import org.cloudburstmc.protocol.java.packet.play.serverbound.PickItemPacket;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PickItemSerializer_v754 implements JavaPacketSerializer<PickItemPacket> {
    public static final PickItemSerializer_v754 INSTANCE = new PickItemSerializer_v754();

    @Override
    public void serialize(ByteBuf buffer, JavaCodecHelper helper, PickItemPacket packet) throws PacketSerializeException {
        helper.writeVarInt(buffer, packet.getSlot());
    }

    @Override
    public void deserialize(ByteBuf buffer, JavaCodecHelper helper, PickItemPacket packet) throws PacketSerializeException {
        packet.setSlot(helper.readVarInt(buffer));
    }
}
