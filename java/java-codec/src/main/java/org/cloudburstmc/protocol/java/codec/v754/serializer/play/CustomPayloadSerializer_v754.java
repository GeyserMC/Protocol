package org.cloudburstmc.protocol.java.codec.v754.serializer.play;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.common.exception.PacketSerializeException;
import org.cloudburstmc.protocol.java.codec.BidirectionalJavaPacketSerializer;
import org.cloudburstmc.protocol.java.codec.JavaCodecHelper;
import org.cloudburstmc.protocol.java.packet.play.CustomPayloadPacket;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CustomPayloadSerializer_v754 extends BidirectionalJavaPacketSerializer<CustomPayloadPacket> {
    public static final CustomPayloadSerializer_v754 INSTANCE = new CustomPayloadSerializer_v754();

    @Override
    public void serialize(ByteBuf buffer, JavaCodecHelper helper, CustomPayloadPacket packet) throws PacketSerializeException {
        helper.writeKey(buffer, packet.getChannel());
        buffer.writeBytes(packet.getBuffer());
    }

    @Override
    public void deserialize(ByteBuf buffer, JavaCodecHelper helper, CustomPayloadPacket packet) throws PacketSerializeException {
        packet.setChannel(helper.readKey(buffer));
        packet.setBuffer(buffer.readBytes(buffer.readableBytes()).retain());
    }
}
