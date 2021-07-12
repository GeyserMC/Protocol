package org.cloudburstmc.protocol.java;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.cloudburstmc.protocol.common.PacketHandler;
import org.cloudburstmc.protocol.java.packet.type.JavaPacketType;

@Getter
@Setter(AccessLevel.PACKAGE)
public abstract class BidirectionalJavaPacket<T extends PacketHandler> extends JavaPacket<T> {
    private JavaPacketType.Direction sendingDirection;
}
