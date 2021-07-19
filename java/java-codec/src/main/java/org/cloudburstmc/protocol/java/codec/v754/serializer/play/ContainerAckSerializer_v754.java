package org.cloudburstmc.protocol.java.codec.v754.serializer.play;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.java.codec.JavaCodecHelper;
import org.cloudburstmc.protocol.java.codec.JavaPacketSerializer;
import org.cloudburstmc.protocol.java.packet.play.ContainerAckPacket;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ContainerAckSerializer_v754 implements JavaPacketSerializer<ContainerAckPacket> {
    public static final ContainerAckSerializer_v754 INSTANCE = new ContainerAckSerializer_v754();

    @Override
    public void serialize(ByteBuf buffer, JavaCodecHelper helper, ContainerAckPacket packet) {
        buffer.writeByte(packet.getContainerId());
        buffer.writeShort(packet.getActionId());
        buffer.writeBoolean(packet.isAccepted());
    }

    @Override
    public void deserialize(ByteBuf buffer, JavaCodecHelper helper, ContainerAckPacket packet) {
        packet.setContainerId(buffer.readByte());
        packet.setActionId(buffer.readShort());
        packet.setAccepted(buffer.readBoolean());
    }
}
