package org.cloudburstmc.protocol.java.packet.handler;

import org.cloudburstmc.protocol.common.PacketSignal;
import org.cloudburstmc.protocol.java.packet.login.*;

public interface JavaLoginPacketHandler extends JavaPacketHandler {

    default PacketSignal handle(DisconnectPacket packet) {
        return PacketSignal.UNHANDLED;
    }

    default PacketSignal handle(EncryptionRequestPacket packet) {
        return PacketSignal.UNHANDLED;
    }

    default PacketSignal handle(LoginSuccessPacket packet) {
        return PacketSignal.UNHANDLED;
    }

    default PacketSignal handle(SetCompressionPacket packet) {
        return PacketSignal.UNHANDLED;
    }

    default PacketSignal handle(CustomQueryRequestPacket packet) {
        return PacketSignal.UNHANDLED;
    }

    default PacketSignal handle(LoginStartPacket packet) {
        return PacketSignal.UNHANDLED;
    }

    default PacketSignal handle(EncryptionResponsePacket packet) {
        return PacketSignal.UNHANDLED;
    }

    default PacketSignal handle(CustomQueryResponsePacket packet) {
        return PacketSignal.UNHANDLED;
    }
}
