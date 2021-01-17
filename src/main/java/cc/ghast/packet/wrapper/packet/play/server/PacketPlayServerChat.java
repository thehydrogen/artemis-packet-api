package cc.ghast.packet.wrapper.packet.play.server;

import cc.ghast.packet.nms.ProtocolVersion;
import cc.ghast.packet.buffer.ProtocolByteBuf;
import cc.ghast.packet.wrapper.packet.Packet;
import cc.ghast.packet.wrapper.packet.ServerPacket;
import cc.ghast.packet.wrapper.packet.ReadableBuffer;
import lombok.Getter;

import java.util.UUID;

@Getter
public class PacketPlayServerChat extends Packet<ServerPacket> implements ReadableBuffer {
    public PacketPlayServerChat(UUID player, ProtocolVersion version) {
        super("PacketPlayOutChat", player, version);
    }

    private String text;
    //1.7.10 clients don't send this setting...
    private ChatMessageType type;

    @Override
    public void read(ProtocolByteBuf byteBuf) {
        final int stringBufArg;
        if (version.isBelow(ProtocolVersion.V1_13)) {
            stringBufArg = 32767;
        }
        else {
            stringBufArg = 262144;
        }
        this.text = byteBuf.readStringBuf(stringBufArg);

        if (version.isBelow(ProtocolVersion.V1_8)) {
            this.type = ChatMessageType.CHAT;
        }
        else {
            byte position = byteBuf.readByte();
            this.type = ChatMessageType.getChatMessageType(position);
        }
    }

    public enum ChatMessageType {
        CHAT((byte)0),
        SYSTEM((byte)1),
        GAME_INFO((byte)2);

        private final byte position;

        ChatMessageType(byte position) {
            this.position = position;
        }

        public byte getPosition() {
            return this.position;
        }

        public static ChatMessageType getChatMessageType(final byte position) {
            ChatMessageType[] values = values();

            for (ChatMessageType type : values) {
                if (position == type.position) {
                    return type;
                }
            }
            return CHAT;
        }
    }

}
