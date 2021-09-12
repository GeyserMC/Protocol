package org.cloudburstmc.protocol.java.codec.v754.serializer.play;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.common.exception.PacketSerializeException;
import org.cloudburstmc.protocol.java.codec.BidirectionalJavaPacketSerializer;
import org.cloudburstmc.protocol.java.codec.JavaCodecHelper;
import org.cloudburstmc.protocol.java.packet.play.ContainerClosePacket;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ContainerCloseSerializer_v754 extends BidirectionalJavaPacketSerializer<ContainerClosePacket> {
    public static final ContainerCloseSerializer_v754 INSTANCE = new ContainerCloseSerializer_v754();

    @Override
    public void serialize(ByteBuf buffer, JavaCodecHelper helper, ContainerClosePacket packet) throws PacketSerializeException {
        buffer.writeByte(packet.getContainerId());
    }

    @Override
    public void deserialize(ByteBuf buffer, JavaCodecHelper helper, ContainerClosePacket packet) throws PacketSerializeException {
        packet.setContainerId(buffer.readUnsignedByte());
    }
}
