package org.cloudburstmc.protocol.bedrock.packet;

import com.nukkitx.math.vector.Vector3i;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.cloudburstmc.protocol.bedrock.data.inventory.LabTableReactionType;
import org.cloudburstmc.protocol.bedrock.data.inventory.LabTableType;
import org.cloudburstmc.protocol.common.PacketSignal;

@Data
@EqualsAndHashCode(doNotUseGetters = true)
@ToString(doNotUseGetters = true)
public class LabTablePacket implements BedrockPacket {
    private LabTableType type;
    private Vector3i position;
    private LabTableReactionType reactionType;

    @Override
    public final PacketSignal handle(BedrockPacketHandler handler) {
        return handler.handle(this);
    }

    public BedrockPacketType getPacketType() {
        return BedrockPacketType.LAB_TABLE;
    }
}
