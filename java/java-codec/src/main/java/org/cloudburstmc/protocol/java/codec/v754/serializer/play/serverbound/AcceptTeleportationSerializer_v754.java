package org.cloudburstmc.protocol.java.codec.v754.serializer.play.serverbound;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.common.exception.PacketSerializeException;
import org.cloudburstmc.protocol.common.util.VarInts;
import org.cloudburstmc.protocol.java.codec.JavaCodecHelper;
import org.cloudburstmc.protocol.java.codec.JavaPacketSerializer;
import org.cloudburstmc.protocol.java.packet.play.serverbound.AcceptTeleportationPacket;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class AcceptTeleportationSerializer_v754 implements JavaPacketSerializer<AcceptTeleportationPacket> {
    public static final AcceptTeleportationSerializer_v754 INSTANCE = new AcceptTeleportationSerializer_v754();

    @Override
    public void serialize(ByteBuf buffer, JavaCodecHelper helper, AcceptTeleportationPacket packet) throws PacketSerializeException {
        VarInts.writeUnsignedInt(buffer, packet.getId());
    }

    @Override
    public void deserialize(ByteBuf buffer, JavaCodecHelper helper, AcceptTeleportationPacket packet) throws PacketSerializeException {
        packet.setId(VarInts.readUnsignedInt(buffer));
    }
}
