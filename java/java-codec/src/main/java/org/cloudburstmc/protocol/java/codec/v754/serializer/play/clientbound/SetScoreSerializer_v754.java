package org.cloudburstmc.protocol.java.codec.v754.serializer.play.clientbound;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.common.exception.PacketSerializeException;
import org.cloudburstmc.protocol.java.codec.JavaCodecHelper;
import org.cloudburstmc.protocol.java.codec.JavaPacketSerializer;
import org.cloudburstmc.protocol.java.packet.play.clientbound.SetScorePacket;

import static org.cloudburstmc.protocol.java.packet.play.clientbound.SetScorePacket.Action;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SetScoreSerializer_v754 implements JavaPacketSerializer<SetScorePacket> {
    public static final SetScoreSerializer_v754 INSTANCE = new SetScoreSerializer_v754();

    @Override
    public void serialize(ByteBuf buffer, JavaCodecHelper helper, SetScorePacket packet) throws PacketSerializeException {
        helper.writeString(buffer, packet.getOwner());
        buffer.writeByte(packet.getAction().ordinal());
        helper.writeString(buffer, packet.getObjectiveName());
        if (packet.getAction().ordinal() != 1) {
            helper.writeVarInt(buffer, packet.getScore());
        }
    }

    @Override
    public void deserialize(ByteBuf buffer, JavaCodecHelper helper, SetScorePacket packet) throws PacketSerializeException {
        packet.setOwner(helper.readString(buffer));
        packet.setAction(Action.getById(buffer.readByte()));
        packet.setObjectiveName(helper.readString(buffer));
        if (packet.getAction().ordinal() != 1) {
            packet.setScore(helper.readVarInt(buffer));
        }
    }
}
