package org.cloudburstmc.protocol.java.codec.v754.serializer.play.clientbound;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.common.exception.PacketSerializeException;
import org.cloudburstmc.protocol.java.codec.JavaCodecHelper;
import org.cloudburstmc.protocol.java.codec.JavaPacketSerializer;
import org.cloudburstmc.protocol.java.packet.play.clientbound.SelectAdvancementsTabPacket;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SelectAdvancementsTabSerializer_v754 implements JavaPacketSerializer<SelectAdvancementsTabPacket> {
    public static final SelectAdvancementsTabSerializer_v754 INSTANCE = new SelectAdvancementsTabSerializer_v754();

    @Override
    public void serialize(ByteBuf buffer, JavaCodecHelper helper, SelectAdvancementsTabPacket packet) throws PacketSerializeException {
        helper.writeOptional(buffer, packet.getTab(), helper::writeKey);
    }

    @Override
    public void deserialize(ByteBuf buffer, JavaCodecHelper helper, SelectAdvancementsTabPacket packet) throws PacketSerializeException {
        packet.setTab(helper.readOptional(buffer, helper::readKey));
    }
}
