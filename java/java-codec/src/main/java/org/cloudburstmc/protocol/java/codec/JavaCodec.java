package org.cloudburstmc.protocol.java.codec;

import io.netty.buffer.ByteBuf;
import io.netty.util.ReferenceCountUtil;
import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.InternalLoggerFactory;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.cloudburstmc.protocol.common.exception.PacketSerializeException;
import org.cloudburstmc.protocol.java.packet.BidirectionalJavaPacket;
import org.cloudburstmc.protocol.java.packet.JavaPacket;
import org.cloudburstmc.protocol.java.packet.State;
import org.cloudburstmc.protocol.java.packet.type.JavaPacketType;
import org.lanternpowered.lmbda.LambdaFactory;
import org.lanternpowered.lmbda.MethodHandlesExtensions;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.Immutable;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.util.EnumMap;
import java.util.IdentityHashMap;
import java.util.Map;
import java.util.function.Supplier;

import static org.cloudburstmc.protocol.common.util.Preconditions.*;

@Getter
@Immutable
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public final class JavaCodec {
    private static final InternalLogger log = InternalLoggerFactory.getInstance(JavaCodec.class);
    private final int protocolVersion;
    private final String minecraftVersion;
    private final Supplier<JavaCodecHelper> helperFactory;
    
    private final EnumMap<State, JavaStateCodec> stateCodecs;

    public JavaStateCodec getCodec(@Nonnull State protocolState) {
        return this.stateCodecs.get(protocolState);
    }

    public static Builder builder() {
        return new Builder();
    }

    public Builder toBuilder() {
        Builder builder = new Builder();

        builder.protocolVersion = this.protocolVersion;
        builder.stateCodecs = this.stateCodecs;
        builder.minecraftVersion = this.minecraftVersion;
        builder.helperFactory = this.helperFactory;

        return builder;
    }
        
    public static class Builder {
        private int protocolVersion = -1;
        private String minecraftVersion = null;
        private Supplier<JavaCodecHelper> helperFactory;
        private EnumMap<State, JavaStateCodec> stateCodecs = new EnumMap<>(State.class);

        public Builder protocolVersion(@Nonnegative int protocolVersion) {
            checkArgument(protocolVersion >= 0, "protocolVersion cannot be negative");
            this.protocolVersion = protocolVersion;
            return this;
        }

        public Builder minecraftVersion(@Nonnull String minecraftVersion) {
            checkNotNull(minecraftVersion, "minecraftVersion");
            checkArgument(!minecraftVersion.isEmpty() && minecraftVersion.split("\\.").length > 2, "Invalid minecraftVersion");
            this.minecraftVersion = minecraftVersion;
            return this;
        }

        public Builder helper(@Nonnull Supplier<JavaCodecHelper> helperFactory) {
            checkNotNull(helperFactory, "helperFactory");
            this.helperFactory = helperFactory;
            return this;
        }

        public Builder codec(@Nonnull State protocolState, @Nonnull JavaStateCodec codec) {
            checkNotNull(this.helperFactory, "helperFactory");
            checkNotNull(protocolState, "protocolState");
            checkNotNull(codec, "codec");
            this.stateCodecs.put(protocolState, codec);
            codec.helperFactory = this.helperFactory;
            return this;
        }

        public JavaCodec build() {
            checkArgument(this.protocolVersion >= 0, "No protocol version defined");
            checkNotNull(this.minecraftVersion, "No Minecraft version defined");
            checkNotNull(this.helperFactory, "helperFactory cannot be null");
            checkState(this.stateCodecs != null && !this.stateCodecs.isEmpty(), "Codecs cannot be empty");

            return new JavaCodec(this.protocolVersion, this.minecraftVersion, this.helperFactory, this.stateCodecs);
        }
    }
    
    @Immutable
    @RequiredArgsConstructor(access = AccessLevel.PRIVATE)
    public static final class JavaStateCodec {

        private final JavaPacketDefinition<? extends JavaPacket<?>>[] clientboundPacketsById;
        private final JavaPacketDefinition<? extends JavaPacket<?>>[] serverboundPacketsById;
        private final Map<Class<? extends JavaPacket<?>>, JavaPacketDefinition<? extends JavaPacket<?>>> clientboundPacketsByClass;
        private final Map<Class<? extends JavaPacket<?>>, JavaPacketDefinition<? extends JavaPacket<?>>> serverboundPacketsByClass;

        private Supplier<JavaCodecHelper> helperFactory;
        
        public static Builder builder() {
            return new Builder();
        }

        @SuppressWarnings({"unchecked", "rawTypes"})
        public JavaPacket<?> tryDecode(JavaCodecHelper helper, ByteBuf buf, int id, JavaPacketType.Direction direction) throws PacketSerializeException {
            boolean clientbound = direction == JavaPacketType.Direction.CLIENTBOUND;
            JavaPacketDefinition<? extends JavaPacket<?>> definition = getPacketDefinition(id, clientbound);
            JavaPacket<?> packet;
            JavaPacketSerializer<JavaPacket<?>> serializer;
            packet = definition.getFactory().get();
            serializer = definition.getSerializer();
            if (packet instanceof BidirectionalJavaPacket) {
                ((BidirectionalJavaPacket<?>) packet).setSendingDirection(direction);
            }

            try {
                serializer.deserialize(buf, helper, packet);
            } catch (Exception e) {
                throw new PacketSerializeException("Error whilst deserializing " + packet, e);
            }

            if (log.isDebugEnabled() && buf.isReadable()) {
                log.debug(packet.getClass().getSimpleName() + " still has " + buf.readableBytes() + " bytes to read!");
            }
            return packet;
        }

        @SuppressWarnings("unchecked")
        public <T extends JavaPacket<?>> void tryEncode(JavaCodecHelper helper, ByteBuf buf, T packet, boolean clientbound) throws PacketSerializeException {
            try {
                JavaPacketSerializer<JavaPacket<?>> serializer;
                if (packet instanceof BidirectionalJavaPacket) {
                    ((BidirectionalJavaPacket<?>) packet).setSendingDirection(clientbound ? JavaPacketType.Direction.CLIENTBOUND : JavaPacketType.Direction.SERVERBOUND);
                }
                JavaPacketDefinition<T> definition = getPacketDefinition((Class<T>) packet.getClass(), clientbound);
                serializer = definition.getSerializer();
                serializer.serialize(buf, helper, packet);
            } catch (Exception e) {
                throw new PacketSerializeException("Error whilst serializing " + packet, e);
            } finally {
                ReferenceCountUtil.release(packet);
            }
        }

        @SuppressWarnings("unchecked")
        public <T extends JavaPacket<?>> JavaPacketDefinition<T> getPacketDefinition(Class<T> packet, boolean clientbound) {
            checkNotNull(packet, "packet");
            if (clientbound) {
                return (JavaPacketDefinition<T>) clientboundPacketsByClass.get(packet);
            } else {
                return (JavaPacketDefinition<T>) serverboundPacketsByClass.get(packet);
            }
        }

        public JavaPacketDefinition<? extends JavaPacket<?>> getPacketDefinition(int id, boolean clientbound) {
            if (clientbound) {
                checkElementIndex(id, this.clientboundPacketsById.length);
                return clientboundPacketsById[id];
            } else {
                checkElementIndex(id, this.serverboundPacketsById.length);
                return serverboundPacketsById[id];
            }
        }

        @SuppressWarnings("unchecked")
        public Builder toBuilder() {
            Builder builder = new Builder();

            builder.clientboundPackets.putAll(this.clientboundPacketsByClass);
            builder.serverboundPackets.putAll(this.serverboundPacketsByClass);

            return builder;
        }

        @SuppressWarnings({"unchecked", "ConstantConditions"})
        @NoArgsConstructor(access = AccessLevel.PRIVATE)
        public static class Builder {
            private final Map<Class<? extends JavaPacket<?>>, JavaPacketDefinition<? extends JavaPacket<?>>> clientboundPackets = new IdentityHashMap<>();
            private final Map<Class<? extends JavaPacket<?>>, JavaPacketDefinition<? extends JavaPacket<?>>> serverboundPackets = new IdentityHashMap<>();

            @SuppressWarnings("rawTypes")
            public <T extends JavaPacket<?>> Builder registerClientboundPacket(Class<T> packetClass, JavaPacketSerializer<T> serializer, @Nonnegative int id) {
                checkArgument(id >= 0, "id cannot be negative");
                checkArgument(!clientboundPackets.containsKey(packetClass), "Packet class already registered");

                Supplier<Object> factory;
                try {
                    MethodHandles.Lookup lookup = MethodHandlesExtensions.privateLookupIn(packetClass, MethodHandles.lookup());
                    MethodHandle handle = lookup.findConstructor(packetClass, MethodType.methodType(void.class));
                    factory = LambdaFactory.createSupplier(handle);
                } catch (NoSuchMethodException | IllegalAccessException e) {
                    throw new IllegalArgumentException("Unable to find suitable constructor for packet factory", e);
                }

                JavaPacketDefinition<T> info = new JavaPacketDefinition<T>(id, (Supplier) factory, (JavaPacketSerializer<JavaPacket<?>>) serializer);

                clientboundPackets.put(packetClass, info);

                return this;
            }

            public <T extends JavaPacket<?>> Builder updateClientboundSerializer(Class<T> packetClass, JavaPacketSerializer<JavaPacket<?>> serializer) {
                JavaPacketDefinition<T> info = (JavaPacketDefinition<T>) clientboundPackets.get(packetClass);
                checkArgument(info != null, "Packet does not exist");
                JavaPacketDefinition<T> updatedInfo = new JavaPacketDefinition<>(info.getId(), info.getFactory(), serializer);

                clientboundPackets.replace(packetClass, info, updatedInfo);

                return this;
            }

            public Builder deregisterClientboundPacket(Class<? extends JavaPacket<?>> packetClass) {
                checkNotNull(packetClass, "packetClass");

                JavaPacketDefinition<? extends JavaPacket<?>> info = clientboundPackets.remove(packetClass);
                return this;
            }

            public <T extends JavaPacket<?>> Builder registerServerboundPacket(Class<T> packetClass, JavaPacketSerializer<T> serializer, @Nonnegative int id) {
                checkArgument(id >= 0, "id cannot be negative");
                checkArgument(!serverboundPackets.containsKey(packetClass), "Packet class already registered");

                Supplier<Object> factory;
                try {
                    MethodHandles.Lookup lookup = MethodHandlesExtensions.privateLookupIn(packetClass, MethodHandles.lookup());
                    MethodHandle handle = lookup.findConstructor(packetClass, MethodType.methodType(void.class));
                    factory = LambdaFactory.createSupplier(handle);
                } catch (NoSuchMethodException | IllegalAccessException e) {
                    throw new IllegalArgumentException("Unable to find suitable constructor for packet factory", e);
                }

                JavaPacketDefinition<T> info = new JavaPacketDefinition<T>(id, (Supplier) factory, (JavaPacketSerializer<JavaPacket<?>>) serializer);

                serverboundPackets.put(packetClass, info);

                return this;
            }

            public <T extends JavaPacket<?>> Builder updateServerboundSerializer(Class<T> packetClass, JavaPacketSerializer<JavaPacket<?>> serializer) {
                JavaPacketDefinition<T> info = (JavaPacketDefinition<T>) serverboundPackets.get(packetClass);
                checkArgument(info != null, "Packet does not exist");
                JavaPacketDefinition<T> updatedInfo = new JavaPacketDefinition<>(info.getId(), info.getFactory(), serializer);

                serverboundPackets.replace(packetClass, info, updatedInfo);

                return this;
            }

            public Builder deregisterServerboundPacket(Class<? extends JavaPacket<?>> packetClass) {
                checkNotNull(packetClass, "packetClass");

                JavaPacketDefinition<? extends JavaPacket<?>> info = serverboundPackets.remove(packetClass);
                return this;
            }

            public JavaStateCodec build() {
                int largestId = -1;
                for (JavaPacketDefinition<? extends JavaPacket<?>> info : clientboundPackets.values()) {
                    if (info.getId() > largestId) {
                        largestId = info.getId();
                    }
                }
                checkArgument(largestId > -1, "Must have at least one packet registered");
                JavaPacketDefinition<? extends JavaPacket<?>>[] clientboundPacketsById = new JavaPacketDefinition[largestId + 1];

                for (JavaPacketDefinition<? extends JavaPacket<?>> info : clientboundPackets.values()) {
                    clientboundPacketsById[info.getId()] = info;
                }

                largestId = -1;
                for (JavaPacketDefinition<? extends JavaPacket<?>> info : serverboundPackets.values()) {
                    if (info.getId() > largestId) {
                        largestId = info.getId();
                    }
                }
                checkArgument(largestId > -1, "Must have at least one packet registered");
                JavaPacketDefinition<? extends JavaPacket<?>>[] serverboundPacketsById = new JavaPacketDefinition[largestId + 1];

                for (JavaPacketDefinition<? extends JavaPacket<?>> info : serverboundPackets.values()) {
                    serverboundPacketsById[info.getId()] = info;
                }
                return new JavaStateCodec(clientboundPacketsById, serverboundPacketsById, clientboundPackets, serverboundPackets);
            }
        }
    }
}
