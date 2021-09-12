package org.cloudburstmc.protocol.java;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.netty.bootstrap.AbstractBootstrap;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.EventLoopGroup;
import io.netty.util.concurrent.Future;
import lombok.Getter;
import lombok.Setter;
import net.kyori.adventure.text.serializer.gson.GsonComponentSerializer;
import org.cloudburstmc.protocol.common.MinecraftInterface;
import org.cloudburstmc.protocol.java.data.profile.GameProfile;
import org.cloudburstmc.protocol.java.data.profile.property.PropertyMap;
import org.cloudburstmc.protocol.java.util.json.GameProfileSerializer;
import org.cloudburstmc.protocol.java.util.json.PropertyMapSerializer;
import org.cloudburstmc.protocol.java.util.json.UUIDTypeAdapter;

import java.net.InetSocketAddress;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

@Getter
@Setter
public abstract class Java<T extends AbstractBootstrap<?, ?>> implements MinecraftInterface {
    public static final Gson GSON = GsonComponentSerializer.gson()
            .populator()
            .apply(new GsonBuilder()
                    .registerTypeAdapter(GameProfile.class, new GameProfileSerializer())
                    .registerTypeAdapter(PropertyMap.class, new PropertyMapSerializer())
                    .registerTypeAdapter(UUID.class, new UUIDTypeAdapter())
            )
            .setPrettyPrinting()
            .create();

    protected InetSocketAddress bindAddress;
    protected final EventLoopGroup eventLoopGroup;
    protected final Supplier<T> bootstrapSupplier;
    protected boolean handleLogin = true;

    Java(EventLoopGroup eventLoopGroup, Supplier<T> bootstrapSupplier) {
        this.bootstrapSupplier = bootstrapSupplier;
        this.eventLoopGroup = eventLoopGroup;
        eventLoopGroup.scheduleAtFixedRate(this::onTick, 50, 50, TimeUnit.MILLISECONDS);
    }

    protected abstract void onTick();

    public InetSocketAddress getBindAddress() {
        return this.bindAddress;
    }

    public Future<Void> bind(InetSocketAddress address) {
        return this.bind(address, f -> {});
    }

    public Future<Void> bind(InetSocketAddress address, ChannelFutureListener listener) {
        if (this.bindAddress != null) {
            throw new IllegalStateException("Already bind");
        }
        this.bindAddress = address;
        return this.doBind(address, listener);
    }

    protected abstract Future<Void> doBind(InetSocketAddress address, ChannelFutureListener listener);

    public abstract void close();
}
