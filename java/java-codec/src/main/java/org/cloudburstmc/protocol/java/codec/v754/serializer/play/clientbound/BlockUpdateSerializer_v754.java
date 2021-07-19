package org.cloudburstmc.protocol.java.codec.v754.serializer.play.clientbound;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.common.util.VarInts;
import org.cloudburstmc.protocol.java.codec.JavaCodecHelper;
import org.cloudburstmc.protocol.java.codec.JavaPacketSerializer;
import org.cloudburstmc.protocol.java.packet.play.clientbound.BlockUpdatePacket;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BlockUpdateSerializer_v754 implements JavaPacketSerializer<BlockUpdatePacket> {
    public static final BlockUpdateSerializer_v754 INSTANCE = new BlockUpdateSerializer_v754();

    @Override
    public void serialize(ByteBuf buffer, JavaCodecHelper helper, BlockUpdatePacket packet) {
        helper.writeBlockPosition(buffer, packet.getPosition());
        VarInts.writeUnsignedInt(buffer, packet.getBlockState());
    }

    @Override
    public void deserialize(ByteBuf buffer, JavaCodecHelper helper, BlockUpdatePacket packet) {
        packet.setPosition(helper.readBlockPosition(buffer));
        packet.setBlockState(VarInts.readUnsignedInt(buffer));
    }
}
