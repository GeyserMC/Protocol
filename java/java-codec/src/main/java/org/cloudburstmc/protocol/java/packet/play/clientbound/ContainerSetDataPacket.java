package org.cloudburstmc.protocol.java.packet.play.clientbound;

import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.common.PacketSignal;
import org.cloudburstmc.protocol.java.packet.JavaPacket;
import org.cloudburstmc.protocol.java.packet.handler.JavaPlayPacketHandler;
import org.cloudburstmc.protocol.java.packet.type.JavaPlayPacketType;

@Data
@EqualsAndHashCode(doNotUseGetters = true, callSuper = false)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ContainerSetDataPacket implements JavaPacket<JavaPlayPacketHandler> {
    private int containerId;
    private int property;
    private int value;

    @Override
    public PacketSignal handle(JavaPlayPacketHandler handler) {
        return handler.handle(this);
    }

    @Override
    public JavaPlayPacketType getPacketType() {
        return JavaPlayPacketType.CONTAINER_SET_DATA;
    }
}
