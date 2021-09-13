package org.cloudburstmc.protocol.java.data.world;

import com.nukkitx.math.vector.Vector3i;
import io.netty.buffer.ByteBuf;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import net.kyori.adventure.key.Key;
import org.cloudburstmc.protocol.java.codec.JavaCodecHelper;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
public abstract class PositionSourceType<T> {
    public static final PositionSourceType<Vector3i> BLOCK = new PositionSourceType<Vector3i>(Key.key("block")) {

        @Override
        public Vector3i read(JavaCodecHelper helper, ByteBuf buffer) {
            return helper.readBlockPosition(buffer);
        }

        @Override
        public void write(JavaCodecHelper helper, ByteBuf buffer, Vector3i value) {
            helper.writeBlockPosition(buffer, value);
        }
    };

    public static final PositionSourceType<Integer> ENTITY = new PositionSourceType<Integer>(Key.key("entity")) {

        @Override
        public Integer read(JavaCodecHelper helper, ByteBuf buffer) {
            return helper.readVarInt(buffer);
        }

        @Override
        public void write(JavaCodecHelper helper, ByteBuf buffer, Integer value) {
            helper.writeVarInt(buffer, value);
        }
    };

    private final Key identifier;

    public abstract T read(JavaCodecHelper helper, ByteBuf buffer);

    public abstract void write(JavaCodecHelper helper, ByteBuf buffer, T value);
}
