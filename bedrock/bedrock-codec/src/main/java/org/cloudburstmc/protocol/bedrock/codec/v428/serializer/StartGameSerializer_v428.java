package org.cloudburstmc.protocol.bedrock.codec.v428.serializer;

import com.nukkitx.nbt.NbtMap;
import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.v419.serializer.StartGameSerializer_v419;
import org.cloudburstmc.protocol.bedrock.data.BlockPropertyData;
import org.cloudburstmc.protocol.bedrock.data.GameType;
import org.cloudburstmc.protocol.bedrock.data.SyncedPlayerMovementSettings;
import org.cloudburstmc.protocol.bedrock.data.defintions.ItemDefinition;
import org.cloudburstmc.protocol.bedrock.packet.StartGamePacket;
import org.cloudburstmc.protocol.common.util.VarInts;

@SuppressWarnings("DuplicatedCode")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class StartGameSerializer_v428 extends StartGameSerializer_v419 {

    public static final StartGameSerializer_v428 INSTANCE = new StartGameSerializer_v428();

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
        writeSyncedPlayerMovementSettings(buffer, helper, packet.getPlayerMovementSettings()); // new for v428
        buffer.writeLongLE(packet.getCurrentTick());
        VarInts.writeInt(buffer, packet.getEnchantmentSeed());

        helper.writeArray(buffer, packet.getBlockProperties(), (buf, packetHelper, block) -> {
            packetHelper.writeString(buf, block.getName());
            packetHelper.writeTag(buf, block.getProperties());
        });

        helper.writeArray(buffer, packet.getItemDefinitions(), (buf, packetHelper, entry) -> {
            packetHelper.writeString(buf, entry.getIdentifier());
            buf.writeShortLE(entry.getRuntimeId());
            buf.writeBoolean(entry.isComponentBased());
        });

        helper.writeString(buffer, packet.getMultiplayerCorrelationId());
        buffer.writeBoolean(packet.isInventoriesServerAuthoritative());
    }

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
        packet.setPlayerMovementSettings(readSyncedPlayerMovementSettings(buffer, helper)); // new for v428
        packet.setCurrentTick(buffer.readLongLE());
        packet.setEnchantmentSeed(VarInts.readInt(buffer));

        helper.readArray(buffer, packet.getBlockProperties(), (buf, packetHelper) -> {
            String name = packetHelper.readString(buf);
            NbtMap properties = packetHelper.readTag(buf, NbtMap.class);
            return new BlockPropertyData(name, properties);
        });

        helper.readArray(buffer, packet.getItemDefinitions(), (buf, packetHelper) -> {
            String identifier = packetHelper.readString(buf);
            short id = buf.readShortLE();
            boolean componentBased = buf.readBoolean();
            return new ItemDefinition(identifier, id, componentBased);
        });

        packet.setMultiplayerCorrelationId(helper.readString(buffer));
        packet.setInventoriesServerAuthoritative(buffer.readBoolean());
    }

    protected void writeSyncedPlayerMovementSettings(ByteBuf buffer, BedrockCodecHelper helper, SyncedPlayerMovementSettings playerMovementSettings) {
        VarInts.writeUnsignedInt(buffer, playerMovementSettings.getMovementMode().ordinal());
        VarInts.writeInt(buffer, playerMovementSettings.getRewindHistorySize());
        buffer.writeBoolean(playerMovementSettings.isServerAuthoritativeBlockBreaking());
    }

    protected SyncedPlayerMovementSettings readSyncedPlayerMovementSettings(ByteBuf buffer, BedrockCodecHelper helper) {
        SyncedPlayerMovementSettings playerMovementSettings = new SyncedPlayerMovementSettings();
        playerMovementSettings.setMovementMode(MOVEMENT_MODES[VarInts.readUnsignedInt(buffer)]);
        playerMovementSettings.setRewindHistorySize(VarInts.readInt(buffer));
        playerMovementSettings.setServerAuthoritativeBlockBreaking(buffer.readBoolean());
        return playerMovementSettings;
    }

}
