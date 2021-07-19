package org.cloudburstmc.protocol.java.codec.v754.serializer.play.clientbound;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.common.exception.PacketSerializeException;
import org.cloudburstmc.protocol.java.codec.JavaCodecHelper;
import org.cloudburstmc.protocol.java.codec.JavaPacketSerializer;
import org.cloudburstmc.protocol.java.packet.play.clientbound.OpenSignEditorPacket;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OpenSignEditorSerializer_v754 implements JavaPacketSerializer<OpenSignEditorPacket> {
    public static final OpenSignEditorSerializer_v754 INSTANCE = new OpenSignEditorSerializer_v754();

    @Override
    public void serialize(ByteBuf buffer, JavaCodecHelper helper, OpenSignEditorPacket packet) throws PacketSerializeException {
        helper.writeBlockPosition(buffer, packet.getPosition());
    }

    @Override
    public void deserialize(ByteBuf buffer, JavaCodecHelper helper, OpenSignEditorPacket packet) throws PacketSerializeException {
        packet.setPosition(helper.readBlockPosition(buffer));
    }
}
