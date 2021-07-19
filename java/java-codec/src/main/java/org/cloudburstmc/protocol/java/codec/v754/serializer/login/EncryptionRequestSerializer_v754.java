package org.cloudburstmc.protocol.java.codec.v754.serializer.login;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.common.exception.PacketSerializeException;
import org.cloudburstmc.protocol.common.util.VarInts;
import org.cloudburstmc.protocol.java.codec.JavaCodecHelper;
import org.cloudburstmc.protocol.java.codec.JavaPacketSerializer;
import org.cloudburstmc.protocol.java.packet.login.EncryptionRequestPacket;

import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class EncryptionRequestSerializer_v754 implements JavaPacketSerializer<EncryptionRequestPacket> {
    public static final EncryptionRequestSerializer_v754 INSTANCE = new EncryptionRequestSerializer_v754();

    @Override
    public void serialize(ByteBuf buffer, JavaCodecHelper helper, EncryptionRequestPacket packet) {
        VarInts.writeUnsignedInt(buffer, 0);
        helper.writeByteArray(buffer, packet.getPublicKey().getEncoded());
        helper.writeByteArray(buffer, packet.getVerifyToken());
    }

    @Override
    public void deserialize(ByteBuf buffer, JavaCodecHelper helper, EncryptionRequestPacket packet) throws PacketSerializeException {
        VarInts.readUnsignedInt(buffer);
        try {
            packet.setPublicKey(KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(helper.readByteArray(buffer))));
        } catch (InvalidKeySpecException | NoSuchAlgorithmException ex) {
            throw new PacketSerializeException("Failed to decode public key!", ex);
        }
        packet.setVerifyToken(helper.readByteArray(buffer));
    }
}
