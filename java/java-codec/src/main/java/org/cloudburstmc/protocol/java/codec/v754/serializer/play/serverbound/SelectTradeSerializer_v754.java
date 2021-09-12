package org.cloudburstmc.protocol.java.codec.v754.serializer.play.serverbound;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.common.exception.PacketSerializeException;
import org.cloudburstmc.protocol.java.codec.JavaCodecHelper;
import org.cloudburstmc.protocol.java.codec.JavaPacketSerializer;
import org.cloudburstmc.protocol.java.packet.play.serverbound.SelectTradePacket;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SelectTradeSerializer_v754 implements JavaPacketSerializer<SelectTradePacket> {
    public static final SelectTradeSerializer_v754 INSTANCE = new SelectTradeSerializer_v754();

    @Override
    public void serialize(ByteBuf buffer, JavaCodecHelper helper, SelectTradePacket packet) throws PacketSerializeException {
        helper.writeVarInt(buffer, packet.getItem());
    }

    @Override
    public void deserialize(ByteBuf buffer, JavaCodecHelper helper, SelectTradePacket packet) throws PacketSerializeException {
        packet.setItem(helper.readVarInt(buffer));
    }
}
