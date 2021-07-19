package org.cloudburstmc.protocol.java.codec.v754.serializer.login;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.common.util.VarInts;
import org.cloudburstmc.protocol.java.codec.JavaCodecHelper;
import org.cloudburstmc.protocol.java.codec.JavaPacketSerializer;
import org.cloudburstmc.protocol.java.packet.login.SetCompressionPacket;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SetCompressionSerializer_v754 implements JavaPacketSerializer<SetCompressionPacket> {
    public static final SetCompressionSerializer_v754 INSTANCE = new SetCompressionSerializer_v754();

    @Override
    public void serialize(ByteBuf buffer, JavaCodecHelper helper, SetCompressionPacket packet) {
        VarInts.writeUnsignedInt(buffer, packet.getCompressionThreshold());
    }

    @Override
    public void deserialize(ByteBuf buffer, JavaCodecHelper helper, SetCompressionPacket packet) {
        packet.setCompressionThreshold(VarInts.readUnsignedInt(buffer));
    }
}
