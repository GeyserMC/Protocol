package org.cloudburstmc.protocol.java.codec.v755.serializer.play.clientbound;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.common.exception.PacketSerializeException;
import org.cloudburstmc.protocol.java.codec.JavaCodecHelper;
import org.cloudburstmc.protocol.java.codec.JavaPacketSerializer;
import org.cloudburstmc.protocol.java.packet.play.clientbound.PlayerCombatKillPacket;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PlayerCombatKillSerializer_v755 implements JavaPacketSerializer<PlayerCombatKillPacket> {
    public static final PlayerCombatKillSerializer_v755 INSTANCE = new PlayerCombatKillSerializer_v755();

    @Override
    public void serialize(ByteBuf buffer, JavaCodecHelper helper, PlayerCombatKillPacket packet) throws PacketSerializeException {
        helper.writeVarInt(buffer, packet.getPlayerEntityId());
        buffer.writeInt(packet.getKillerEntityId());
        helper.writeComponent(buffer, packet.getMessage());
    }

    @Override
    public void deserialize(ByteBuf buffer, JavaCodecHelper helper, PlayerCombatKillPacket packet) throws PacketSerializeException {
        packet.setPlayerEntityId(helper.readVarInt(buffer));
        packet.setKillerEntityId(buffer.readInt());
        packet.setMessage(helper.readComponent(buffer));
    }
}
