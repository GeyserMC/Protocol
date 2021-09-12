package org.cloudburstmc.protocol.java.codec.v754;

import net.kyori.adventure.key.Key;
import org.cloudburstmc.protocol.common.util.TypeMap;
import org.cloudburstmc.protocol.java.codec.BaseJavaCodecHelper;
import org.cloudburstmc.protocol.java.data.crafting.RecipeType;
import org.cloudburstmc.protocol.java.data.entity.EntityDataType;
import org.cloudburstmc.protocol.java.data.entity.EntityType;
import org.cloudburstmc.protocol.java.data.entity.MobEffectType;
import org.cloudburstmc.protocol.java.data.entity.Pose;
import org.cloudburstmc.protocol.java.data.inventory.ContainerType;
import org.cloudburstmc.protocol.java.data.world.BlockEntityAction;
import org.cloudburstmc.protocol.java.data.world.ParticleType;

public class JavaCodecHelper_v754 extends BaseJavaCodecHelper {

    public JavaCodecHelper_v754(TypeMap<EntityType> entityTypes, TypeMap<BlockEntityAction> blockEntityActions,
                                TypeMap<ContainerType> containerTypes, TypeMap<EntityDataType<?>> entityDataTypes,
                                TypeMap<Pose> poses, TypeMap<ParticleType> particles, TypeMap<MobEffectType> mobEffects) {
        super(entityTypes, blockEntityActions, containerTypes, entityDataTypes, poses, particles, mobEffects);
    }

    @Override
    public void registerRecipeTypes() {
        this.registerRecipeType(RecipeType.SHAPELESS);
        this.registerRecipeType(RecipeType.SHAPED);
        this.registerRecipeType(RecipeType.SPECIAL_ARMORDYE);
        this.registerRecipeType(RecipeType.SPECIAL_BOOKCLONING);
        this.registerRecipeType(RecipeType.SPECIAL_MAPCLONING);
        this.registerRecipeType(RecipeType.SPECIAL_MAPEXTENDING);
        this.registerRecipeType(RecipeType.SPECIAL_FIREWORK_ROCKET);
        this.registerRecipeType(RecipeType.SPECIAL_FIREWORK_STAR);
        this.registerRecipeType(RecipeType.SPECIAL_FIREWORK_STAR_FADE);
        this.registerRecipeType(RecipeType.SPECIAL_REPAIRITEM);
        this.registerRecipeType(RecipeType.SPECIAL_TIPPEDARROW);
        this.registerRecipeType(RecipeType.SPECIAL_BANNERDUPLICATE);
        this.registerRecipeType(RecipeType.SPECIAL_BANNERADDPATTERN);
        this.registerRecipeType(RecipeType.SPECIAL_SHIELDDECORATION);
        this.registerRecipeType(RecipeType.SPECIAL_SHULKERBOXCOLORING);
        this.registerRecipeType(RecipeType.SPECIAL_SUSPICIOUSSTEW);
        this.registerRecipeType(RecipeType.SMELTING);
        this.registerRecipeType(RecipeType.BLASTING);
        this.registerRecipeType(RecipeType.SMOKING);
        this.registerRecipeType(RecipeType.CAMPFIRE_COOKING);
        this.registerRecipeType(RecipeType.STONECUTTING);
        this.registerRecipeType(RecipeType.SMITHING);
    }
}
