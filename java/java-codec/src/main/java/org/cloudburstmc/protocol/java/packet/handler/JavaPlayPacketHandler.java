package org.cloudburstmc.protocol.java.packet.handler;

import org.cloudburstmc.protocol.common.PacketSignal;
import org.cloudburstmc.protocol.java.packet.play.*;
import org.cloudburstmc.protocol.java.packet.play.clientbound.*;
import org.cloudburstmc.protocol.java.packet.play.serverbound.*;

public interface JavaPlayPacketHandler extends JavaPacketHandler {
    // Clientbound packets
    default PacketSignal handle(AddEntityPacket packet) {
        return PacketSignal.UNHANDLED;
    }

    default PacketSignal handle(AddExperienceOrbPacket packet) {
        return PacketSignal.UNHANDLED;
    }

    default PacketSignal handle(AddMobPacket packet) {
        return PacketSignal.UNHANDLED;
    }

    default PacketSignal handle(AddPaintingPacket packet) {
        return PacketSignal.UNHANDLED;
    }

    default PacketSignal handle(AddPlayerPacket packet) {
        return PacketSignal.UNHANDLED;
    }

    default PacketSignal handle(AnimatePacket packet) {
        return PacketSignal.UNHANDLED;
    }

    default PacketSignal handle(AwardStatsPacket packet) {
        return PacketSignal.UNHANDLED;
    }

    default PacketSignal handle(BlockBreakAckPacket packet) {
        return PacketSignal.UNHANDLED;
    }

    default PacketSignal handle(BlockDestructionPacket packet) {
        return PacketSignal.UNHANDLED;
    }

    default PacketSignal handle(BlockEntityDataPacket packet) {
        return PacketSignal.UNHANDLED;
    }

    default PacketSignal handle(BlockEventPacket packet) {
        return PacketSignal.UNHANDLED;
    }

    default PacketSignal handle(BlockUpdatePacket packet) {
        return PacketSignal.UNHANDLED;
    }

    default PacketSignal handle(BossEventPacket packet) {
        return PacketSignal.UNHANDLED;
    }

    default PacketSignal handle(CommandSuggestionsPacket packet) {
        return PacketSignal.UNHANDLED;
    }

    default PacketSignal handle(CommandsPacket packet) {
        return PacketSignal.UNHANDLED;
    }

    default PacketSignal handle(ContainerAckPacket packet) {
        return PacketSignal.UNHANDLED;
    }

    default PacketSignal handle(ContainerSetContentPacket packet) {
        return PacketSignal.UNHANDLED;
    }

    default PacketSignal handle(ContainerSetDataPacket packet) {
        return PacketSignal.UNHANDLED;
    }

    default PacketSignal handle(ContainerSetSlotPacket packet) {
        return PacketSignal.UNHANDLED;
    }

    default PacketSignal handle(CooldownPacket packet) {
        return PacketSignal.UNHANDLED;
    }

    default PacketSignal handle(CustomSoundPacket packet) {
        return PacketSignal.UNHANDLED;
    }

    default PacketSignal handle(DisconnectPacket packet) {
        return PacketSignal.UNHANDLED;
    }

    default PacketSignal handle(EntityEventPacket packet) {
        return PacketSignal.UNHANDLED;
    }

    default PacketSignal handle(ExplodePacket packet) {
        return PacketSignal.UNHANDLED;
    }

    default PacketSignal handle(ForgetLevelChunkPacket packet) {
        return PacketSignal.UNHANDLED;
    }

    default PacketSignal handle(GameEventPacket packet) {
        return PacketSignal.UNHANDLED;
    }

    default PacketSignal handle(HorseScreenOpenPacket packet) {
        return PacketSignal.UNHANDLED;
    }

    default PacketSignal handle(LevelChunkPacket packet) {
        return PacketSignal.UNHANDLED;
    }

    default PacketSignal handle(LevelEventPacket packet) {
        return PacketSignal.UNHANDLED;
    }

