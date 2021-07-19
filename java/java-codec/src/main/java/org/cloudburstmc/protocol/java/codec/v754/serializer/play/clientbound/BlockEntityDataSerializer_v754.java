package org.cloudburstmc.protocol.java.codec.v754.serializer.play.clientbound;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.java.codec.JavaCodecHelper;
import org.cloudburstmc.protocol.java.codec.JavaPacketSerializer;
import org.cloudburstmc.protocol.java.packet.play.clientbound.BlockEntityDataPacket;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BlockEntityDataSerializer_v754 implements JavaPacketSerializer<BlockEntityDataPacket> {
    public static final BlockEntityDataSerializer_v754 INSTANCE = new BlockEntityDataSerializer_v754();

    @Override
    public void serialize(ByteBuf buffer, JavaCodecHelper helper, BlockEntityDataPacket packet) {
        helper.writeBlockPosition(buffer, packet.getPosition());
        buffer.writeByte(packet.getAction().ordinal());
        helper.writeTag(buffer, packet.getData());
    }

    @Override
    public void deserialize(ByteBuf buffer, JavaCodecHelper helper, BlockEntityDataPacket packet) {
        packet.setPosition(helper.readBlockPosition(buffer));
        packet.setAction(helper.getBlockEntityAction(buffer.readUnsignedByte()));
        packet.setData(helper.readTag(buffer));
    }
}
