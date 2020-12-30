package org.cloudburstmc.protocol.java.packet.play;

import com.nukkitx.math.vector.Vector3i;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.cloudburstmc.protocol.java.JavaPacket;
import org.cloudburstmc.protocol.java.handler.JavaPlayPacketHandler;
import org.cloudburstmc.protocol.java.packet.type.JavaPacketType;
import org.cloudburstmc.protocol.java.packet.type.JavaPlayPacketType;

@Data
@EqualsAndHashCode(doNotUseGetters = true, callSuper = false)
public class BlockDestructionPacket extends JavaPacket<JavaPlayPacketHandler> {
    private int entityId;
    private Vector3i position;
    private byte destroyStage;

    @Override
    public boolean handle(JavaPlayPacketHandler handler) {
        return handler.handle(this);
    }

    @Override
    public JavaPacketType getPacketType() {
        return JavaPlayPacketType.BLOCK_DESTRUCTION_S2C;
    }
}