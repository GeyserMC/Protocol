package org.cloudburstmc.protocol.java.data.entity;

import com.nukkitx.math.vector.Vector3f;
import com.nukkitx.math.vector.Vector3i;
import com.nukkitx.nbt.NbtMap;
import io.netty.buffer.ByteBuf;
import net.kyori.adventure.text.Component;
import org.cloudburstmc.protocol.common.util.TriConsumer;
import org.cloudburstmc.protocol.common.util.VarInts;
import org.cloudburstmc.protocol.java.codec.JavaCodecHelper;
import org.cloudburstmc.protocol.java.data.Direction;
import org.cloudburstmc.protocol.java.data.inventory.ItemStack;
import org.cloudburstmc.protocol.java.data.world.Particle;

import java.util.Optional;
import java.util.UUID;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Function;

public class EntityDataType<T> {
    public static final EntityDataType<Byte> BYTE = new EntityDataType<Byte>(ByteBuf::readByte, ByteBuf::writeByte);

    public static final EntityDataType<Integer> INT = new EntityDataType<>(VarInts::readUnsignedInt, VarInts::writeUnsignedInt);

    public static final EntityDataType<Float> FLOAT = new EntityDataType<>(ByteBuf::readFloat, ByteBuf::writeFloat);

    public static final EntityDataType<String> STRING = new EntityDataType<>(JavaCodecHelper::readString, JavaCodecHelper::writeString);

    public static final EntityDataType<Component> COMPONENT = new EntityDataType<>(JavaCodecHelper::readComponent, JavaCodecHelper::writeComponent);

    public static final EntityDataType<Optional<Component>> OPTIONAL_COMPONENT = new EntityDataType<>(readOptional(JavaCodecHelper::readComponent), writeOptional(JavaCodecHelper::writeComponent));

    public static final EntityDataType<ItemStack> ITEM_STACK = new EntityDataType<>(JavaCodecHelper::readItemStack, JavaCodecHelper::writeItemStack);

    public static final EntityDataType<Optional<Integer>> OPTIONAL_BLOCK_STATE = new EntityDataType<>(readOptional(JavaCodecHelper::readVarInt), writeOptional(JavaCodecHelper::writeVarInt));

    public static final EntityDataType<Boolean> BOOLEAN = new EntityDataType<>(ByteBuf::readBoolean, ByteBuf::writeBoolean);

    public static final EntityDataType<Particle> PARTICLE = new EntityDataType<>(JavaCodecHelper::readParticle, JavaCodecHelper::writeParticle);

    public static final EntityDataType<Vector3f> ROTATION = new EntityDataType<>(JavaCodecHelper::readVector3f, JavaCodecHelper::writeVector3f);

    public static final EntityDataType<Vector3i> BLOCK_POS = new EntityDataType<>(JavaCodecHelper::readBlockPosition, JavaCodecHelper::writeBlockPosition);

    public static final EntityDataType<Optional<Vector3i>> OPTIONAL_BLOCK_POS = new EntityDataType<>(readOptional(JavaCodecHelper::readBlockPosition), writeOptional(JavaCodecHelper::writeBlockPosition));

    public static final EntityDataType<Direction> DIRECTION = new EntityDataType<>(JavaCodecHelper::readDirection, JavaCodecHelper::writeDirection);

    public static final EntityDataType<Optional<UUID>> OPTIONAL_UUID = new EntityDataType<>(readOptional(JavaCodecHelper::readUUID), writeOptional(JavaCodecHelper::writeUUID));

    public static final EntityDataType<NbtMap> NBT = new EntityDataType<>(JavaCodecHelper::readTag, JavaCodecHelper::writeTag);

    public static final EntityDataType<VillagerData> VILLAGER_DATA = new EntityDataType<>(JavaCodecHelper::readVillagerData, JavaCodecHelper::writeVillagerData);

    public static final EntityDataType<Optional<Integer>> OPTIONAL_INT = new EntityDataType<>(readOptional(JavaCodecHelper::readVarInt), writeOptional(JavaCodecHelper::writeVarInt));

    public static final EntityDataType<Pose> POSE = new EntityDataType<>(JavaCodecHelper::readPose, JavaCodecHelper::writePose);

    private final BiFunction<JavaCodecHelper, ByteBuf, T> readFunction;
    private final TriConsumer<JavaCodecHelper, ByteBuf, T> writeFunction;

    public EntityDataType(Function<ByteBuf, T> readFunction, BiConsumer<ByteBuf, T> writeFunction) {
        this((helper, buf) -> readFunction.apply(buf), ((helper, buf, t) -> writeFunction.accept(buf, t)));
    }

    public EntityDataType(BiFunction<JavaCodecHelper, ByteBuf, T> readFunction, TriConsumer<JavaCodecHelper, ByteBuf, T> writeFunction) {
        this.readFunction = readFunction;
        this.writeFunction = writeFunction;
    }

    private static <E> BiFunction<JavaCodecHelper, ByteBuf, Optional<E>> readOptional(BiFunction<JavaCodecHelper, ByteBuf, E> readFunction) {
        return ((helper, buf) -> {
            if (!buf.readBoolean()) {
                return Optional.empty();
            }
            return Optional.of(readFunction.apply(helper, buf));
        });
    }

    private static <E> TriConsumer<JavaCodecHelper, ByteBuf, Optional<E>> writeOptional(TriConsumer<JavaCodecHelper, ByteBuf, E> writeFunction) {
        return ((helper, buf, e) -> {
            buf.writeBoolean(e.isPresent());
            e.ifPresent(value -> writeFunction.accept(helper, buf, value));
        });
    }

    public T read(JavaCodecHelper helper, ByteBuf buffer) {
        return this.readFunction.apply(helper, buffer);
    }

    public void write(JavaCodecHelper helper, ByteBuf buffer, T value) {
        this.writeFunction.accept(helper, buffer, value);
    }
}