    default PacketSignal handle(LevelParticlesPacket packet) {
        return PacketSignal.UNHANDLED;
    }

    default PacketSignal handle(LightUpdatePacket packet) {
        return PacketSignal.UNHANDLED;
    }

    default PacketSignal handle(LoginPacket packet) {
        return PacketSignal.UNHANDLED;
    }

    default PacketSignal handle(MapItemDataPacket packet) {
        return PacketSignal.UNHANDLED;
    }

    default PacketSignal handle(MerchantOffersPacket packet) {
        return PacketSignal.UNHANDLED;
    }

    default PacketSignal handle(MoveEntityPacket.Pos packet) {
        return PacketSignal.UNHANDLED;
    }

    default PacketSignal handle(MoveEntityPacket.PosRot packet) {
        return PacketSignal.UNHANDLED;
    }

    default PacketSignal handle(MoveEntityPacket.Rot packet) {
        return PacketSignal.UNHANDLED;
    }

    default PacketSignal handle(OpenBookPacket packet) {
        return PacketSignal.UNHANDLED;
    }

    default PacketSignal handle(OpenScreenPacket packet) {
        return PacketSignal.UNHANDLED;
    }

    default PacketSignal handle(OpenSignEditorPacket packet) {
        return PacketSignal.UNHANDLED;
    }

    default PacketSignal handle(PlaceGhostRecipePacket packet) {
        return PacketSignal.UNHANDLED;
    }

    default PacketSignal handle(PlayerCombatPacket packet) {
        return PacketSignal.UNHANDLED;
    }

    default PacketSignal handle(PlayerInfoPacket packet) {
        return PacketSignal.UNHANDLED;
    }

    default PacketSignal handle(PlayerLookAtPacket packet) {
        return PacketSignal.UNHANDLED;
    }

    default PacketSignal handle(PlayerPositionPacket packet) {
        return PacketSignal.UNHANDLED;
    }

    default PacketSignal handle(RecipePacket packet) {
        return PacketSignal.UNHANDLED;
    }

    default PacketSignal handle(RemoveEntitiesPacket packet) {
        return PacketSignal.UNHANDLED;
    }

    default PacketSignal handle(RemoveMobEffectPacket packet) {
        return PacketSignal.UNHANDLED;
    }

    default PacketSignal handle(RespawnPacket packet) {
        return PacketSignal.UNHANDLED;
    }

    default PacketSignal handle(RotateHeadPacket packet) {
        return PacketSignal.UNHANDLED;
    }

    default PacketSignal handle(SectionBlocksUpdatePacket packet) {
        return PacketSignal.UNHANDLED;
    }

    default PacketSignal handle(SelectAdvancementsTabPacket packet) {
        return PacketSignal.UNHANDLED;
    }

    default PacketSignal handle(SetBorderPacket packet) {
        return PacketSignal.UNHANDLED;
    }

    default PacketSignal handle(SetCameraPacket packet) {
        return PacketSignal.UNHANDLED;
    }

    default PacketSignal handle(SetChunkCacheCenterPacket packet) {
        return PacketSignal.UNHANDLED;
    }

    default PacketSignal handle(SetChunkCacheRadiusPacket packet) {
        return PacketSignal.UNHANDLED;
    }

    default PacketSignal handle(SetDefaultSpawnPositionPacket packet) {
        return PacketSignal.UNHANDLED;
    }

    default PacketSignal handle(SetDisplayObjectivePacket packet) {
        return PacketSignal.UNHANDLED;
    }

    default PacketSignal handle(SetEntityDataPacket packet) {
        return PacketSignal.UNHANDLED;
    }

    default PacketSignal handle(SetEntityLinkPacket packet) {
        return PacketSignal.UNHANDLED;
    }

    default PacketSignal handle(SetEntityMotionPacket packet) {
        return PacketSignal.UNHANDLED;
    }

    default PacketSignal handle(SetEquipmentPacket packet) {
        return PacketSignal.UNHANDLED;
    }

