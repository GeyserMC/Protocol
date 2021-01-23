package org.cloudburstmc.protocol.java.v754.serializer.play.clientbound;

import com.nukkitx.math.vector.Vector3i;
import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.java.JavaPacketHelper;
import org.cloudburstmc.protocol.java.JavaPacketSerializer;
import org.cloudburstmc.protocol.java.packet.play.clientbound.ExplodePacket;

import java.util.ArrayList;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ExplodeSerializer_v754 implements JavaPacketSerializer<ExplodePacket> {
    public static final ExplodeSerializer_v754 INSTANCE = new ExplodeSerializer_v754();

    @Override
    public void serialize(ByteBuf buffer, JavaPacketHelper helper, ExplodePacket packet) {
        helper.writeVector3f(buffer, packet.getPosition());
        buffer.writeFloat(packet.getStrength());
        buffer.writeInt(packet.getRecords().size());
        for (Vector3i vector3i : packet.getRecords()) {
            helper.writeVector3i(buffer, vector3i);
        }
        helper.writeVector3f(buffer, packet.getVelocity());
    }

    @Override
    public void deserialize(ByteBuf buffer, JavaPacketHelper helper, ExplodePacket packet) {
        packet.setPosition(helper.readVector3f(buffer));
        packet.setStrength(buffer.readFloat());
        packet.setRecords(new ArrayList<>());
        int length = buffer.readInt();
        for (int i = 0; i < length; i++) {
            packet.getRecords().add(helper.readVector3i(buffer));
        }
        packet.setVelocity(helper.readVector3f(buffer));
    }
}
