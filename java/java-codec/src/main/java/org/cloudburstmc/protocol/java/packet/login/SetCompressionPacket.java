package org.cloudburstmc.protocol.java.packet.login;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.cloudburstmc.protocol.common.PacketSignal;
import org.cloudburstmc.protocol.java.packet.JavaPacket;
import org.cloudburstmc.protocol.java.packet.handler.JavaLoginPacketHandler;
import org.cloudburstmc.protocol.java.packet.type.JavaLoginPacketType;
import org.cloudburstmc.protocol.java.packet.type.JavaPacketType;
@Data
@EqualsAndHashCode(doNotUseGetters = true, callSuper = false)
public class SetCompressionPacket implements JavaPacket<JavaLoginPacketHandler> {
    private int compressionThreshold;

    @Override
    public PacketSignal handle(JavaLoginPacketHandler handler) {
        return handler.handle(this);
    }

    @Override
    public JavaPacketType getPacketType() {
        return JavaLoginPacketType.SET_COMPRESSION;
    }
}
