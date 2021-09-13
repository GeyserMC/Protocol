package org.cloudburstmc.protocol.java.data.world;

import lombok.Value;

@Value
public class PositionSource<T> {
    PositionSourceType<T> type;
    T value;
}
