package org.cloudburstmc.protocol.java;

import com.nukkitx.network.util.EventLoops;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelPipeline;
import io.netty.util.concurrent.DefaultPromise;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.ImmediateEventExecutor;
import io.netty.util.concurrent.Promise;
import org.cloudburstmc.protocol.common.util.Preconditions;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.handler.timeout.ReadTimeoutHandler;
import lombok.Getter;
import lombok.Setter;
import org.cloudburstmc.protocol.common.PacketSignal;
import org.cloudburstmc.protocol.java.data.Pong;
import org.cloudburstmc.protocol.java.packet.handler.JavaStatusPacketHandler;
import org.cloudburstmc.protocol.java.packet.State;
import org.cloudburstmc.protocol.java.packet.handshake.HandshakingPacket;
import org.cloudburstmc.protocol.java.packet.status.StatusRequestPacket;
import org.cloudburstmc.protocol.java.packet.status.StatusResponsePacket;
import org.cloudburstmc.protocol.java.packet.type.JavaPacketType;
import org.cloudburstmc.protocol.java.pipeline.PacketDecoder;
import org.cloudburstmc.protocol.java.pipeline.PacketEncoder;
import org.cloudburstmc.protocol.java.pipeline.VarInt21FrameDecoder;
import org.cloudburstmc.protocol.java.pipeline.VarInt21LengthFieldPrepender;

import javax.annotation.Nullable;
import java.net.InetSocketAddress;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

@Getter
public class JavaClient extends Java<Bootstrap> {
    private JavaClientSession session;
    private boolean closed = false;
    private boolean connected = false;

    private Channel channel;
    private final EventLoopGroup networkGroup;

    @Setter private JavaEventHandler<JavaClientSession> handler;

    public JavaClient() {
        this(EventLoops.commonGroup(), null);
    }

    public JavaClient(EventLoopGroup javaGroup) {
        this(javaGroup, null);
    }

    public JavaClient(EventLoopGroup javaGroup, @Nullable EventLoopGroup networkGroup) {
        super(javaGroup, Bootstrap::new);

        this.networkGroup = networkGroup;
    }

    @Override
    public InetSocketAddress getBindAddress() {
        if (this.bindAddress == null) {
            return (InetSocketAddress) this.session.getChannel().localAddress();
        }

        return super.getBindAddress();
    }

    @Override
    protected void onTick() {
        if (this.session.isConnected()) {
            this.session.tick();
        }
    }

    @Override
    protected Future<Void> doBind(InetSocketAddress address, ChannelFutureListener listener) {
        Bootstrap bootstrap = this.createBootstrap();
        ChannelFuture channelFuture = bootstrap.bind();
        channelFuture.addListener(listener);

        Promise<Void> promise = new DefaultPromise<>(ImmediateEventExecutor.INSTANCE);
        channelFuture.addListener((ChannelFuture f) -> {
            if (f.cause() == null) {
                this.channel = f.channel();
                promise.setSuccess(null);
            } else {
                promise.setFailure(f.cause());
            }
        });
        return promise;
    }

    public java.util.concurrent.Future<Pong> ping(InetSocketAddress remoteAddress) {
        return this.ping(remoteAddress, response -> this.disconnect(null));
    }

    public java.util.concurrent.Future<Pong> ping(InetSocketAddress remoteAddress, Consumer<StatusResponsePacket> response) {
        Preconditions.checkState(!this.connected, "Client is already connected!");
        ChannelFuture connectFuture = this.connect(remoteAddress);
        CompletableFuture<Pong> pongFuture = new CompletableFuture<>();
        connectFuture.addListener(aVoid -> {
            HandshakingPacket handshakingPacket = new HandshakingPacket();
            handshakingPacket.setProtocolVersion(this.session.getPacketCodec().getProtocolVersion());
            handshakingPacket.setNextState(State.STATUS);
            handshakingPacket.setAddress(this.getBindAddress().getHostName());
            handshakingPacket.setPort(this.getBindAddress().getPort());

            this.session.sendPacket(handshakingPacket);
            this.session.setProtocolState(State.STATUS);
            this.session.sendPacket(new StatusRequestPacket());

            this.session.setPacketHandler(new JavaStatusPacketHandler() {
                @Override
                public PacketSignal handle(StatusResponsePacket packet) {
                    response.accept(packet);
                    pongFuture.complete(packet.getResponse());
                    return PacketSignal.HANDLED;
                }
            });
        });

        return pongFuture;
    }

    public ChannelFuture connect(InetSocketAddress address) {
        return this.connect(address, 10, TimeUnit.SECONDS);
    }

    public ChannelFuture connect(InetSocketAddress address, long timeout, TimeUnit unit) {
        if (this.bindAddress != null) {
            throw new IllegalStateException("Already bind");
        }
        this.bindAddress = address;

        Bootstrap bootstrap = this.createBootstrap(pipeline -> pipeline.addLast("timeout", new ReadTimeoutHandler((int) unit.toMillis(timeout))));
        bootstrap.option(ChannelOption.CONNECT_TIMEOUT_MILLIS, (int) unit.toMillis(timeout));
        bootstrap.remoteAddress(address);

        ChannelFuture channelFuture = bootstrap.connect();
        channelFuture.addListener((ChannelFuture f) -> {
            if (f.cause() == null) {
                this.channel = f.channel();
            }
        });
        return channelFuture;
    }

    private Bootstrap createBootstrap() {
        return this.createBootstrap(null);
    }

    private Bootstrap createBootstrap(@Nullable Consumer<ChannelPipeline> channelPipelineConsumer) {
        EventLoopGroup eventLoopGroup = this.networkGroup;
        if (eventLoopGroup == null) {
            eventLoopGroup = EventLoops.newEventLoopGroup(1);
        }

        return this.bootstrapSupplier.get()
                .channel(EventLoops.getChannelType().getSocketChannel())
                .group(eventLoopGroup)
                .localAddress(this.bindAddress)
                .option(ChannelOption.TCP_NODELAY, true)
                .handler(new ChannelInitializer<Channel>() {
                    @Override
                    public void initChannel(Channel channel) throws Exception {
                        JavaClientSession session = new JavaClientSession(JavaClient.this, (InetSocketAddress) channel.localAddress(), channel, channel.eventLoop());

                        ChannelPipeline pipeline = channel.pipeline();
                        if (channelPipelineConsumer != null) {
                            channelPipelineConsumer.accept(pipeline);
                        }

                        pipeline
                                .addLast("splitter", new VarInt21FrameDecoder())
                                .addLast("prepender", new VarInt21LengthFieldPrepender())
                                .addLast("encoder", new PacketEncoder(session))
                                .addLast("decoder", new PacketDecoder(session, JavaPacketType.Direction.CLIENTBOUND))
                                .addLast("session", session);
                        JavaClient.this.session = session;
                        JavaClient.this.handler.onSessionCreation(session);
                    }
                });
    }

    public void disconnect(String reason) {
        this.session.disconnect(reason);
        this.session = null;
        this.connected = false;
    }

    @Override
    public void close() {
        this.close("disconnect.disconnected");
    }

    public void close(String reason) {
        this.session.disconnect(reason);
        this.closed = true;
    }
}
