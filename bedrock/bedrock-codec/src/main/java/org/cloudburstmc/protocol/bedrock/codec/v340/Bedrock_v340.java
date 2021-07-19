package org.cloudburstmc.protocol.bedrock.codec.v340;

import org.cloudburstmc.protocol.bedrock.codec.BedrockCodec;
import org.cloudburstmc.protocol.bedrock.codec.v291.serializer.LevelEventSerializer_v291;
import org.cloudburstmc.protocol.bedrock.codec.v291.serializer.LevelSoundEvent1Serializer_v291;
import org.cloudburstmc.protocol.bedrock.codec.v313.serializer.LevelSoundEvent2Serializer_v313;
import org.cloudburstmc.protocol.bedrock.codec.v332.Bedrock_v332;
import org.cloudburstmc.protocol.bedrock.codec.v332.serializer.LevelSoundEventSerializer_v332;
import org.cloudburstmc.protocol.bedrock.codec.v340.serializer.*;
import org.cloudburstmc.protocol.bedrock.data.SoundEvent;
import org.cloudburstmc.protocol.bedrock.data.command.CommandParam;
import org.cloudburstmc.protocol.bedrock.data.entity.EntityData;
import org.cloudburstmc.protocol.bedrock.data.entity.EntityFlag;
import org.cloudburstmc.protocol.bedrock.packet.*;
import org.cloudburstmc.protocol.common.util.TypeMap;

public class Bedrock_v340 extends Bedrock_v332 {

    protected static final TypeMap<CommandParam> COMMAND_PARAMS = Bedrock_v332.COMMAND_PARAMS.toBuilder()
            .shift(28, -1)
            .build();

    protected static final TypeMap<EntityData> ENTITY_DATA = Bedrock_v332.ENTITY_DATA.toBuilder()
            .replace(39, EntityData.HAS_NPC_COMPONENT)
            .insert(99, EntityData.INTERACTIVE_TAG)
            .insert(100, EntityData.TRADE_TIER)
            .insert(101, EntityData.MAX_TRADE_TIER)
            .build();

    protected static final TypeMap<EntityFlag> ENTITY_FLAGS = Bedrock_v332.ENTITY_FLAGS.toBuilder()
            .insert(71, EntityFlag.BLOCKING)
            .insert(72, EntityFlag.TRANSITION_BLOCKING)
            .insert(73, EntityFlag.BLOCKED_USING_SHIELD)
            .insert(74, EntityFlag.SLEEPING)
            .insert(75, EntityFlag.WANTS_TO_WAKE)
            .insert(76, EntityFlag.TRADE_INTEREST)
            .insert(77, EntityFlag.DOOR_BREAKER)
            .insert(78, EntityFlag.BREAKING_OBSTRUCTION)
            .insert(79, EntityFlag.DOOR_OPENER)
            .build();

    protected static final TypeMap<SoundEvent> SOUND_EVENTS = Bedrock_v332.SOUND_EVENTS.toBuilder()
            .replace(255, SoundEvent.SHIELD_BLOCK)
            .insert(256, SoundEvent.LECTERN_BOOK_PLACE)
            .insert(257, SoundEvent.UNDEFINED)
            .build();

    public static final BedrockCodec CODEC = Bedrock_v332.CODEC.toBuilder()
            .protocolVersion(340)
            .minecraftVersion("1.10.0")
            .helper(() -> new BedrockCodecHelper_v340(ENTITY_DATA, ENTITY_DATA_TYPES, ENTITY_FLAGS, GAME_RULE_TYPES))
            .updateSerializer(EventPacket.class, EventSerializer_v340.INSTANCE)
            .updateSerializer(AvailableCommandsPacket.class, new AvailableCommandsSerializer_v340(COMMAND_PARAMS))
            .updateSerializer(StructureBlockUpdatePacket.class, StructureBlockUpdateSerializer_v340.INSTANCE)
            .updateSerializer(LevelEventPacket.class, new LevelEventSerializer_v291(LEVEL_EVENTS))
            .updateSerializer(LevelSoundEvent1Packet.class, new LevelSoundEvent1Serializer_v291(SOUND_EVENTS))
            .updateSerializer(LevelSoundEvent2Packet.class, new LevelSoundEvent2Serializer_v313(SOUND_EVENTS))
            .updateSerializer(LevelSoundEventPacket.class, new LevelSoundEventSerializer_v332(SOUND_EVENTS))
            .registerPacket(LecternUpdatePacket.class, LecternUpdateSerializer_v340.INSTANCE, 124)
            .registerPacket(VideoStreamConnectPacket.class, VideoStreamConnectSerializer_v340.INSTANCE, 125)
            .build();
}
