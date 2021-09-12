package org.cloudburstmc.protocol.java.packet.login;

import lombok.Data;
import lombok.EqualsAndHashCode;
import net.kyori.adventure.key.Key;
import org.cloudburstmc.protocol.common.PacketSignal;
import org.cloudburstmc.protocol.java.packet.JavaPacket;
import org.cloudburstmc.protocol.java.packet.handler.JavaLoginPacketHandler;
import org.cloudburstmc.protocol.java.packet.type.JavaLoginPacketType;
import org.cloudburstmc.protocol.java.packet.type.JavaPacketType;

@Data
@EqualsAndHashCode(doNotUseGetters = true, callSuper = false)
public class CustomQueryRequestPacket implements JavaPacket<JavaLoginPacketHandler> {
    private int transactionId;
    private Key identifier;
    private byte[] data;

    @Override
    public PacketSignal handle(JavaLoginPacketHandler handler) {
        return handler.handle(this);
    }

    @Override
    public JavaPacketType getPacketType() {
        return JavaLoginPacketType.CUSTOM_QUERY_REQUEST;
    }
}
