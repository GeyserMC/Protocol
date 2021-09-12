package org.cloudburstmc.protocol.java.codec.v754.serializer.play;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.common.exception.PacketSerializeException;
import org.cloudburstmc.protocol.java.codec.BidirectionalJavaPacketSerializer;
import org.cloudburstmc.protocol.java.codec.JavaCodecHelper;
import org.cloudburstmc.protocol.java.packet.play.SetCarriedItemPacket;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class SetCarriedItemSerializer_v754 extends BidirectionalJavaPacketSerializer<SetCarriedItemPacket> {
    public static final SetCarriedItemSerializer_v754 INSTANCE = new SetCarriedItemSerializer_v754();

    @Override
    public void serialize(ByteBuf buffer, JavaCodecHelper helper, SetCarriedItemPacket packet)
            throws PacketSerializeException {
        buffer.writeShort(packet.getSlot());
    }

    @Override
    public void deserialize(ByteBuf buffer, JavaCodecHelper helper, SetCarriedItemPacket packet)
            throws PacketSerializeException {
        packet.setSlot(buffer.readShort());
    }
}
