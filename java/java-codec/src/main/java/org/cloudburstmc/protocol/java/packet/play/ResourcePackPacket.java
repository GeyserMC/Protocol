package org.cloudburstmc.protocol.java.packet.play;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.cloudburstmc.protocol.common.PacketSignal;
import org.cloudburstmc.protocol.java.packet.BidirectionalJavaPacket;
import org.cloudburstmc.protocol.java.annotation.DirectionAvailability;
import org.cloudburstmc.protocol.java.packet.handler.JavaPlayPacketHandler;
import org.cloudburstmc.protocol.java.packet.type.JavaPacketType;
import org.cloudburstmc.protocol.java.packet.type.JavaPlayPacketType;

@Data
@EqualsAndHashCode(doNotUseGetters = true, callSuper = false)
public class ResourcePackPacket implements BidirectionalJavaPacket<JavaPlayPacketHandler> {
    @DirectionAvailability(JavaPacketType.Direction.CLIENTBOUND)
    private String url;

    @DirectionAvailability(JavaPacketType.Direction.CLIENTBOUND)
    private String hash;

    @DirectionAvailability(JavaPacketType.Direction.SERVERBOUND)
    private Action action;

    @Override
    public PacketSignal handle(JavaPlayPacketHandler handler) {
        return handler.handle(this);
    }

    @Override
    public JavaPacketType getPacketType() {
        return JavaPlayPacketType.RESOURCE_PACK;
    }

    public enum Action {
        SUCCESSFULLY_LOADED,
        DECLINED,
        FAILED_DOWNLOAD,
        ACCEPTED;

        private static final Action[] VALUES = values();

        public static Action getById(int id) {
            return VALUES.length > id ? VALUES[id] : null;
        }
    }
}
