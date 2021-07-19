package org.cloudburstmc.protocol.java.codec;

import io.netty.buffer.ByteBuf;
import org.cloudburstmc.protocol.common.exception.PacketSerializeException;
import org.cloudburstmc.protocol.java.packet.JavaPacket;

public interface JavaPacketSerializer<T extends JavaPacket<?>> {

    void serialize(ByteBuf buffer, JavaCodecHelper helper, T packet) throws PacketSerializeException;

    void deserialize(ByteBuf buffer, JavaCodecHelper helper, T packet) throws PacketSerializeException;
}
