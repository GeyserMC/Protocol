package org.cloudburstmc.protocol.java.codec.v754.serializer.play.serverbound;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.common.exception.PacketSerializeException;
import org.cloudburstmc.protocol.java.codec.JavaCodecHelper;
import org.cloudburstmc.protocol.java.codec.JavaPacketSerializer;
import org.cloudburstmc.protocol.java.data.Hand;
import org.cloudburstmc.protocol.java.packet.play.serverbound.InteractPacket;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class InteractSerializer_v754 implements JavaPacketSerializer<InteractPacket> {
    public static final InteractSerializer_v754 INSTANCE = new InteractSerializer_v754();

    @Override
    public void serialize(ByteBuf buffer, JavaCodecHelper helper, InteractPacket packet) throws PacketSerializeException {
        helper.writeVarInt(buffer, packet.getEntityId());
        helper.writeVarInt(buffer, packet.getAction().ordinal());
        if (packet.getAction() != InteractPacket.Action.INTERACT_AT) {
            helper.writeVarInt(buffer, packet.getHand().ordinal());
        } else {
            helper.writeVector3f(buffer, packet.getMousePosition());
        }
        buffer.writeBoolean(packet.isSecondaryAction());
    }

    @Override
    public void deserialize(ByteBuf buffer, JavaCodecHelper helper, InteractPacket packet) throws PacketSerializeException {
        packet.setEntityId(helper.readVarInt(buffer));
        packet.setAction(InteractPacket.Action.getById(helper.readVarInt(buffer)));
        if (packet.getAction() != InteractPacket.Action.INTERACT_AT) {
            packet.setHand(Hand.getById(helper.readVarInt(buffer)));
        } else {
            packet.setMousePosition(helper.readVector3f(buffer));
        }
        packet.setSecondaryAction(buffer.readBoolean());
    }
}
