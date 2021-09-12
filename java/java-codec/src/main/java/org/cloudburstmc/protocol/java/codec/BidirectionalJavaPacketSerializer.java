package org.cloudburstmc.protocol.java.codec;

import io.netty.buffer.ByteBuf;
import org.cloudburstmc.protocol.common.exception.PacketSerializeException;
import org.cloudburstmc.protocol.java.JavaSession;
import org.cloudburstmc.protocol.java.packet.BidirectionalJavaPacket;
import org.cloudburstmc.protocol.java.packet.type.JavaPacketType;

public abstract class BidirectionalJavaPacketSerializer<T extends BidirectionalJavaPacket<?>> implements JavaPacketSerializer<T> {

    @Override
    public void serialize(ByteBuf buffer, JavaCodecHelper helper, T packet) throws PacketSerializeException {
        if (packet.getSendingDirection() == JavaPacketType.Direction.CLIENTBOUND) {
            this.serializeClientbound(buffer, helper, packet);
        } else if (packet.getSendingDirection() == JavaPacketType.Direction.SERVERBOUND) {
            this.serializeServerbound(buffer, helper, packet);
        } else {
            throw new PacketSerializeException("Cannot serialize packet with direction bidirectional!", new RuntimeException());
        }
    }

    public void serialize(ByteBuf buffer, JavaCodecHelper helper, T packet, JavaSession session) throws PacketSerializeException {
        if (packet.getSendingDirection() == JavaPacketType.Direction.CLIENTBOUND) {
            this.serializeClientbound(buffer, helper, packet, session);
        } else if (packet.getSendingDirection() == JavaPacketType.Direction.SERVERBOUND) {
            this.serializeServerbound(buffer, helper, packet, session);
        } else {
            throw new PacketSerializeException("Cannot serialize packet with direction bidirectional!", new RuntimeException());
        }
    }

    @Override
    public void deserialize(ByteBuf buffer, JavaCodecHelper helper, T packet) throws PacketSerializeException {
        if (packet.getSendingDirection() == JavaPacketType.Direction.CLIENTBOUND) {
            this.deserializeClientbound(buffer, helper, packet);
        } else if (packet.getSendingDirection() == JavaPacketType.Direction.SERVERBOUND) {
            this.deserializeServerbound(buffer, helper, packet);
        } else {
            throw new PacketSerializeException("Cannot serialize packet with direction bidirectional!", new RuntimeException());
        }
    }

    public void deserialize(ByteBuf buffer, JavaCodecHelper helper, T packet, JavaSession session) throws PacketSerializeException {
        if (packet.getSendingDirection() == JavaPacketType.Direction.CLIENTBOUND) {
            this.deserializeClientbound(buffer, helper, packet, session);
        } else if (packet.getSendingDirection() == JavaPacketType.Direction.SERVERBOUND) {
            this.deserializeServerbound(buffer, helper, packet, session);
        } else {
            throw new PacketSerializeException("Cannot serialize packet with direction bidirectional!", new RuntimeException());
        }
    }

    public void serializeClientbound(ByteBuf buffer, JavaCodecHelper helper, T packet) throws PacketSerializeException {
    }

    public void serializeClientbound(ByteBuf buffer, JavaCodecHelper helper, T packet, JavaSession session) throws PacketSerializeException {
        this.serializeClientbound(buffer, helper, packet);
    }

    public void serializeServerbound(ByteBuf buffer, JavaCodecHelper helper, T packet) throws PacketSerializeException {
    }

    public void serializeServerbound(ByteBuf buffer, JavaCodecHelper helper, T packet, JavaSession session) throws PacketSerializeException {
        this.serializeClientbound(buffer, helper, packet);
    }

    public void deserializeClientbound(ByteBuf buffer, JavaCodecHelper helper, T packet) throws PacketSerializeException {
    }

    public void deserializeClientbound(ByteBuf buffer, JavaCodecHelper helper, T packet, JavaSession session) throws PacketSerializeException {
        this.deserializeClientbound(buffer, helper, packet);
    }

    public void deserializeServerbound(ByteBuf buffer, JavaCodecHelper helper, T packet) throws PacketSerializeException {
    }

    public void deserializeServerbound(ByteBuf buffer, JavaCodecHelper helper, T packet, JavaSession session) throws PacketSerializeException {
        this.deserializeServerbound(buffer, helper, packet);
    }
}