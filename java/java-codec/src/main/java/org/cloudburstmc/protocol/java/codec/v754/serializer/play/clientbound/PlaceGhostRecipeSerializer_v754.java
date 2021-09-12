package org.cloudburstmc.protocol.java.codec.v754.serializer.play.clientbound;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.common.exception.PacketSerializeException;
import org.cloudburstmc.protocol.java.codec.JavaCodecHelper;
import org.cloudburstmc.protocol.java.codec.JavaPacketSerializer;
import org.cloudburstmc.protocol.java.packet.play.clientbound.PlaceGhostRecipePacket;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PlaceGhostRecipeSerializer_v754 implements JavaPacketSerializer<PlaceGhostRecipePacket> {
    public static final PlaceGhostRecipeSerializer_v754 INSTANCE = new PlaceGhostRecipeSerializer_v754();

    @Override
    public void serialize(ByteBuf buffer, JavaCodecHelper helper, PlaceGhostRecipePacket packet) throws PacketSerializeException {
        buffer.writeByte(packet.getContainerId());
        helper.writeKey(buffer, packet.getRecipe());
    }

    @Override
    public void deserialize(ByteBuf buffer, JavaCodecHelper helper, PlaceGhostRecipePacket packet) throws PacketSerializeException {
        packet.setContainerId(buffer.readByte());
        packet.setRecipe(helper.readKey(buffer));
    }
}
