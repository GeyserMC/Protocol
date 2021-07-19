package org.cloudburstmc.protocol.java.codec;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.nukkitx.math.vector.*;
import com.nukkitx.nbt.NBTInputStream;
import com.nukkitx.nbt.NBTOutputStream;
import com.nukkitx.nbt.NbtUtils;
import org.cloudburstmc.protocol.common.util.Preconditions;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufInputStream;
import io.netty.buffer.ByteBufOutputStream;
import io.netty.buffer.ByteBufUtil;
import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.InternalLoggerFactory;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import net.kyori.adventure.key.Key;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.gson.GsonComponentSerializer;
import org.cloudburstmc.protocol.common.util.TriConsumer;
import org.cloudburstmc.protocol.common.util.TypeMap;
import org.cloudburstmc.protocol.common.util.VarInts;
import org.cloudburstmc.protocol.java.data.Direction;
import org.cloudburstmc.protocol.java.data.crafting.Recipe;
import org.cloudburstmc.protocol.java.data.crafting.RecipeIngredient;
import org.cloudburstmc.protocol.java.data.crafting.RecipeType;
import org.cloudburstmc.protocol.java.data.entity.*;
import org.cloudburstmc.protocol.java.data.inventory.ContainerType;
import org.cloudburstmc.protocol.java.data.inventory.ItemStack;
import org.cloudburstmc.protocol.java.data.profile.GameProfile;
import org.cloudburstmc.protocol.java.data.profile.property.Property;
import org.cloudburstmc.protocol.java.data.profile.property.PropertyMap;
import org.cloudburstmc.protocol.java.data.world.BlockEntityAction;
import org.cloudburstmc.protocol.java.data.world.Particle;
import org.cloudburstmc.protocol.java.data.world.ParticleType;

import javax.annotation.Nullable;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Collection;
import java.util.UUID;
import java.util.function.*;

