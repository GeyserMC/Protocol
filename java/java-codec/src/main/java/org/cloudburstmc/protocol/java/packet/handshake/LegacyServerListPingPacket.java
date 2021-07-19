package org.cloudburstmc.protocol.java.packet.handshake;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.cloudburstmc.protocol.common.PacketSignal;
import org.cloudburstmc.protocol.java.packet.JavaPacket;
import org.cloudburstmc.protocol.java.packet.handler.JavaHandshakePacketHandler;
import org.cloudburstmc.protocol.java.packet.type.JavaHandshakePacketType;
import org.cloudburstmc.protocol.java.packet.type.JavaPacketType;

@Data
@EqualsAndHashCode(doNotUseGetters = true, callSuper = false)
public class LegacyServerListPingPacket implements JavaPacket<JavaHandshakePacketHandler> {
    private byte payload;

    @Override
    public PacketSignal handle(JavaHandshakePacketHandler handler) {
        return handler.handle(this);
    }

    @Override
    public JavaPacketType getPacketType() {
        return JavaHandshakePacketType.LEGACY_SERVER_LIST_PING;
    }
}
