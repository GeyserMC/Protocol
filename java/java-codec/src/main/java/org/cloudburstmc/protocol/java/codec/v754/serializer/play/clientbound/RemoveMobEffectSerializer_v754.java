package org.cloudburstmc.protocol.java.codec.v754.serializer.play.clientbound;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.common.exception.PacketSerializeException;
import org.cloudburstmc.protocol.java.codec.JavaCodecHelper;
import org.cloudburstmc.protocol.java.codec.JavaPacketSerializer;
import org.cloudburstmc.protocol.java.packet.play.clientbound.RemoveMobEffectPacket;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RemoveMobEffectSerializer_v754 implements JavaPacketSerializer<RemoveMobEffectPacket> {
    public static final RemoveMobEffectSerializer_v754 INSTANCE = new RemoveMobEffectSerializer_v754();

    @Override
    public void serialize(ByteBuf buffer, JavaCodecHelper helper, RemoveMobEffectPacket packet) throws PacketSerializeException {
        helper.writeVarInt(buffer, packet.getEntityId());
        buffer.writeByte(helper.getMobEffectId(packet.getMobEffect()));
    }

    @Override
    public void deserialize(ByteBuf buffer, JavaCodecHelper helper, RemoveMobEffectPacket packet) throws PacketSerializeException {
        packet.setEntityId(helper.readVarInt(buffer));
        packet.setMobEffect(helper.getMobEffect(buffer.readUnsignedByte()));
    }
}
