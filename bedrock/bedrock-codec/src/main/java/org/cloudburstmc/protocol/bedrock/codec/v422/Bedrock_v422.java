package org.cloudburstmc.protocol.bedrock.codec.v422;

import org.cloudburstmc.protocol.bedrock.codec.BedrockCodec;
import org.cloudburstmc.protocol.bedrock.codec.v419.Bedrock_v419;
import org.cloudburstmc.protocol.bedrock.codec.v422.serializer.FilterTextSerializer_v422;
import org.cloudburstmc.protocol.bedrock.codec.v422.serializer.ItemStackResponseSerializer_v422;
import org.cloudburstmc.protocol.bedrock.codec.v422.serializer.ResourcePacksInfoSerializer_v422;
import org.cloudburstmc.protocol.bedrock.data.inventory.stackrequestactions.StackRequestActionType;
import org.cloudburstmc.protocol.bedrock.packet.FilterTextPacket;
import org.cloudburstmc.protocol.bedrock.packet.ItemStackResponsePacket;
import org.cloudburstmc.protocol.bedrock.packet.ResourcePacksInfoPacket;
import org.cloudburstmc.protocol.common.util.TypeMap;

public class Bedrock_v422 extends Bedrock_v419 {

    protected static final TypeMap<StackRequestActionType> ITEM_STACK_REQUEST_TYPES = Bedrock_v419.ITEM_STACK_REQUEST_TYPES.toBuilder()
            .shift(12, 1)
            .insert(12, StackRequestActionType.CRAFT_RECIPE_OPTIONAL)
            .build();

    public static BedrockCodec CODEC = Bedrock_v419.CODEC.toBuilder()
            .protocolVersion(422)
            .minecraftVersion("1.16.200")
            .helper(() -> new BedrockCodecHelper_v422(ENTITY_DATA, ENTITY_DATA_TYPES, ENTITY_FLAGS, GAME_RULE_TYPES, ITEM_STACK_REQUEST_TYPES))
            .updateSerializer(ResourcePacksInfoPacket.class, ResourcePacksInfoSerializer_v422.INSTANCE)
            .updateSerializer(ItemStackResponsePacket.class, ItemStackResponseSerializer_v422.INSTANCE)
            .registerPacket(FilterTextPacket.class, FilterTextSerializer_v422.INSTANCE, 163)
            .build();

}
