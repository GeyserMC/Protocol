package com.nukkitx.protocol.bedrock.v340.serializer;

import com.nukkitx.network.VarInts;
import com.nukkitx.protocol.bedrock.packet.SpawnExperienceOrbPacket;
import com.nukkitx.protocol.bedrock.v340.BedrockUtils;
import com.nukkitx.protocol.serializer.PacketSerializer;
import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SpawnExperienceOrbSerializer_v340 implements PacketSerializer<SpawnExperienceOrbPacket> {
    public static final SpawnExperienceOrbSerializer_v340 INSTANCE = new SpawnExperienceOrbSerializer_v340();


    @Override
    public void serialize(ByteBuf buffer, SpawnExperienceOrbPacket packet) {
        BedrockUtils.writeVector3f(buffer, packet.getPosition());
        VarInts.writeInt(buffer, packet.getAmount());
    }

    @Override
    public void deserialize(ByteBuf buffer, SpawnExperienceOrbPacket packet) {
        packet.setPosition(BedrockUtils.readVector3f(buffer));
        packet.setAmount(VarInts.readInt(buffer));
    }
}