    default PacketSignal handle(SetExperiencePacket packet) {
        return PacketSignal.UNHANDLED;
    }

    default PacketSignal handle(SetHealthPacket packet) {
        return PacketSignal.UNHANDLED;
    }

    default PacketSignal handle(SetObjectivePacket packet) {
        return PacketSignal.UNHANDLED;
    }

    default PacketSignal handle(SetPassengersPacket packet) {
        return PacketSignal.UNHANDLED;
    }

    default PacketSignal handle(SetPlayerTeamPacket packet) {
        return PacketSignal.UNHANDLED;
    }

    default PacketSignal handle(SetScorePacket packet) {
        return PacketSignal.UNHANDLED;
    }

    default PacketSignal handle(SetTimePacket packet) {
        return PacketSignal.UNHANDLED;
    }

    default PacketSignal handle(SetTitlesPacket packet) {
        return PacketSignal.UNHANDLED;
    }

    default PacketSignal handle(SoundEntityPacket packet) {
        return PacketSignal.UNHANDLED;
    }

    default PacketSignal handle(SoundPacket packet) {
        return PacketSignal.UNHANDLED;
    }

    default PacketSignal handle(StopSoundPacket packet) {
        return PacketSignal.UNHANDLED;
    }

    default PacketSignal handle(TabListPacket packet) {
        return PacketSignal.UNHANDLED;
    }

    default PacketSignal handle(TagQueryPacket packet) {
        return PacketSignal.UNHANDLED;
    }

    default PacketSignal handle(TakeItemEntityPacket packet) {
        return PacketSignal.UNHANDLED;
    }

    default PacketSignal handle(TeleportEntityPacket packet) {
        return PacketSignal.UNHANDLED;
    }

    default PacketSignal handle(UpdateAdvancementsPacket packet) {
        return PacketSignal.UNHANDLED;
    }

    default PacketSignal handle(UpdateAttributesPacket packet) {
        return PacketSignal.UNHANDLED;
    }

    default PacketSignal handle(UpdateMobEffectPacket packet) {
        return PacketSignal.UNHANDLED;
    }

    default PacketSignal handle(UpdateRecipesPacket packet) {
        return PacketSignal.UNHANDLED;
    }

    default PacketSignal handle(UpdateTagsPacket packet) {
        return PacketSignal.UNHANDLED;
    }

    // Serverbound packets
    default PacketSignal handle(AcceptTeleportationPacket packet) {
        return PacketSignal.UNHANDLED;
    }

    default PacketSignal handle(BlockEntityTagQueryPacket packet) {
        return PacketSignal.UNHANDLED;
    }

    default PacketSignal handle(ClientCommandPacket packet) {
        return PacketSignal.UNHANDLED;
    }

    default PacketSignal handle(ClientInformationPacket packet) {
        return PacketSignal.UNHANDLED;
    }

    default PacketSignal handle(CommandSuggestionPacket packet) {
        return PacketSignal.UNHANDLED;
    }

    default PacketSignal handle(ContainerButtonClickPacket packet) {
        return PacketSignal.UNHANDLED;
    }

    default PacketSignal handle(ContainerClickPacket packet) {
        return PacketSignal.UNHANDLED;
    }

    default PacketSignal handle(EditBookPacket packet) {
        return PacketSignal.UNHANDLED;
    }

    default PacketSignal handle(EntityTagQueryPacket packet) {
        return PacketSignal.UNHANDLED;
    }

    default PacketSignal handle(InteractPacket packet) {
        return PacketSignal.UNHANDLED;
    }

    default PacketSignal handle(JigsawGeneratePacket packet) {
        return PacketSignal.UNHANDLED;
    }

    default PacketSignal handle(LockDifficultyPacket packet) {
        return PacketSignal.UNHANDLED;
    }

    default PacketSignal handle(MovePlayerPacket.Pos packet) {
        return PacketSignal.UNHANDLED;
    }

