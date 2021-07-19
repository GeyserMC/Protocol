package org.cloudburstmc.protocol.java.codec.v754.serializer.play.serverbound;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.common.exception.PacketSerializeException;
import org.cloudburstmc.protocol.java.codec.JavaCodecHelper;
import org.cloudburstmc.protocol.java.codec.JavaPacketSerializer;
import org.cloudburstmc.protocol.java.packet.play.serverbound.SetCommandMinecartPacket;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SetCommandMinecartSerializer_v754 implements JavaPacketSerializer<SetCommandMinecartPacket> {
    public static final SetCommandMinecartSerializer_v754 INSTANCE = new SetCommandMinecartSerializer_v754();

    @Override
    public void serialize(ByteBuf buffer, JavaCodecHelper helper, SetCommandMinecartPacket packet) throws PacketSerializeException {
        helper.writeVarInt(buffer, packet.getEntityId());
        helper.writeString(buffer, packet.getCommand());
        buffer.writeBoolean(packet.isTrackOutput());
    }

    @Override
    public void deserialize(ByteBuf buffer, JavaCodecHelper helper, SetCommandMinecartPacket packet) throws PacketSerializeException {
        packet.setEntityId(helper.readVarInt(buffer));
        packet.setCommand(helper.readString(buffer));
        packet.setTrackOutput(buffer.readBoolean());
    }
}
