package org.cloudburstmc.protocol.java.codec.v755.serializer.play.clientbound;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.common.exception.PacketSerializeException;
import org.cloudburstmc.protocol.java.codec.JavaCodecHelper;
import org.cloudburstmc.protocol.java.codec.JavaPacketSerializer;
import org.cloudburstmc.protocol.java.packet.play.clientbound.AddVibrationSignalPacket;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AddVibrationSignalSerializer_v755 implements JavaPacketSerializer<AddVibrationSignalPacket> {
    public static final AddVibrationSignalSerializer_v755 INSTANCE = new AddVibrationSignalSerializer_v755();

    @Override
    public void serialize(ByteBuf buffer, JavaCodecHelper helper, AddVibrationSignalPacket packet) throws PacketSerializeException {
        helper.writeBlockPosition(buffer, packet.getPosition());
        helper.writePositionSource(buffer, packet.getPositionSource());
    }

    @Override
    public void deserialize(ByteBuf buffer, JavaCodecHelper helper, AddVibrationSignalPacket packet) throws PacketSerializeException {
        packet.setPosition(helper.readBlockPosition(buffer));
        packet.setPositionSource(helper.readPositionSource(buffer));
    }
}
