package org.cloudburstmc.protocol.java.codec.v754.serializer.play.clientbound;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.common.exception.PacketSerializeException;
import org.cloudburstmc.protocol.java.codec.JavaCodecHelper;
import org.cloudburstmc.protocol.java.codec.JavaPacketSerializer;
import org.cloudburstmc.protocol.java.packet.play.clientbound.OpenScreenPacket;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OpenScreenSerializer_v754 implements JavaPacketSerializer<OpenScreenPacket> {
    public static final OpenScreenSerializer_v754 INSTANCE = new OpenScreenSerializer_v754();

    @Override
    public void serialize(ByteBuf buffer, JavaCodecHelper helper, OpenScreenPacket packet) throws PacketSerializeException {
        helper.writeVarInt(buffer, packet.getContainerId());
        helper.writeVarInt(buffer, helper.getContainerId(packet.getContainerType()));
        helper.writeComponent(buffer, packet.getTitle());
    }

    @Override
    public void deserialize(ByteBuf buffer, JavaCodecHelper helper, OpenScreenPacket packet) throws PacketSerializeException {
        packet.setContainerId(helper.readVarInt(buffer));
        packet.setContainerType(helper.getContainerType(helper.readVarInt(buffer)));
        packet.setTitle(helper.readComponent(buffer));
    }
}
