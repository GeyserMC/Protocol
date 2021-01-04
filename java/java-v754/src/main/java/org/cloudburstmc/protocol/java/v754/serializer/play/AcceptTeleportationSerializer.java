package org.cloudburstmc.protocol.java.v754.serializer.play;

import com.nukkitx.network.VarInts;
import com.nukkitx.protocol.exception.PacketSerializeException;
import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.java.JavaPacketHelper;
import org.cloudburstmc.protocol.java.JavaPacketSerializer;
import org.cloudburstmc.protocol.java.packet.play.AcceptTeleportationPacket;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AcceptTeleportationSerializer implements JavaPacketSerializer<AcceptTeleportationPacket> {
    public static final AcceptTeleportationSerializer INSTANCE = new AcceptTeleportationSerializer();

    @Override
    public void serialize(ByteBuf buffer, JavaPacketHelper helper, AcceptTeleportationPacket packet) throws PacketSerializeException {
        VarInts.writeUnsignedInt(buffer, packet.getTeleportId());
    }

    @Override
    public void deserialize(ByteBuf buffer, JavaPacketHelper helper, AcceptTeleportationPacket packet) throws PacketSerializeException {
        packet.setTeleportId(VarInts.readUnsignedInt(buffer));
    }
}
