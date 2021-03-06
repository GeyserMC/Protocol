package com.nukkitx.protocol.bedrock.v388.serializer;

import com.nukkitx.protocol.bedrock.packet.CommandRequestPacket;
import com.nukkitx.protocol.bedrock.v388.BedrockUtils;
import com.nukkitx.protocol.serializer.PacketSerializer;
import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CommandRequestSerializer_v388 implements PacketSerializer<CommandRequestPacket> {
    public static final CommandRequestSerializer_v388 INSTANCE = new CommandRequestSerializer_v388();

    @Override
    public void serialize(ByteBuf buffer, CommandRequestPacket packet) {
        BedrockUtils.writeString(buffer, packet.getCommand());
        BedrockUtils.writeCommandOriginData(buffer, packet.getCommandOriginData());
        buffer.writeBoolean(packet.isInternal());
    }

    @Override
    public void deserialize(ByteBuf buffer, CommandRequestPacket packet) {
        packet.setCommand(BedrockUtils.readString(buffer));
        packet.setCommandOriginData(BedrockUtils.readCommandOriginData(buffer));
        packet.setInternal(buffer.readBoolean());
    }
}
