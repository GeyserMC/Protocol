package org.cloudburstmc.protocol.java.packet;

import org.cloudburstmc.protocol.common.MinecraftPacket;
import org.cloudburstmc.protocol.common.PacketHandler;
import org.cloudburstmc.protocol.common.PacketSignal;
import org.cloudburstmc.protocol.java.packet.type.JavaPacketType;

public interface JavaPacket<T extends PacketHandler> extends MinecraftPacket {

    PacketSignal handle(T handler);

    JavaPacketType getPacketType();
}
