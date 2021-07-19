package org.cloudburstmc.protocol.bedrock.codec.v388.serializer;

import com.nukkitx.nbt.NbtList;
import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.v361.serializer.StartGameSerializer_v361;
import org.cloudburstmc.protocol.bedrock.data.AuthoritativeMovementMode;
import org.cloudburstmc.protocol.bedrock.data.GameType;
import org.cloudburstmc.protocol.bedrock.data.defintions.ItemDefinition;
import org.cloudburstmc.protocol.bedrock.packet.StartGamePacket;
import org.cloudburstmc.protocol.common.util.VarInts;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class StartGameSerializer_v388 extends StartGameSerializer_v361 {
    public static final StartGameSerializer_v388 INSTANCE = new StartGameSerializer_v388();

    @Override
    public void serialize(ByteBuf buffer, BedrockCodecHelper helper, StartGamePacket packet) {
        VarInts.writeLong(buffer, packet.getUniqueEntityId());
        VarInts.writeUnsignedLong(buffer, packet.getRuntimeEntityId());
        VarInts.writeInt(buffer, packet.getPlayerGameType().ordinal());
        helper.writeVector3f(buffer, packet.getPlayerPosition());
        helper.writeVector2f(buffer, packet.getRotation());

        this.writeLevelSettings(buffer, helper, packet);

        helper.writeString(buffer, packet.getLevelId());
        helper.writeString(buffer, packet.getLevelName());
        helper.writeString(buffer, packet.getPremiumWorldTemplateId());
        buffer.writeBoolean(packet.isTrial());
        buffer.writeBoolean(packet.getAuthoritativeMovementMode() != AuthoritativeMovementMode.CLIENT);
        buffer.writeLongLE(packet.getCurrentTick());
        VarInts.writeInt(buffer, packet.getEnchantmentSeed());

        // cache palette for fast writing
        helper.writeTag(buffer, packet.getBlockPalette());

        helper.writeArray(buffer, packet.getItemDefinitions(), (buf, h, entry) -> {
            h.writeString(buf, entry.getIdentifier());
            buf.writeShortLE(entry.getRuntimeId());
        });

        helper.writeString(buffer, packet.getMultiplayerCorrelationId());

    }

    @SuppressWarnings("unchecked")
    @Override
    public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, StartGamePacket packet) {
        packet.setUniqueEntityId(VarInts.readLong(buffer));
        packet.setRuntimeEntityId(VarInts.readUnsignedLong(buffer));
        packet.setPlayerGameType(GameType.from(VarInts.readInt(buffer)));
        packet.setPlayerPosition(helper.readVector3f(buffer));
        packet.setRotation(helper.readVector2f(buffer));

        this.readLevelSettings(buffer, helper, packet);

        packet.setLevelId(helper.readString(buffer));
        packet.setLevelName(helper.readString(buffer));
        packet.setPremiumWorldTemplateId(helper.readString(buffer));
        packet.setTrial(buffer.readBoolean());
        packet.setAuthoritativeMovementMode(buffer.readBoolean() ? AuthoritativeMovementMode.SERVER : AuthoritativeMovementMode.CLIENT);
        packet.setCurrentTick(buffer.readLongLE());
        packet.setEnchantmentSeed(VarInts.readInt(buffer));

        packet.setBlockPalette(helper.readTag(buffer, NbtList.class));

        helper.readArray(buffer, packet.getItemDefinitions(), (buf, packetHelper) -> {
            String identifier = packetHelper.readString(buf);
            short id = buf.readShortLE();
            return new ItemDefinition(identifier, id, false);
        });

        packet.setMultiplayerCorrelationId(helper.readString(buffer));
    }

    @Override
    protected void readLevelSettings(ByteBuf buffer, BedrockCodecHelper helper, StartGamePacket packet) {
        super.readLevelSettings(buffer, helper, packet);

        packet.setVanillaVersion(helper.readString(buffer));
    }

    @Override
    protected void writeLevelSettings(ByteBuf buffer, BedrockCodecHelper helper, StartGamePacket packet) {
        super.writeLevelSettings(buffer, helper, packet);

        helper.writeString(buffer, packet.getVanillaVersion());
    }
}
