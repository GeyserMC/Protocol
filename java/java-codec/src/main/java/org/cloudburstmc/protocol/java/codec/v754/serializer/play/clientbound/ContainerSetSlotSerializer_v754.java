package org.cloudburstmc.protocol.java.codec.v754.serializer.play.clientbound;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.java.codec.JavaCodecHelper;
import org.cloudburstmc.protocol.java.codec.JavaPacketSerializer;
import org.cloudburstmc.protocol.java.packet.play.clientbound.ContainerSetSlotPacket;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ContainerSetSlotSerializer_v754 implements JavaPacketSerializer<ContainerSetSlotPacket> {
    public static final ContainerSetSlotSerializer_v754 INSTANCE = new ContainerSetSlotSerializer_v754();

    @Override
    public void serialize(ByteBuf buffer, JavaCodecHelper helper, ContainerSetSlotPacket packet) {
        buffer.writeByte(packet.getContainerId());
        buffer.writeShort(packet.getSlot());
        helper.writeItemStack(buffer, packet.getItem());
    }

    @Override
    public void deserialize(ByteBuf buffer, JavaCodecHelper helper, ContainerSetSlotPacket packet) {
        packet.setContainerId(buffer.readUnsignedByte());
        packet.setSlot(buffer.readShort());
        packet.setItem(helper.readItemStack(buffer));
    }
}
