package org.cloudburstmc.protocol.java.codec.v754.serializer.play.clientbound;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.common.util.VarInts;
import org.cloudburstmc.protocol.java.codec.JavaCodecHelper;
import org.cloudburstmc.protocol.java.codec.JavaPacketSerializer;
import org.cloudburstmc.protocol.java.packet.play.clientbound.BlockEventPacket;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BlockEventSerializer_v754 implements JavaPacketSerializer<BlockEventPacket> {
    public static final BlockEventSerializer_v754 INSTANCE = new BlockEventSerializer_v754();

    @Override
    public void serialize(ByteBuf buffer, JavaCodecHelper helper, BlockEventPacket packet) {
        helper.writeBlockPosition(buffer, packet.getPosition());
        buffer.writeByte(packet.getActionId());
        buffer.writeByte(packet.getActionData());
        VarInts.writeUnsignedInt(buffer, packet.getBlockState());
    }

    @Override
    public void deserialize(ByteBuf buffer, JavaCodecHelper helper, BlockEventPacket packet) {
        packet.setPosition(helper.readBlockPosition(buffer));
        packet.setActionId(buffer.readUnsignedByte());
        packet.setActionId(buffer.readUnsignedByte());
        packet.setBlockState(VarInts.readUnsignedInt(buffer));
    }
}
