package org.cloudburstmc.protocol.java.packet.play.clientbound;

import it.unimi.dsi.fastutil.objects.Object2LongMap;
import lombok.Data;
import lombok.EqualsAndHashCode;
import net.kyori.adventure.key.Key;
import org.cloudburstmc.protocol.common.PacketSignal;
import org.cloudburstmc.protocol.java.data.Advancement;
import org.cloudburstmc.protocol.java.packet.JavaPacket;
import org.cloudburstmc.protocol.java.packet.handler.JavaPlayPacketHandler;
import org.cloudburstmc.protocol.java.packet.type.JavaPacketType;
import org.cloudburstmc.protocol.java.packet.type.JavaPlayPacketType;

import java.util.List;
import java.util.Map;

@Data
@EqualsAndHashCode(doNotUseGetters = true, callSuper = false)
public class UpdateAdvancementsPacket implements JavaPacket<JavaPlayPacketHandler> {
    private boolean reset;
    private List<Advancement> added;
    private List<Key> removed;
    private Map<Key, Object2LongMap<Key>> progress;

    @Override
    public PacketSignal handle(JavaPlayPacketHandler handler) {
        return handler.handle(this);
    }

    @Override
    public JavaPacketType getPacketType() {
        return JavaPlayPacketType.UPDATE_ADVANCEMENTS;
    }
}
