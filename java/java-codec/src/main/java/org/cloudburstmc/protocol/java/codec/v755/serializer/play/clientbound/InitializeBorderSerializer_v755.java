package org.cloudburstmc.protocol.java.codec.v755.serializer.play.clientbound;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.common.exception.PacketSerializeException;
import org.cloudburstmc.protocol.java.codec.JavaCodecHelper;
import org.cloudburstmc.protocol.java.codec.JavaPacketSerializer;
import org.cloudburstmc.protocol.java.packet.play.clientbound.InitializeBorderPacket;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class InitializeBorderSerializer_v755 implements JavaPacketSerializer<InitializeBorderPacket> {
    public static final InitializeBorderSerializer_v755 INSTANCE = new InitializeBorderSerializer_v755();

    @Override
    public void serialize(ByteBuf buffer, JavaCodecHelper helper, InitializeBorderPacket packet) throws PacketSerializeException {
        buffer.writeDouble(packet.getCenterX());
        buffer.writeDouble(packet.getCenterZ());
        buffer.writeDouble(packet.getOldSize());
        buffer.writeDouble(packet.getSize());
        helper.writeVarLong(buffer, packet.getLerpTime());
        helper.writeVarInt(buffer, packet.getNewAbsoluteMaxSize());
        helper.writeVarInt(buffer, packet.getWarningBlocks());
        helper.writeVarInt(buffer, packet.getWarningTime());
    }

    @Override
    public void deserialize(ByteBuf buffer, JavaCodecHelper helper, InitializeBorderPacket packet) throws PacketSerializeException {
        packet.setCenterX(buffer.readDouble());
        packet.setCenterZ(buffer.readDouble());
        packet.setOldSize(buffer.readDouble());
        packet.setSize(buffer.readDouble());
        packet.setLerpTime(helper.readVarLong(buffer));
        packet.setNewAbsoluteMaxSize(helper.readVarInt(buffer));
        packet.setWarningBlocks(helper.readVarInt(buffer));
        packet.setWarningTime(helper.readVarInt(buffer));
    }
}
