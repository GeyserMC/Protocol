package org.cloudburstmc.protocol.java.packet.status;

import org.cloudburstmc.protocol.common.PacketSignal;
import org.cloudburstmc.protocol.java.packet.JavaPacket;
import org.cloudburstmc.protocol.java.packet.handler.JavaStatusPacketHandler;
import org.cloudburstmc.protocol.java.packet.type.JavaPacketType;
import org.cloudburstmc.protocol.java.packet.type.JavaStatusPacketType;

// Empty packet
public class StatusRequestPacket implements JavaPacket<JavaStatusPacketHandler> {

    @Override
    public PacketSignal handle(JavaStatusPacketHandler handler) {
        return handler.handle(this);
    }

    @Override
    public JavaPacketType getPacketType() {
        return JavaStatusPacketType.STATUS_REQUEST;
    }
}
