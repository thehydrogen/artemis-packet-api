package cc.ghast.packet.wrapper.packet.status;

import cc.ghast.packet.buffer.ProtocolByteBuf;
import cc.ghast.packet.nms.ProtocolVersion;
import ac.artemis.packet.spigot.wrappers.GPacket;
import cc.ghast.packet.wrapper.packet.ReadableBuffer;

import java.util.UUID;

public class PacketStatusServerInfoServer extends GPacket implements ReadableBuffer {

    public PacketStatusServerInfoServer(UUID player, ProtocolVersion version) {
        super("PacketStatusOutInfoServer", player, version);
    }


    @Override
    public void read(ProtocolByteBuf byteBuf) {

    }
}