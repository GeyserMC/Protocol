package org.cloudburstmc.protocol.java.codec.v754.serializer.play.serverbound;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.common.exception.PacketSerializeException;
import org.cloudburstmc.protocol.java.codec.JavaCodecHelper;
import org.cloudburstmc.protocol.java.codec.JavaPacketSerializer;
import org.cloudburstmc.protocol.java.packet.play.serverbound.PlayerCommandPacket;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PlayerCommandSerializer_v754 implements JavaPacketSerializer<PlayerCommandPacket> {
    public static PlayerCommandSerializer_v754 INSTANCE = new PlayerCommandSerializer_v754();

    @Override
    public void serialize(ByteBuf buffer, JavaCodecHelper helper, PlayerCommandPacket packet) throws PacketSerializeException {
        helper.writeVarInt(buffer, packet.getId());
        helper.writeVarInt(buffer, packet.getAction().ordinal());
        helper.writeVarInt(buffer, packet.getData());
    }

    @Override
    public void deserialize(ByteBuf buffer, JavaCodecHelper helper, PlayerCommandPacket packet) throws PacketSerializeException {
        packet.setId(helper.readVarInt(buffer));
        packet.setAction(PlayerCommandPacket.Action.getById(helper.readVarInt(buffer)));
        packet.setData(helper.readVarInt(buffer));
    }
}
