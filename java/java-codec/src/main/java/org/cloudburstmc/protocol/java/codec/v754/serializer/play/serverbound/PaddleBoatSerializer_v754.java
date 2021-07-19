package org.cloudburstmc.protocol.java.codec.v754.serializer.play.serverbound;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.common.exception.PacketSerializeException;
import org.cloudburstmc.protocol.java.codec.JavaCodecHelper;
import org.cloudburstmc.protocol.java.codec.JavaPacketSerializer;
import org.cloudburstmc.protocol.java.packet.play.serverbound.PaddleBoatPacket;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PaddleBoatSerializer_v754 implements JavaPacketSerializer<PaddleBoatPacket> {
    public static final PaddleBoatSerializer_v754 INSTANCE = new PaddleBoatSerializer_v754();

    @Override
    public void serialize(ByteBuf buffer, JavaCodecHelper helper, PaddleBoatPacket packet) throws PacketSerializeException {
        buffer.writeBoolean(packet.isLeft());
        buffer.writeBoolean(packet.isRight());
    }

    @Override
    public void deserialize(ByteBuf buffer, JavaCodecHelper helper, PaddleBoatPacket packet) throws PacketSerializeException {
        packet.setLeft(buffer.readBoolean());
        packet.setRight(buffer.readBoolean());
    }
}
