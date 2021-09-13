package org.cloudburstmc.protocol.java.codec.v755;

import io.netty.buffer.ByteBuf;
import net.kyori.adventure.key.Key;
import org.cloudburstmc.protocol.common.util.Preconditions;
import org.cloudburstmc.protocol.common.util.TypeMap;
import org.cloudburstmc.protocol.java.codec.v754.JavaCodecHelper_v754;
import org.cloudburstmc.protocol.java.data.entity.EntityDataType;
import org.cloudburstmc.protocol.java.data.entity.EntityType;
import org.cloudburstmc.protocol.java.data.entity.MobEffectType;
import org.cloudburstmc.protocol.java.data.entity.Pose;
import org.cloudburstmc.protocol.java.data.inventory.ContainerType;
import org.cloudburstmc.protocol.java.data.world.BlockEntityAction;
import org.cloudburstmc.protocol.java.data.world.ParticleType;
import org.cloudburstmc.protocol.java.data.world.PositionSource;
import org.cloudburstmc.protocol.java.data.world.PositionSourceType;

public class JavaCodecHelper_v755 extends JavaCodecHelper_v754 {

    public JavaCodecHelper_v755(TypeMap<EntityType> entityTypes, TypeMap<BlockEntityAction> blockEntityActions, TypeMap<ContainerType> containerTypes, TypeMap<EntityDataType<?>> entityDataTypes, TypeMap<Pose> poses, TypeMap<ParticleType> particles, TypeMap<MobEffectType> mobEffects) {
        super(entityTypes, blockEntityActions, containerTypes, entityDataTypes, poses, particles, mobEffects);

        this.registerPositionSourceTypes();
    }

    @Override
    public <T> PositionSource<T> readPositionSource(ByteBuf buffer) {
        Preconditions.checkNotNull(buffer, "buffer");
        Key key = this.readKey(buffer);
        PositionSourceType<T> type = this.getPositionSourceType(key);
        return new PositionSource<>(type, type.read(this, buffer));
    }

    @Override
    public <T> void writePositionSource(ByteBuf buffer, PositionSource<T> positionSource) {
        Preconditions.checkNotNull(buffer, "buffer");
        Preconditions.checkNotNull(positionSource, "positionSource");
        PositionSourceType<T> type = positionSource.getType();
        this.writeKey(buffer, type.getIdentifier());
        type.write(this, buffer, positionSource.getValue());
    }

    @Override
    public void registerPositionSourceTypes() {
        this.registerPositionSourceType(PositionSourceType.BLOCK);
        this.registerPositionSourceType(PositionSourceType.ENTITY);
    }
}
