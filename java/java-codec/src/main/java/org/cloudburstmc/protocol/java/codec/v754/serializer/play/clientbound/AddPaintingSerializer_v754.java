package org.cloudburstmc.protocol.java.codec.v754.serializer.play.clientbound;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.common.util.VarInts;
import org.cloudburstmc.protocol.java.codec.JavaCodecHelper;
import org.cloudburstmc.protocol.java.codec.JavaPacketSerializer;
import org.cloudburstmc.protocol.java.data.Direction;
import org.cloudburstmc.protocol.java.data.entity.PaintingType;
import org.cloudburstmc.protocol.java.packet.play.clientbound.AddPaintingPacket;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AddPaintingSerializer_v754 implements JavaPacketSerializer<AddPaintingPacket> {
    public static final AddPaintingSerializer_v754 INSTANCE = new AddPaintingSerializer_v754();

    @Override
    public void serialize(ByteBuf buffer, JavaCodecHelper helper, AddPaintingPacket packet) {
        VarInts.writeUnsignedInt(buffer, packet.getEntityId());
        helper.writeUUID(buffer, packet.getUuid());
        VarInts.writeUnsignedInt(buffer, packet.getPainting().getId());
        helper.writeBlockPosition(buffer, packet.getPosition());
        buffer.writeByte(packet.getDirection().ordinal());
    }

    @Override
    public void deserialize(ByteBuf buffer, JavaCodecHelper helper, AddPaintingPacket packet) {
        packet.setEntityId(VarInts.readUnsignedInt(buffer));
        packet.setUuid(helper.readUUID(buffer));
        packet.setPainting(PaintingType.getById(VarInts.readUnsignedInt(buffer)));
        packet.setPosition(helper.readBlockPosition(buffer));
        packet.setDirection(Direction.getById(buffer.readUnsignedByte()));
    }
}
