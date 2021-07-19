package org.cloudburstmc.protocol.java.codec.v754.serializer.play.clientbound;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.common.exception.PacketSerializeException;
import org.cloudburstmc.protocol.java.codec.JavaCodecHelper;
import org.cloudburstmc.protocol.java.codec.JavaPacketSerializer;
import org.cloudburstmc.protocol.java.packet.play.clientbound.TabListPacket;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TabListSerializer_v754 implements JavaPacketSerializer<TabListPacket> {
    public static final TabListSerializer_v754 INSTANCE = new TabListSerializer_v754();

    @Override
    public void serialize(ByteBuf buffer, JavaCodecHelper helper, TabListPacket packet) throws PacketSerializeException {
        helper.writeComponent(buffer, packet.getHeader());
        helper.writeComponent(buffer, packet.getFooter());
    }

    @Override
    public void deserialize(ByteBuf buffer, JavaCodecHelper helper, TabListPacket packet) throws PacketSerializeException {
        packet.setHeader(helper.readComponent(buffer));
        packet.setFooter(helper.readComponent(buffer));
    }
}
