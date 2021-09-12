package org.cloudburstmc.protocol.java.codec.v754.serializer.play.serverbound;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.common.exception.PacketSerializeException;
import org.cloudburstmc.protocol.java.codec.JavaCodecHelper;
import org.cloudburstmc.protocol.java.codec.JavaPacketSerializer;
import org.cloudburstmc.protocol.java.packet.play.serverbound.SetCreativeModeSlotPacket;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SetCreativeModeSlotSerializer_v754 implements JavaPacketSerializer<SetCreativeModeSlotPacket> {
    public static final SetCreativeModeSlotSerializer_v754 INSTANCE = new SetCreativeModeSlotSerializer_v754();

    @Override
    public void serialize(ByteBuf buffer, JavaCodecHelper helper, SetCreativeModeSlotPacket packet) throws PacketSerializeException {
        buffer.writeShort(packet.getSlot());
        helper.writeItemStack(buffer, packet.getItem());
    }

    @Override
    public void deserialize(ByteBuf buffer, JavaCodecHelper helper, SetCreativeModeSlotPacket packet) throws PacketSerializeException {
        packet.setSlot(buffer.readShort());
        packet.setItem(helper.readItemStack(buffer));
    }
}
