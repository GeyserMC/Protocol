package org.cloudburstmc.protocol.java.codec.v754.serializer.play.serverbound;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.common.exception.PacketSerializeException;
import org.cloudburstmc.protocol.java.codec.JavaCodecHelper;
import org.cloudburstmc.protocol.java.codec.JavaPacketSerializer;
import org.cloudburstmc.protocol.java.packet.play.serverbound.BlockEntityTagQueryPacket;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BlockEntityTagQuerySerializer_v754 implements JavaPacketSerializer<BlockEntityTagQueryPacket> {
    public static final BlockEntityTagQuerySerializer_v754 INSTANCE = new BlockEntityTagQuerySerializer_v754();

    @Override
    public void serialize(ByteBuf buffer, JavaCodecHelper helper, BlockEntityTagQueryPacket packet) throws PacketSerializeException {
        helper.writeVarInt(buffer, packet.getTransactionId());
        helper.writeBlockPosition(buffer, packet.getPosition());
    }

    @Override
    public void deserialize(ByteBuf buffer, JavaCodecHelper helper, BlockEntityTagQueryPacket packet) throws PacketSerializeException {
        packet.setTransactionId(helper.readVarInt(buffer));
        packet.setPosition(helper.readBlockPosition(buffer));
    }
}
