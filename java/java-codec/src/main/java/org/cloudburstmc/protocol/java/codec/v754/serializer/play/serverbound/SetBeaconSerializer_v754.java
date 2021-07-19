package org.cloudburstmc.protocol.java.codec.v754.serializer.play.serverbound;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.common.exception.PacketSerializeException;
import org.cloudburstmc.protocol.java.codec.JavaCodecHelper;
import org.cloudburstmc.protocol.java.codec.JavaPacketSerializer;
import org.cloudburstmc.protocol.java.packet.play.serverbound.SetBeaconPacket;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SetBeaconSerializer_v754 implements JavaPacketSerializer<SetBeaconPacket> {
    public static final SetBeaconSerializer_v754 INSTANCE = new SetBeaconSerializer_v754();

    @Override
    public void serialize(ByteBuf buffer, JavaCodecHelper helper, SetBeaconPacket packet) throws PacketSerializeException {
        helper.writeVarInt(buffer, packet.getPrimary());
        helper.writeVarInt(buffer, packet.getSecondary());
    }

    @Override
    public void deserialize(ByteBuf buffer, JavaCodecHelper helper, SetBeaconPacket packet) throws PacketSerializeException {
        packet.setPrimary(helper.readVarInt(buffer));
        packet.setSecondary(helper.readVarInt(buffer));
    }
}
