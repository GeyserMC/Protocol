package org.cloudburstmc.protocol.java.codec;

import com.nukkitx.math.vector.Vector2f;
import com.nukkitx.math.vector.Vector3d;
import com.nukkitx.math.vector.Vector3f;
import com.nukkitx.math.vector.Vector3i;
import io.netty.buffer.ByteBuf;
import net.kyori.adventure.key.Key;
import net.kyori.adventure.text.Component;
import org.cloudburstmc.protocol.common.util.TriConsumer;
import org.cloudburstmc.protocol.java.data.Direction;
import org.cloudburstmc.protocol.java.data.crafting.Recipe;
import org.cloudburstmc.protocol.java.data.crafting.RecipeIngredient;
import org.cloudburstmc.protocol.java.data.crafting.RecipeType;
import org.cloudburstmc.protocol.java.data.entity.*;
import org.cloudburstmc.protocol.java.data.inventory.ContainerType;
import org.cloudburstmc.protocol.java.data.inventory.ItemStack;
import org.cloudburstmc.protocol.java.data.profile.GameProfile;
import org.cloudburstmc.protocol.java.data.world.BlockEntityAction;
import org.cloudburstmc.protocol.java.data.world.Particle;
import org.cloudburstmc.protocol.java.data.world.ParticleType;

import javax.annotation.Nullable;
import java.util.Collection;
import java.util.UUID;
import java.util.function.*;

public interface JavaCodecHelper {
    int readVarInt(ByteBuf buffer);

    void writeVarInt(ByteBuf buffer, int varint);

    long readVarLong(ByteBuf buffer);

    void writeVarLong(ByteBuf buffer, long varlong);

    byte[] readByteArray(ByteBuf buffer);

    void writeByteArray(ByteBuf buffer, byte[] bytes);

    String readString(ByteBuf buffer);

    void writeString(ByteBuf buffer, String string);

    Key readKey(ByteBuf buffer);

    void writeKey(ByteBuf buffer, Key key);

    GameProfile readGameProfile(ByteBuf buffer);

    GameProfile readGameProfile(ByteBuf buffer, boolean readProperties);

    void writeGameProfile(ByteBuf buffer, GameProfile profile);

    void writeGameProfile(ByteBuf buffer, GameProfile profile, boolean writeProperties);

    UUID readUUID(ByteBuf buffer);

    void writeUUID(ByteBuf buffer, UUID uuid);

    Vector3d readPosition(ByteBuf buffer);

    void writePosition(ByteBuf buffer, Vector3d vector3d);

    Vector3d readVelocity(ByteBuf buffer);

    void writeVelocity(ByteBuf buffer, Vector3d vector3d);

    Vector2f readRotation2f(ByteBuf buffer);

    Vector3f readRotation3f(ByteBuf buffer);

    void writeRotation2f(ByteBuf buffer, Vector2f vector2f);

    void writeRotation3f(ByteBuf buffer, Vector3f vector3f);

    Vector3f readVector3f(ByteBuf buffer);

    void writeVector3f(ByteBuf buffer, Vector3f vector3f);

    Vector2f readVector2f(ByteBuf buffer);

    void writeVector2f(ByteBuf buffer, Vector2f vector2f);

    Direction readDirection(ByteBuf buffer);

    void writeDirection(ByteBuf buffer, Direction direction);

    VillagerData readVillagerData(ByteBuf buffer);

    void writeVillagerData(ByteBuf buffer, VillagerData villagerData);

    Particle readParticle(ByteBuf buffer);

    void writeParticle(ByteBuf buffer, Particle particle);

    @Nullable
    Particle readParticleData(ByteBuf buffer, ParticleType type);

    void writeParticleData(ByteBuf buffer, Particle particle);

    // TODO: Move these to version helpers as they have changed between versions
    ItemStack readItemStack(ByteBuf buffer);

    void writeItemStack(ByteBuf buffer, ItemStack item);

    RecipeIngredient readRecipeIngredient(ByteBuf buffer);

    void writeRecipeIngredient(ByteBuf buffer, RecipeIngredient ingredient);

    Recipe readRecipe(ByteBuf buffer);

    void writeRecipe(ByteBuf buffer, Recipe recipe);

    Vector3i readBlockPosition(ByteBuf buffer);

    void writeBlockPosition(ByteBuf buffer, Vector3i vector3i);

    <T> void readArray(ByteBuf buffer, Collection<T> array, BiFunction<ByteBuf, JavaCodecHelper, T> function);

    <T> void writeArray(ByteBuf buffer, Collection<T> array, TriConsumer<ByteBuf, JavaCodecHelper, T> biConsumer);

    <T> T[] readArray(ByteBuf buffer, T[] array, BiFunction<ByteBuf, JavaCodecHelper, T> function);

    <T> void writeArray(ByteBuf buffer, T[] array, TriConsumer<ByteBuf, JavaCodecHelper, T> biConsumer);

    <T> void readArray(ByteBuf buffer, Collection<T> array, Function<ByteBuf, T> function);

    <T> void writeArray(ByteBuf buffer, Collection<T> array, BiConsumer<ByteBuf, T> biConsumer);

    <T> T[] readArray(ByteBuf buffer, T[] array, Function<ByteBuf, T> function);

    <T> void writeArray(ByteBuf buffer, T[] array, BiConsumer<ByteBuf, T> biConsumer);

    int[] readIntArray(ByteBuf buffer, ToIntFunction<ByteBuf> function);

    void writeIntArray(ByteBuf buffer, int[] array, ObjIntConsumer<ByteBuf> consumer);

    @SuppressWarnings("unchecked")
    <T> T readTag(ByteBuf buffer);

    <T> void writeTag(ByteBuf buffer, T tag);

    Component readComponent(ByteBuf buffer);

    void writeComponent(ByteBuf buffer, Component component);

    Pose readPose(ByteBuf buffer);

    void writePose(ByteBuf buffer, Pose pose);

    <T> EntityData<T> readEntityData(int dataId, int id, ByteBuf buffer);

    <T> void writeEntityData(EntityData<T> data, ByteBuf buffer);

    <T> T readOptional(ByteBuf buffer, Function<ByteBuf, T> readFunction);

    <T> void writeOptional(ByteBuf buffer, T value, BiConsumer<ByteBuf, T> writeFunction);

    int getEntityId(EntityType entityType);

    EntityType getEntityType(int entityId);

    int getBlockEntityActionId(BlockEntityAction action);

    BlockEntityAction getBlockEntityAction(int actionId);

    int getContainerId(ContainerType containerType);

    ContainerType getContainerType(int containerId);

    Pose getPose(int id);

    int getPoseId(Pose pose);

    @SuppressWarnings("unchecked")
    <T> EntityDataType<T> getEntityDataType(int id);

    int getEntityDataTypeId(EntityDataType<?> type);

    ParticleType getParticle(int id);

    int getParticleId(ParticleType particle);

    MobEffectType getMobEffect(int id);

    int getMobEffectId(MobEffectType mobEffect);

    RecipeType<? extends Recipe> getRecipeType(Key key);

    Key getRecipeTypeKey(RecipeType<?> recipeType);

    void registerRecipeTypes();
}
