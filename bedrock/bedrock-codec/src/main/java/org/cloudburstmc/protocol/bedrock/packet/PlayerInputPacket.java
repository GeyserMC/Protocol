package org.cloudburstmc.protocol.bedrock.packet;

import com.nukkitx.math.vector.Vector2f;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.cloudburstmc.protocol.common.PacketSignal;

@Data
@EqualsAndHashCode(doNotUseGetters = true)
@ToString(doNotUseGetters = true)
public class PlayerInputPacket implements BedrockPacket {
    private Vector2f inputMotion;
    private boolean jumping;
    private boolean sneaking;

    @Override
    public final PacketSignal handle(BedrockPacketHandler handler) {
        return handler.handle(this);
    }

    public BedrockPacketType getPacketType() {
        return BedrockPacketType.PLAYER_INPUT;
    }
}
