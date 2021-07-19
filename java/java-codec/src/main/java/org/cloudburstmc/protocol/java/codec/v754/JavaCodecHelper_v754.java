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
        this.recipeTypes.put(Key.key("crafting_shapeless"), RecipeType.SHAPELESS);
        this.recipeTypes.put(Key.key("crafting_shaped"), RecipeType.SHAPED);
        this.recipeTypes.put(Key.key("crafting_special_armordye"), RecipeType.SPECIAL_ARMORDYE);
        this.recipeTypes.put(Key.key("crafting_special_bookcloning"), RecipeType.SPECIAL_BOOKCLONING);
        this.recipeTypes.put(Key.key("crafting_special_mapcloning"), RecipeType.SPECIAL_MAPCLONING);
        this.recipeTypes.put(Key.key("crafting_special_mapextending"), RecipeType.SPECIAL_MAPEXTENDING);
        this.recipeTypes.put(Key.key("crafting_special_firework_rocket"), RecipeType.SPECIAL_FIREWORK_ROCKET);
        this.recipeTypes.put(Key.key("crafting_special_firework_star"), RecipeType.SPECIAL_FIREWORK_STAR);
        this.recipeTypes.put(Key.key("crafting_special_firework_star_fade"), RecipeType.SPECIAL_FIREWORK_STAR_FADE);
        this.recipeTypes.put(Key.key("crafting_special_repairitem"), RecipeType.SPECIAL_REPAIRITEM);
        this.recipeTypes.put(Key.key("crafting_special_tippedarrow"), RecipeType.SPECIAL_TIPPEDARROW);
        this.recipeTypes.put(Key.key("crafting_special_bannerduplicate"), RecipeType.SPECIAL_BANNERDUPLICATE);
        this.recipeTypes.put(Key.key("crafting_special_banneraddpattern"), RecipeType.SPECIAL_BANNERADDPATTERN);
        this.recipeTypes.put(Key.key("crafting_special_shielddecoration"), RecipeType.SPECIAL_SHIELDDECORATION);
        this.recipeTypes.put(Key.key("crafting_special_shulkerboxcolorning"), RecipeType.SPECIAL_SHULKERBOXCOLORING);
        this.recipeTypes.put(Key.key("crafting_special_suspiciousstew"), RecipeType.SPECIAL_SUSPICIOUSSTEW);
        this.recipeTypes.put(Key.key("smelting"), RecipeType.SMELTING);
        this.recipeTypes.put(Key.key("blasting"), RecipeType.BLASTING);
        this.recipeTypes.put(Key.key("smoking"), RecipeType.SMOKING);
        this.recipeTypes.put(Key.key("campfire_cooking"), RecipeType.CAMPFIRE_COOKING);
        this.recipeTypes.put(Key.key("stonecutting"), RecipeType.STONECUTTING);
        this.recipeTypes.put(Key.key("smithing"), RecipeType.SMITHING);
    }
}
