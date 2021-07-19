package org.cloudburstmc.protocol.java.codec.v754.serializer.play.clientbound;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.common.exception.PacketSerializeException;
import org.cloudburstmc.protocol.java.codec.JavaCodecHelper;
import org.cloudburstmc.protocol.java.codec.JavaPacketSerializer;
import org.cloudburstmc.protocol.java.data.DisplaySlot;
import org.cloudburstmc.protocol.java.packet.play.clientbound.SetDisplayObjectivePacket;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SetDisplayObjectiveSerializer_v754 implements JavaPacketSerializer<SetDisplayObjectivePacket> {
    public static final SetDisplayObjectiveSerializer_v754 INSTANCE = new SetDisplayObjectiveSerializer_v754();

    @Override
    public void serialize(ByteBuf buffer, JavaCodecHelper helper, SetDisplayObjectivePacket packet) throws PacketSerializeException {
        buffer.writeByte(packet.getSlot().ordinal());
        helper.writeString(buffer, packet.getObjectiveName());
    }

    @Override
    public void deserialize(ByteBuf buffer, JavaCodecHelper helper, SetDisplayObjectivePacket packet) throws PacketSerializeException {
        packet.setSlot(DisplaySlot.getById(buffer.readByte()));
        packet.setObjectiveName(helper.readString(buffer));
    }
}
