package org.cloudburstmc.protocol.java.packet.play.clientbound;

import com.nukkitx.nbt.NbtMap;
import lombok.Data;
import lombok.EqualsAndHashCode;
import net.kyori.adventure.key.Key;
import org.cloudburstmc.protocol.common.PacketSignal;
import org.cloudburstmc.protocol.java.data.GameType;
import org.cloudburstmc.protocol.java.packet.JavaPacket;
import org.cloudburstmc.protocol.java.packet.handler.JavaPlayPacketHandler;
import org.cloudburstmc.protocol.java.packet.type.JavaPacketType;
import org.cloudburstmc.protocol.java.packet.type.JavaPlayPacketType;

@Data
@EqualsAndHashCode(doNotUseGetters = true, callSuper = false)
public class RespawnPacket implements JavaPacket<JavaPlayPacketHandler> {
    private Key dimensionType;
    private NbtMap dimension;
    private long seed;
    private GameType gameType;
    private GameType previousGameType;
    private boolean debug;
    private boolean flat;
    private boolean keepAllPlayerData;

    @Override
    public PacketSignal handle(JavaPlayPacketHandler handler) {
        return handler.handle(this);
    }

    @Override
    public JavaPacketType getPacketType() {
        return JavaPlayPacketType.RESPAWN;
    }
}
