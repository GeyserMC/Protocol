package org.cloudburstmc.protocol.java.packet;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.cloudburstmc.protocol.common.PacketHandler;
import org.cloudburstmc.protocol.java.packet.type.JavaPacketType;

@Getter
@Setter(AccessLevel.PUBLIC)
public abstract class BidirectionalJavaPacket<T extends PacketHandler> implements JavaPacket<T> {
    JavaPacketType.Direction sendingDirection;
}
