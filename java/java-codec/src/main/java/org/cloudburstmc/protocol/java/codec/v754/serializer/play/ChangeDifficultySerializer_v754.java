package org.cloudburstmc.protocol.java.codec.v754.serializer.play;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.java.codec.BidirectionalJavaPacketSerializer;
import org.cloudburstmc.protocol.java.codec.JavaCodecHelper;
import org.cloudburstmc.protocol.java.data.world.Difficulty;
import org.cloudburstmc.protocol.java.packet.play.ChangeDifficultyPacket;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ChangeDifficultySerializer_v754 extends BidirectionalJavaPacketSerializer<ChangeDifficultyPacket> {
    public static final ChangeDifficultySerializer_v754 INSTANCE = new ChangeDifficultySerializer_v754();

    @Override
    public void serializeClientbound(ByteBuf buffer, JavaCodecHelper helper, ChangeDifficultyPacket packet) {
        buffer.writeByte(packet.getDifficulty().ordinal());
        buffer.writeBoolean(packet.isLocked());
    }

    @Override
    public void deserializeClientbound(ByteBuf buffer, JavaCodecHelper helper, ChangeDifficultyPacket packet) {
        packet.setDifficulty(Difficulty.getById(buffer.readUnsignedByte()));
        packet.setLocked(buffer.readBoolean());
    }

    @Override
    public void serializeServerbound(ByteBuf buffer, JavaCodecHelper helper, ChangeDifficultyPacket packet) {
        buffer.writeByte(packet.getDifficulty().ordinal());
    }

    @Override
    public void deserializeServerbound(ByteBuf buffer, JavaCodecHelper helper, ChangeDifficultyPacket packet) {
        packet.setDifficulty(Difficulty.getById(buffer.readUnsignedByte()));
    }
}
