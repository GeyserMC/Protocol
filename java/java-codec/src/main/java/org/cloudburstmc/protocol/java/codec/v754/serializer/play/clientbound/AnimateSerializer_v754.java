package org.cloudburstmc.protocol.java.codec.v754.serializer.play.clientbound;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.common.util.VarInts;
import org.cloudburstmc.protocol.java.codec.JavaCodecHelper;
import org.cloudburstmc.protocol.java.codec.JavaPacketSerializer;
import org.cloudburstmc.protocol.java.data.entity.Animation;
import org.cloudburstmc.protocol.java.packet.play.clientbound.AnimatePacket;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AnimateSerializer_v754 implements JavaPacketSerializer<AnimatePacket> {
    public static final AnimateSerializer_v754 INSTANCE = new AnimateSerializer_v754();

    @Override
    public void serialize(ByteBuf buffer, JavaCodecHelper helper, AnimatePacket packet) {
        VarInts.writeUnsignedInt(buffer, packet.getPlayerId());
        buffer.writeByte(packet.getAnimation().ordinal());
    }

    @Override
    public void deserialize(ByteBuf buffer, JavaCodecHelper helper, AnimatePacket packet) {
        packet.setPlayerId(VarInts.readUnsignedInt(buffer));
        packet.setAnimation(Animation.getById(buffer.readUnsignedByte()));
    }
}
