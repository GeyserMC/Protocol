package org.cloudburstmc.protocol.java.codec;

import lombok.Value;
import org.cloudburstmc.protocol.java.packet.JavaPacket;

import java.util.function.Supplier;

@Value
public class JavaPacketDefinition<T extends JavaPacket<?>> {
    int id;
    Supplier<T> factory;
    JavaPacketSerializer<JavaPacket<?>> serializer;
}
