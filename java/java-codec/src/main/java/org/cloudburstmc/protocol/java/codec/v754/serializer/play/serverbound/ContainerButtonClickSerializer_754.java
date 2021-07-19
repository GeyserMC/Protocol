package org.cloudburstmc.protocol.java.codec.v754.serializer.play.serverbound;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.common.exception.PacketSerializeException;
import org.cloudburstmc.protocol.java.codec.JavaCodecHelper;
import org.cloudburstmc.protocol.java.codec.JavaPacketSerializer;
import org.cloudburstmc.protocol.java.packet.play.serverbound.ContainerButtonClickPacket;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ContainerButtonClickSerializer_754 implements JavaPacketSerializer<ContainerButtonClickPacket> {
    public static final ContainerButtonClickSerializer_754 INSTANCE = new ContainerButtonClickSerializer_754();

    @Override
    public void serialize(ByteBuf buffer, JavaCodecHelper helper, ContainerButtonClickPacket packet) throws PacketSerializeException {
        buffer.writeByte(packet.getContainerId());
        buffer.writeByte(packet.getButtonId());
    }

    @Override
    public void deserialize(ByteBuf buffer, JavaCodecHelper helper, ContainerButtonClickPacket packet) throws PacketSerializeException {
        packet.setContainerId(buffer.readByte());
        packet.setButtonId(buffer.readByte());
    }
}
