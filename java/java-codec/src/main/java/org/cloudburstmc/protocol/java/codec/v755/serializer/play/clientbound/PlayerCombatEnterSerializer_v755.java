package org.cloudburstmc.protocol.java.codec.v755.serializer.play.clientbound;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.common.exception.PacketSerializeException;
import org.cloudburstmc.protocol.java.codec.JavaCodecHelper;
import org.cloudburstmc.protocol.java.codec.JavaPacketSerializer;
import org.cloudburstmc.protocol.java.packet.play.clientbound.PlayerCombatEnterPacket;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PlayerCombatEnterSerializer_v755 implements JavaPacketSerializer<PlayerCombatEnterPacket> {
    public static final PlayerCombatEnterSerializer_v755 INSTANCE = new PlayerCombatEnterSerializer_v755();

    @Override
    public void serialize(ByteBuf buffer, JavaCodecHelper helper, PlayerCombatEnterPacket packet) throws PacketSerializeException {
        // no-op
    }

    @Override
    public void deserialize(ByteBuf buffer, JavaCodecHelper helper, PlayerCombatEnterPacket packet) throws PacketSerializeException {
        // no-op
    }
}
