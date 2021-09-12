package org.cloudburstmc.protocol.java.codec.v754.serializer.status;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.java.codec.JavaCodecHelper;
import org.cloudburstmc.protocol.java.codec.JavaPacketSerializer;
import org.cloudburstmc.protocol.java.packet.status.StatusRequestPacket;

// Empty packet
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class StatusRequestSerializer_v754 implements JavaPacketSerializer<StatusRequestPacket> {
    public static final StatusRequestSerializer_v754 INSTANCE = new StatusRequestSerializer_v754();

    @Override
    public void serialize(ByteBuf buffer, JavaCodecHelper helper, StatusRequestPacket packet) {
    }

    @Override
    public void deserialize(ByteBuf buffer, JavaCodecHelper helper, StatusRequestPacket packet) {
    }
}
