package org.cloudburstmc.protocol.java.packet.handler;

import org.cloudburstmc.protocol.common.PacketSignal;
import org.cloudburstmc.protocol.java.packet.status.PingPacket;
import org.cloudburstmc.protocol.java.packet.status.PongPacket;
import org.cloudburstmc.protocol.java.packet.status.StatusRequestPacket;
import org.cloudburstmc.protocol.java.packet.status.StatusResponsePacket;

public interface JavaStatusPacketHandler extends JavaPacketHandler {

    default PacketSignal handle(StatusResponsePacket packet) {
        return PacketSignal.UNHANDLED;
    }

    default PacketSignal handle(PongPacket packet) {
        return PacketSignal.UNHANDLED;
    }

    default PacketSignal handle(StatusRequestPacket packet) {
        return PacketSignal.UNHANDLED;
    }

    default PacketSignal handle(PingPacket packet) {
        return PacketSignal.UNHANDLED;
    }
}
