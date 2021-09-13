package org.cloudburstmc.protocol.java.codec.v755.serializer.play.clientbound;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.common.exception.PacketSerializeException;
import org.cloudburstmc.protocol.java.codec.JavaCodecHelper;
import org.cloudburstmc.protocol.java.codec.JavaPacketSerializer;
import org.cloudburstmc.protocol.java.packet.play.clientbound.PlayerCombatEndPacket;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PlayerCombatEndSerializer_v755 implements JavaPacketSerializer<PlayerCombatEndPacket> {
    public static final PlayerCombatEndSerializer_v755 INSTANCE = new PlayerCombatEndSerializer_v755();

    @Override
    public void serialize(ByteBuf buffer, JavaCodecHelper helper, PlayerCombatEndPacket packet) throws PacketSerializeException {
        helper.writeVarInt(buffer, packet.getDuration());
        buffer.writeInt(packet.getKillerEntityId());
    }

    @Override
    public void deserialize(ByteBuf buffer, JavaCodecHelper helper, PlayerCombatEndPacket packet) throws PacketSerializeException {
        packet.setDuration(helper.readVarInt(buffer));
        packet.setKillerEntityId(buffer.readInt());
    }
}
