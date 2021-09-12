package org.cloudburstmc.protocol.java.packet.status;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.cloudburstmc.protocol.common.PacketSignal;
import org.cloudburstmc.protocol.java.packet.JavaPacket;
import org.cloudburstmc.protocol.java.packet.handler.JavaStatusPacketHandler;
import org.cloudburstmc.protocol.java.packet.type.JavaPacketType;
import org.cloudburstmc.protocol.java.packet.type.JavaStatusPacketType;

@Data
@EqualsAndHashCode(doNotUseGetters = true, callSuper = false)
public class PongPacket implements JavaPacket<JavaStatusPacketHandler> {
    private long timestamp;

    @Override
    public PacketSignal handle(JavaStatusPacketHandler handler) {
        return handler.handle(this);
    }

    @Override
    public JavaPacketType getPacketType() {
        return JavaStatusPacketType.PONG;
    }
}