    default PacketSignal handle(MovePlayerPacket.PosRot packet) {
        return PacketSignal.UNHANDLED;
    }

    default PacketSignal handle(MovePlayerPacket.Rot packet) {
        return PacketSignal.UNHANDLED;
    }

    default PacketSignal handle(PaddleBoatPacket packet) {
        return PacketSignal.UNHANDLED;
    }

    default PacketSignal handle(PickItemPacket packet) {
        return PacketSignal.UNHANDLED;
    }

    default PacketSignal handle(PlaceRecipePacket packet) {
        return PacketSignal.UNHANDLED;
    }

    default PacketSignal handle(PlayerActionPacket packet) {
        return PacketSignal.UNHANDLED;
    }

    default PacketSignal handle(PlayerCommandPacket packet) {
        return PacketSignal.UNHANDLED;
    }

    default PacketSignal handle(PlayerInputPacket packet) {
        return PacketSignal.UNHANDLED;
    }

    default PacketSignal handle(RecipeBookChangeSettingsPacket packet) {
        return PacketSignal.UNHANDLED;
    }

    default PacketSignal handle(RecipeBookSeenRecipePacket packet) {
        return PacketSignal.UNHANDLED;
    }

    default PacketSignal handle(RenameItemPacket packet) {
        return PacketSignal.UNHANDLED;
    }

    default PacketSignal handle(SeenAdvancementsPacket packet) {
        return PacketSignal.UNHANDLED;
    }

    default PacketSignal handle(SelectTradePacket packet) {
        return PacketSignal.UNHANDLED;
    }

    default PacketSignal handle(SetBeaconPacket packet) {
        return PacketSignal.UNHANDLED;
    }

    default PacketSignal handle(SetCommandBlockPacket packet) {
        return PacketSignal.UNHANDLED;
    }

    default PacketSignal handle(SetCommandMinecartPacket packet) {
        return PacketSignal.UNHANDLED;
    }

    default PacketSignal handle(SetCreativeModeSlotPacket packet) {
        return PacketSignal.UNHANDLED;
    }

    default PacketSignal handle(SetJigsawBlockPacket packet) {
        return PacketSignal.UNHANDLED;
    }

    default PacketSignal handle(SetStructureBlockPacket packet) {
        return PacketSignal.UNHANDLED;
    }

    default PacketSignal handle(SignUpdatePacket packet) {
        return PacketSignal.UNHANDLED;
    }

    default PacketSignal handle(SwingPacket packet) {
        return PacketSignal.UNHANDLED;
    }

    default PacketSignal handle(TeleportToEntityPacket packet) {
        return PacketSignal.UNHANDLED;
    }

    default PacketSignal handle(UseItemOnPacket packet) {
        return PacketSignal.UNHANDLED;
    }

    default PacketSignal handle(UseItemPacket packet) {
        return PacketSignal.UNHANDLED;
    }

    // Bidirectional packets
    default PacketSignal handle(ChangeDifficultyPacket packet) {
        return PacketSignal.UNHANDLED;
    }

    default PacketSignal handle(ChatPacket packet) {
        return PacketSignal.UNHANDLED;
    }

    default PacketSignal handle(ContainerClosePacket packet) {
        return PacketSignal.UNHANDLED;
    }

    default PacketSignal handle(CustomPayloadPacket packet) {
        return PacketSignal.UNHANDLED;
    }

    default PacketSignal handle(KeepAlivePacket packet) {
        return PacketSignal.UNHANDLED;
    }

    default PacketSignal handle(MoveVehiclePacket packet) {
        return PacketSignal.UNHANDLED;
    }

    default PacketSignal handle(PlayerAbilitiesPacket packet) {
        return PacketSignal.UNHANDLED;
    }

    default PacketSignal handle(ResourcePackPacket packet) {
        return PacketSignal.UNHANDLED;
    }

    default PacketSignal handle(SetCarriedItemPacket packet) {
        return PacketSignal.UNHANDLED;
    }
}
