package org.cloudburstmc.protocol.java.packet.play.clientbound;

import com.nukkitx.math.vector.Vector3d;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.cloudburstmc.protocol.common.PacketSignal;
import org.cloudburstmc.protocol.java.packet.JavaPacket;
import org.cloudburstmc.protocol.java.packet.handler.JavaPlayPacketHandler;
import org.cloudburstmc.protocol.java.packet.type.JavaPacketType;
import org.cloudburstmc.protocol.java.packet.type.JavaPlayPacketType;

@Data
@EqualsAndHashCode(doNotUseGetters = true, callSuper = false)
public class PlayerLookAtPacket implements JavaPacket<JavaPlayPacketHandler> {
    private Vector3d position;
    private int entityId;
    private Anchor fromAnchor;
    private Anchor toAnchor;
    private boolean atEntity;

    @Override
    public PacketSignal handle(JavaPlayPacketHandler handler) {
        return handler.handle(this);
    }

    @Override
    public JavaPacketType getPacketType() {
        return JavaPlayPacketType.PLAYER_LOOK_AT;
    }

    public enum Anchor {
        FEET,
        EYES;

        private static final Anchor[] VALUES = values();

        public static Anchor getById(int id) {
            return VALUES.length > id ? VALUES[id] : null;
        }
    }
}
