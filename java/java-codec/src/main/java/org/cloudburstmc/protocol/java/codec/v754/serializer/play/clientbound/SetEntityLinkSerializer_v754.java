package org.cloudburstmc.protocol.java.codec.v754.serializer.play.clientbound;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.common.exception.PacketSerializeException;
import org.cloudburstmc.protocol.java.codec.JavaCodecHelper;
import org.cloudburstmc.protocol.java.codec.JavaPacketSerializer;
import org.cloudburstmc.protocol.java.packet.play.clientbound.SetEntityLinkPacket;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SetEntityLinkSerializer_v754 implements JavaPacketSerializer<SetEntityLinkPacket> {
    public static final SetEntityLinkSerializer_v754 INSTANCE = new SetEntityLinkSerializer_v754();

    @Override
    public void serialize(ByteBuf buffer, JavaCodecHelper helper, SetEntityLinkPacket packet) throws PacketSerializeException {
        buffer.writeInt(packet.getEntityId());
        buffer.writeInt(packet.getAttachId());
    }

    @Override
    public void deserialize(ByteBuf buffer, JavaCodecHelper helper, SetEntityLinkPacket packet) throws PacketSerializeException {
        packet.setEntityId(buffer.readInt());
        packet.setAttachId(buffer.readInt());
    }
}
