package org.cloudburstmc.protocol.java.codec.v754.serializer.play.serverbound;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.common.exception.PacketSerializeException;
import org.cloudburstmc.protocol.java.codec.JavaCodecHelper;
import org.cloudburstmc.protocol.java.codec.JavaPacketSerializer;
import org.cloudburstmc.protocol.java.packet.play.serverbound.EditBookPacket;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class EditBookSerializer_v754 implements JavaPacketSerializer<EditBookPacket> {
    public static final EditBookSerializer_v754 INSTANCE = new EditBookSerializer_v754();

    @Override
    public void serialize(ByteBuf buffer, JavaCodecHelper helper, EditBookPacket packet) throws PacketSerializeException {
        helper.writeItemStack(buffer, packet.getBook());
        buffer.writeBoolean(packet.isSigning());
        helper.writeVarInt(buffer, packet.getSlot());
    }

    @Override
    public void deserialize(ByteBuf buffer, JavaCodecHelper helper, EditBookPacket packet) throws PacketSerializeException {
        packet.setBook(helper.readItemStack(buffer));
        packet.setSigning(buffer.readBoolean());
        packet.setSlot(helper.readVarInt(buffer));
    }
}
