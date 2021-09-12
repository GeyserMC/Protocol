package org.cloudburstmc.protocol.java.codec.v754.serializer.play.serverbound;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.common.exception.PacketSerializeException;
import org.cloudburstmc.protocol.java.codec.JavaCodecHelper;
import org.cloudburstmc.protocol.java.codec.JavaPacketSerializer;
import org.cloudburstmc.protocol.java.packet.play.serverbound.SignUpdatePacket;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SignUpdateSerializer_v754 implements JavaPacketSerializer<SignUpdatePacket> {
    public static final SignUpdateSerializer_v754 INSTANCE = new SignUpdateSerializer_v754();

    @Override
    public void serialize(ByteBuf buffer, JavaCodecHelper helper, SignUpdatePacket packet) throws PacketSerializeException {
        helper.writeBlockPosition(buffer, packet.getPosition());
        helper.writeArray(buffer, packet.getLines(), helper::writeString);
    }

    @Override
    public void deserialize(ByteBuf buffer, JavaCodecHelper helper, SignUpdatePacket packet) throws PacketSerializeException {
        packet.setPosition(helper.readBlockPosition(buffer));
        packet.setLines(helper.readArray(buffer, new String[0], helper::readString));
    }
}
