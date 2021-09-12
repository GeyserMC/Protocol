package org.cloudburstmc.protocol.java.data;

import lombok.*;
import net.kyori.adventure.text.Component;
import org.cloudburstmc.protocol.java.data.profile.GameProfile;

@Data
@NoArgsConstructor
public class Pong {
    private Component description;

    private Players players;
    private Version version;

    @Builder
    @Value
    public static class Players {
        int max;
        int online;
        GameProfile[] sample = null;
    }

    @Builder
    @Value
    public static class Version {
        String name;
        int protocol;
    }
}
