package com.nukkitx.protocol.bedrock.v361.serializer;

import com.nukkitx.network.VarInts;
import com.nukkitx.protocol.bedrock.packet.SetEntityDataPacket;
import com.nukkitx.protocol.bedrock.v361.BedrockUtils;
import com.nukkitx.protocol.serializer.PacketSerializer;
import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SetEntityDataSerializer_v361 implements PacketSerializer<SetEntityDataPacket> {
    public static final SetEntityDataSerializer_v361 INSTANCE = new SetEntityDataSerializer_v361();


    @Override
    public void serialize(ByteBuf buffer, SetEntityDataPacket packet) {
        VarInts.writeUnsignedLong(buffer, packet.getRuntimeEntityId());
        BedrockUtils.writeEntityData(buffer, packet.getMetadata());
    }

    @Override
    public void deserialize(ByteBuf buffer, SetEntityDataPacket packet) {
        packet.setRuntimeEntityId(VarInts.readUnsignedLong(buffer));
        BedrockUtils.readEntityData(buffer, packet.getMetadata());
    }
}
