package org.cloudburstmc.protocol.java.packet.handler;

import org.cloudburstmc.protocol.common.PacketSignal;
import org.cloudburstmc.protocol.java.packet.handshake.HandshakingPacket;
import org.cloudburstmc.protocol.java.packet.handshake.LegacyServerListPingPacket;

public interface JavaHandshakePacketHandler extends JavaPacketHandler {

    default PacketSignal handle(HandshakingPacket packet) {
        return PacketSignal.UNHANDLED;
    }

    default PacketSignal handle(LegacyServerListPingPacket packet) {
        return PacketSignal.UNHANDLED;
    }
}
