package org.cloudburstmc.protocol.java.codec.v754.serializer.play.serverbound;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.common.exception.PacketSerializeException;
import org.cloudburstmc.protocol.java.codec.JavaCodecHelper;
import org.cloudburstmc.protocol.java.codec.JavaPacketSerializer;
import org.cloudburstmc.protocol.java.data.Hand;
import org.cloudburstmc.protocol.java.data.text.ChatVisibility;
import org.cloudburstmc.protocol.java.packet.play.serverbound.ClientInformationPacket;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ClientInformationSerializer_v754 implements JavaPacketSerializer<ClientInformationPacket> {
    public static final ClientInformationSerializer_v754 INSTANCE = new ClientInformationSerializer_v754();

    @Override
    public void serialize(ByteBuf buffer, JavaCodecHelper helper, ClientInformationPacket packet) throws PacketSerializeException {
        helper.writeString(buffer, packet.getLanguage());
        buffer.writeByte(packet.getViewDistance());
        helper.writeVarInt(buffer, packet.getVisibility().ordinal());
        buffer.writeBoolean(packet.isChatColors());
        buffer.writeByte(packet.getModelCustomisation());
        helper.writeVarInt(buffer, packet.getMainHand().ordinal());
    }

    @Override
    public void deserialize(ByteBuf buffer, JavaCodecHelper helper, ClientInformationPacket packet)
            throws PacketSerializeException {
        packet.setLanguage(helper.readString(buffer));
        packet.setViewDistance(buffer.readByte());
        packet.setVisibility(ChatVisibility.getById(helper.readVarInt(buffer)));
        packet.setChatColors(buffer.readBoolean());
        packet.setModelCustomisation(buffer.readUnsignedByte());
        packet.setMainHand(Hand.getById(helper.readVarInt(buffer)));
    }
}