@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class BaseJavaCodecHelper implements JavaCodecHelper {
    protected static final InternalLogger log = InternalLoggerFactory.getInstance(BaseJavaCodecHelper.class);

    protected final TypeMap<EntityType> entityTypes;
    protected final TypeMap<BlockEntityAction> blockEntityActions;
    protected final TypeMap<ContainerType> containerTypes;
    protected final TypeMap<EntityDataType<?>> entityDataTypes;
    protected final TypeMap<Pose> poses;
    protected final TypeMap<ParticleType> particles;
    protected final TypeMap<MobEffectType> mobEffects;
    protected final BiMap<Key, RecipeType<? extends Recipe>> recipeTypes = HashBiMap.create(); //TODO: change this so we can remove the registerRecipeTypes() method

    @Override
    public int readVarInt(ByteBuf buffer) {
        // Don't use the signed version! Only Bedrock knows that concept
        return VarInts.readUnsignedInt(buffer);
    }

    @Override
    public void writeVarInt(ByteBuf buffer, int varint) {
        // Don't use the signed version! Only Bedrock knows that concept
        VarInts.writeUnsignedInt(buffer, varint);
    }

    @Override
    public long readVarLong(ByteBuf buffer) {
        return VarInts.readUnsignedLong(buffer);
    }

    @Override
    public void writeVarLong(ByteBuf buffer, long varlong) {
        VarInts.writeUnsignedLong(buffer, varlong);
    }

    @Override
    public byte[] readByteArray(ByteBuf buffer) {
        Preconditions.checkNotNull(buffer, "buffer");
        int length = VarInts.readUnsignedInt(buffer);
        Preconditions.checkArgument(buffer.isReadable(length),
                 "Tried to read %s bytes but only has %s readable", length, buffer.readableBytes());
        byte[] bytes = new byte[length];
        buffer.readBytes(bytes);
        return bytes;
    }

    @Override
    public void writeByteArray(ByteBuf buffer, byte[] bytes) {
        Preconditions.checkNotNull(buffer, "buffer");
        Preconditions.checkNotNull(bytes, "bytes");
        VarInts.writeUnsignedInt(buffer, bytes.length);
        buffer.writeBytes(bytes);
    }

    @Override
    public String readString(ByteBuf buffer) {
        Preconditions.checkNotNull(buffer, "buffer");
        int length = VarInts.readUnsignedInt(buffer);
        return (String) buffer.readCharSequence(length, StandardCharsets.UTF_8);
    }

    @Override
    public void writeString(ByteBuf buffer, String string) {
        Preconditions.checkNotNull(buffer, "buffer");
        Preconditions.checkNotNull(string, "string");
        VarInts.writeUnsignedInt(buffer, ByteBufUtil.utf8Bytes(string));
        buffer.writeCharSequence(string, StandardCharsets.UTF_8);
    }

    @Override
    public Key readKey(ByteBuf buffer) {
        return Key.key(this.readString(buffer));
    }

    @Override
    public void writeKey(ByteBuf buffer, Key key) {
        this.writeString(buffer, key.asString());
    }

    @Override
    public GameProfile readGameProfile(ByteBuf buffer) {
        return this.readGameProfile(buffer, false);
    }

    @Override
    public GameProfile readGameProfile(ByteBuf buffer, boolean readProperties) {
        UUID id = readUUID(buffer);
        String name = null;
        if (readProperties && buffer.readableBytes() > 0) {
            name = readString(buffer);
        }
        GameProfile profile = new GameProfile(id, name);
        if (!readProperties || buffer.readableBytes() <= 0) {
            return profile;
        }
        // Read properties
        int propertiesAmt = VarInts.readUnsignedInt(buffer);
        PropertyMap properties = new PropertyMap();
        for (int i = 0; i < propertiesAmt; i++) {
            Property property = new Property(readString(buffer), readString(buffer), buffer.readBoolean() ? readString(buffer) : null);
            properties.put(property.getName(), property);
        }
        profile.setProperties(properties);
        return profile;
    }

    @Override
    public void writeGameProfile(ByteBuf buffer, GameProfile profile) {
        writeGameProfile(buffer, profile, false);
    }

    @Override
    public void writeGameProfile(ByteBuf buffer, GameProfile profile, boolean writeProperties) {
        writeUUID(buffer, profile.getId());
        writeString(buffer, profile.getName() == null ? "" : profile.getName());
        if (writeProperties && profile.getProperties() != null && !profile.getProperties().isEmpty()) {
            VarInts.writeUnsignedInt(buffer, profile.getProperties().size());
            for (Property property : profile.getProperties().values()) {
                writeString(buffer, property.getName());
                writeString(buffer, property.getValue());
                buffer.writeBoolean(property.getSignature() != null);
                if (property.getSignature() != null) {
                    writeString(buffer, property.getSignature());
                }
            }
        }
    }

    @Override
    public UUID readUUID(ByteBuf buffer) {
        return new UUID(buffer.readLong(), buffer.readLong());
    }

    @Override
    public void writeUUID(ByteBuf buffer, UUID uuid) {
        buffer.writeLong(uuid.getMostSignificantBits());
        buffer.writeLong(uuid.getLeastSignificantBits());
    }

    @Override
    public Vector3d readPosition(ByteBuf buffer) {
        Preconditions.checkNotNull(buffer, "buffer");
        double x = buffer.readDouble();
        double y = buffer.readDouble();
        double z = buffer.readDouble();
        return Vector3d.from(x, y, z);
    }

    @Override
    public void writePosition(ByteBuf buffer, Vector3d vector3d) {
        Preconditions.checkNotNull(buffer, "buffer");
        Preconditions.checkNotNull(vector3d, "vector3d");
        buffer.writeDouble(vector3d.getX());
        buffer.writeDouble(vector3d.getY());
        buffer.writeDouble(vector3d.getZ());
    }

    @Override
    public Vector3d readVelocity(ByteBuf buffer) {
        Preconditions.checkNotNull(buffer, "buffer");
        double x = buffer.readShort() / 8000D;
        double y = buffer.readShort() / 8000D;
        double z = buffer.readShort() / 8000D;
        return Vector3d.from(x, y, z);
    }

    @Override
    public void writeVelocity(ByteBuf buffer, Vector3d vector3d) {
        Preconditions.checkNotNull(buffer, "buffer");
        Preconditions.checkNotNull(vector3d, "vector3d");
        buffer.writeDouble(vector3d.getX() * 8000D);
        buffer.writeDouble(vector3d.getY() * 8000D);
        buffer.writeDouble(vector3d.getZ() * 8000D);
    }

    @Override
    public Vector2f readRotation2f(ByteBuf buffer) {
        Preconditions.checkNotNull(buffer, "buffer");
        float pitch = buffer.readByte() * 360 / 256F;
        float yaw = buffer.readByte() * 360 / 256F;
        return Vector2f.from(pitch, yaw);
    }

    @Override
    public Vector3f readRotation3f(ByteBuf buffer) {
        Preconditions.checkNotNull(buffer, "buffer");
        float pitch = buffer.readByte() * 360 / 256F;
        float yaw = buffer.readByte() * 360 / 256F;
        float headYaw = buffer.readByte() * 360 / 256F;
        return Vector3f.from(pitch, yaw, headYaw);
    }

    @Override
    public void writeRotation2f(ByteBuf buffer, Vector2f vector2f) {
        Preconditions.checkNotNull(buffer, "buffer");
        Preconditions.checkNotNull(vector2f, "vector2f");
        buffer.writeByte((int) (vector2f.getX() * 256F / 360));
        buffer.writeByte((int) (vector2f.getY() * 256F / 360));
    }

    @Override
    public void writeRotation3f(ByteBuf buffer, Vector3f vector3f) {
        Preconditions.checkNotNull(buffer, "buffer");
        Preconditions.checkNotNull(vector3f, "vector3f");
        buffer.writeByte((int) (vector3f.getX() * 256F / 360));
        buffer.writeByte((int) (vector3f.getY() * 256F / 360));
        buffer.writeByte((int) (vector3f.getZ() * 256F / 360));
    }

    @Override
    public Vector3f readVector3f(ByteBuf buffer) {
        Preconditions.checkNotNull(buffer, "buffer");
        return Vector3f.from(buffer.readFloat(), buffer.readFloat(), buffer.readFloat());
    }

    @Override
    public void writeVector3f(ByteBuf buffer, Vector3f vector3f) {
        Preconditions.checkNotNull(buffer, "buffer");
        Preconditions.checkNotNull(vector3f, "vector3f");
        buffer.writeFloat(vector3f.getX());
        buffer.writeFloat(vector3f.getY());
        buffer.writeFloat(vector3f.getZ());
    }

    @Override
    public Vector2f readVector2f(ByteBuf buffer) {
        Preconditions.checkNotNull(buffer, "buffer");
        return Vector2f.from(buffer.readFloat(), buffer.readFloat());
    }

    @Override
    public void writeVector2f(ByteBuf buffer, Vector2f vector2f) {
        Preconditions.checkNotNull(buffer, "buffer");
        Preconditions.checkNotNull(vector2f, "vector2f");
        buffer.writeFloat(vector2f.getX());
        buffer.writeFloat(vector2f.getY());
    }

    @Override
    public Direction readDirection(ByteBuf buffer) {
        Preconditions.checkNotNull(buffer, "buffer");
        return Direction.getById(this.readVarInt(buffer));
    }

    @Override
    public void writeDirection(ByteBuf buffer, Direction direction) {
        Preconditions.checkNotNull(buffer, "buffer");
        Preconditions.checkNotNull(direction, "direction");
        this.writeVarInt(buffer, direction.ordinal());
    }

    @Override
    public VillagerData readVillagerData(ByteBuf buffer) {
        Preconditions.checkNotNull(buffer, "buffer");
        return new VillagerData(this.readVarInt(buffer), this.readVarInt(buffer), this.readVarInt(buffer));
    }

    @Override
    public void writeVillagerData(ByteBuf buffer, VillagerData villagerData) {
        Preconditions.checkNotNull(buffer, "buffer");
        Preconditions.checkNotNull(villagerData, "villagerData");
        this.writeVarInt(buffer, villagerData.getLevel());
        this.writeVarInt(buffer, villagerData.getProfession());
        this.writeVarInt(buffer, villagerData.getType());
    }

    @Override
    public Particle readParticle(ByteBuf buffer) {
        Preconditions.checkNotNull(buffer, "buffer");
        ParticleType type = this.getParticle(this.readVarInt(buffer));
        return new Particle(type, this.readParticleData(buffer, type));
    }

    @Override
    public void writeParticle(ByteBuf buffer, Particle particle) {
        Preconditions.checkNotNull(buffer, "buffer");
        Preconditions.checkNotNull(particle, "particle");
        this.writeVarInt(buffer, particle.getType().ordinal());
        this.writeParticleData(buffer, particle);
    }

    @Override
    @Nullable
    public Particle readParticleData(ByteBuf buffer, ParticleType type) {
        Preconditions.checkNotNull(buffer, "buffer");
        Preconditions.checkNotNull(type, "particleType");
        if (type.getTypeClass() == int.class) {
            return new Particle(type, this.readVarInt(buffer));
        } else if (type.getTypeClass() == Vector4f.class) {
            return new Particle(type, Vector4f.from(buffer.readFloat(), buffer.readFloat(), buffer.readFloat(), buffer.readFloat()));
        } else if (type.getTypeClass() == ItemStack.class) {
            return new Particle(type, this.readItemStack(buffer));
        }
        return null;
    }

    @Override
    public void writeParticleData(ByteBuf buffer, Particle particle) {
        Preconditions.checkNotNull(buffer, "buffer");
        Preconditions.checkNotNull(particle, "particle");
        ParticleType type = particle.getType();
        Object data = particle.getData();
        if (type.getTypeClass() == int.class) {
            this.writeVarInt(buffer, (int) data);
        } else if (type.getTypeClass() == Vector4f.class) {
            Vector4f color = (Vector4f) data;
            buffer.writeFloat(color.getX());
            buffer.writeFloat(color.getY());
            buffer.writeFloat(color.getZ());
            buffer.writeFloat(color.getW());
        } else if (type.getTypeClass() == ItemStack.class) {
            this.writeItemStack(buffer, (ItemStack) data);
        }
    }

    // TODO: Move these to version helpers as they have changed between versions
    @Override
    public ItemStack readItemStack(ByteBuf buffer) {
        boolean present = buffer.readBoolean();
        if (!present) {
            return null;
        }

        int item = VarInts.readUnsignedInt(buffer);
        return new ItemStack(item, buffer.readByte(), readTag(buffer));
    }

    @Override
    public void writeItemStack(ByteBuf buffer, ItemStack item) {
        buffer.writeBoolean(item != null);
        if (item != null) {
            VarInts.writeUnsignedInt(buffer, item.getId());
            buffer.writeByte(item.getAmount());
            writeTag(buffer, item.getNbt());
        }
    }

    @Override
    public RecipeIngredient readRecipeIngredient(ByteBuf buffer) {
        Preconditions.checkNotNull(buffer, "buffer");
        return new RecipeIngredient(this.readArray(buffer, new ItemStack[0], this::readItemStack));
    }

    @Override
    public void writeRecipeIngredient(ByteBuf buffer, RecipeIngredient ingredient) {
        Preconditions.checkNotNull(buffer, "buffer");
        Preconditions.checkNotNull(ingredient, "ingredient");
        this.writeArray(buffer, ingredient.getChoices(), this::writeItemStack);
    }

    @Override
    public Recipe readRecipe(ByteBuf buffer) {
        Preconditions.checkNotNull(buffer, "buffer");
        Key key = this.readKey(buffer);
        RecipeType<? extends Recipe> type = this.getRecipeType(key);
        return type.read(this, buffer);
    }

    @Override
    public void writeRecipe(ByteBuf buffer, Recipe recipe) {
        Preconditions.checkNotNull(buffer, "buffer");
        Preconditions.checkNotNull(recipe, "recipe");
        this.writeKey(buffer, this.getRecipeTypeKey(recipe.getType()));
        recipe.getType().write(this, buffer, recipe);
    }

    @Override
    public Vector3i readBlockPosition(ByteBuf buffer) {
        Preconditions.checkNotNull(buffer, "buffer");
        long position = buffer.readLong();
        return Vector3i.from((int) (position >> 38), (int) (position >> 12), (int) (position << 26 >> 38));
    }

    @Override
    public void writeBlockPosition(ByteBuf buffer, Vector3i vector3i) {
        Preconditions.checkNotNull(buffer, "buffer");
        Preconditions.checkNotNull(vector3i, "vector3i");
        buffer.writeLong((long) (vector3i.getX() & 0x3FFFFFF) << 38 | (long) (vector3i.getZ() & 0x3FFFFFF) << 12 | (long) (vector3i.getY() & 0xFFF));
    }

    /*
        Helper array serialization
     */

    @Override
    public <T> void readArray(ByteBuf buffer, Collection<T> array, BiFunction<ByteBuf, JavaCodecHelper, T> function) {
        int length = VarInts.readUnsignedInt(buffer);
        for (int i = 0; i < length; i++) {
            array.add(function.apply(buffer, this));
        }
    }

    @Override
    public <T> void writeArray(ByteBuf buffer, Collection<T> array, TriConsumer<ByteBuf, JavaCodecHelper, T> biConsumer) {
        VarInts.writeUnsignedInt(buffer, array.size());
        for (T val : array) {
            biConsumer.accept(buffer, this, val);
        }
    }

    @Override
    public <T> T[] readArray(ByteBuf buffer, T[] array, BiFunction<ByteBuf, JavaCodecHelper, T> function) {
        ObjectArrayList<T> list = new ObjectArrayList<>();
        readArray(buffer, list, function);
        return list.toArray(array);
    }

    @Override
    public <T> void writeArray(ByteBuf buffer, T[] array, TriConsumer<ByteBuf, JavaCodecHelper, T> biConsumer) {
        VarInts.writeUnsignedInt(buffer, array.length);
        for (T val : array) {
            biConsumer.accept(buffer, this, val);
        }
    }

    /*
        Non-helper array serialization
     */

    @Override
    public <T> void readArray(ByteBuf buffer, Collection<T> array, Function<ByteBuf, T> function) {
        int length = VarInts.readUnsignedInt(buffer);
        for (int i = 0; i < length; i++) {
            array.add(function.apply(buffer));
        }
    }

    @Override
    public <T> void writeArray(ByteBuf buffer, Collection<T> array, BiConsumer<ByteBuf, T> biConsumer) {
        VarInts.writeUnsignedInt(buffer, array.size());
        for (T val : array) {
            biConsumer.accept(buffer, val);
        }
    }

    @Override
    public <T> T[] readArray(ByteBuf buffer, T[] array, Function<ByteBuf, T> function) {
        ObjectArrayList<T> list = new ObjectArrayList<>();
        readArray(buffer, list, function);
        return list.toArray(array);
    }

    @Override
    public <T> void writeArray(ByteBuf buffer, T[] array, BiConsumer<ByteBuf, T> biConsumer) {
        VarInts.writeUnsignedInt(buffer, array.length);
        for (T val : array) {
            biConsumer.accept(buffer, val);
        }
    }

    @Override
    public int[] readIntArray(ByteBuf buffer, ToIntFunction<ByteBuf> function) {
        int[] array = new int[VarInts.readUnsignedInt(buffer)];
        for (int i = 0; i < array.length; i++) {
            array[i] = function.applyAsInt(buffer);
        }
        return array;
    }

    @Override
    public void writeIntArray(ByteBuf buffer, int[] array, ObjIntConsumer<ByteBuf> consumer) {
        VarInts.writeUnsignedInt(buffer, array.length);
        for (int val : array) {
            consumer.accept(buffer, val);
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> T readTag(ByteBuf buffer) {
        try (NBTInputStream reader = NbtUtils.createReader(new ByteBufInputStream(buffer))) {
            return (T) reader.readTag();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public <T> void writeTag(ByteBuf buffer, T tag) {
        try (NBTOutputStream writer = NbtUtils.createWriter(new ByteBufOutputStream(buffer))) {
            writer.writeTag(tag);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Component readComponent(ByteBuf buffer) {
        return GsonComponentSerializer.gson().deserialize(this.readString(buffer));
    }

    @Override
    public void writeComponent(ByteBuf buffer, Component component) {
        this.writeString(buffer, GsonComponentSerializer.gson().serialize(component));
    }

    @Override
    public Pose readPose(ByteBuf buffer) {
        Preconditions.checkNotNull(buffer, "buffer");
        return this.getPose(this.readVarInt(buffer));
    }

    @Override
    public void writePose(ByteBuf buffer, Pose pose) {
        Preconditions.checkNotNull(buffer, "buffer");
        Preconditions.checkNotNull(pose, "pose");
        this.writeVarInt(buffer, this.getPoseId(pose));
    }

    @Override
    public <T> EntityData<T> readEntityData(int dataId, int id, ByteBuf buffer) {
        EntityDataType<T> type = this.getEntityDataType(id);
        return new EntityData<>(dataId, type, type.read(this, buffer));
    }

    @Override
    public <T> void writeEntityData(EntityData<T> data, ByteBuf buffer) {
        data.getType().write(this, buffer, data.getValue());
    }

    @Override
    public <T> T readOptional(ByteBuf buffer, Function<ByteBuf, T> readFunction) {
        if (buffer.readBoolean()) {
            return readFunction.apply(buffer);
        }
        return null;
    }

    @Override
    public <T> void writeOptional(ByteBuf buffer, T value, BiConsumer<ByteBuf, T> writeFunction) {
        buffer.writeBoolean(value != null);
        if (value != null) {
            writeFunction.accept(buffer, value);
        }
    }

    @Override
    public final int getEntityId(EntityType entityType) {
        return this.entityTypes.getId(entityType);
    }

    @Override
    public final EntityType getEntityType(int entityId) {
        return this.entityTypes.getType(entityId);
    }

    @Override
    public final int getBlockEntityActionId(BlockEntityAction action) {
        return this.blockEntityActions.getId(action);
    }

    @Override
    public final BlockEntityAction getBlockEntityAction(int actionId) {
        return this.blockEntityActions.getType(actionId);
    }

    @Override
    public final int getContainerId(ContainerType containerType) {
        return this.containerTypes.getId(containerType);
    }

    @Override
    public final ContainerType getContainerType(int containerId) {
        return this.containerTypes.getType(containerId);
    }

    @Override
    public final Pose getPose(int id) {
        return this.poses.getType(id);
    }

    @Override
    public final int getPoseId(Pose pose) {
        return this.poses.getId(pose);
    }

    @Override
    @SuppressWarnings("unchecked")
    public final <T> EntityDataType<T> getEntityDataType(int id) {
        return (EntityDataType<T>) this.entityDataTypes.getType(id);
    }

    @Override
    public final int getEntityDataTypeId(EntityDataType<?> type) {
        return this.entityDataTypes.getId(type);
    }

    @Override
    public final ParticleType getParticle(int id) {
        return this.particles.getType(id);
    }

    @Override
    public final int getParticleId(ParticleType particle) {
        return this.particles.getId(particle);
    }

    @Override
    public final MobEffectType getMobEffect(int id) {
        return this.mobEffects.getType(id);
    }

    @Override
    public final int getMobEffectId(MobEffectType mobEffect) {
        return this.mobEffects.getId(mobEffect);
    }

    @Override
    public final RecipeType<? extends Recipe> getRecipeType(Key key) {
        return this.recipeTypes.get(key);
    }

    @Override
    public final Key getRecipeTypeKey(RecipeType<?> recipeType) {
        return this.recipeTypes.inverse().get(recipeType);
    }

}
