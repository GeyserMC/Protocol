package org.cloudburstmc.protocol.java.codec.v754.serializer.login;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.java.codec.JavaCodecHelper;
import org.cloudburstmc.protocol.java.codec.JavaPacketSerializer;
import org.cloudburstmc.protocol.java.packet.login.LoginSuccessPacket;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LoginSuccessSerializer_v754 implements JavaPacketSerializer<LoginSuccessPacket> {
    public static final LoginSuccessSerializer_v754 INSTANCE = new LoginSuccessSerializer_v754();

    @Override
    public void serialize(ByteBuf buffer, JavaCodecHelper helper, LoginSuccessPacket packet) {
        helper.writeGameProfile(buffer, packet.getProfile(), false);
    }

    @Override
    public void deserialize(ByteBuf buffer, JavaCodecHelper helper, LoginSuccessPacket packet) {
        packet.setProfile(helper.readGameProfile(buffer, false));
    }
}
