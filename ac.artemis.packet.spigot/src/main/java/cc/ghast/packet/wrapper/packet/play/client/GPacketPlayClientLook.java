package cc.ghast.packet.wrapper.packet.play.client;

import ac.artemis.packet.spigot.protocol.PacketLink;
import ac.artemis.packet.spigot.wrappers.GPacket;
import ac.artemis.packet.wrapper.client.PacketPlayClientLook;
import cc.ghast.packet.buffer.ProtocolByteBuf;
import cc.ghast.packet.nms.ProtocolVersion;
import cc.ghast.packet.wrapper.packet.ReadableBuffer;
import lombok.Getter;

import java.util.Objects;
import java.util.UUID;

@Getter
@PacketLink(PacketPlayClientLook.class)
public class GPacketPlayClientLook extends GPacket implements ReadableBuffer {
    public GPacketPlayClientLook(UUID player, ProtocolVersion version) {
        super((ProtocolVersion.getGameVersion().isBelow(ProtocolVersion.V1_8)
                ? "" : "PacketPlayInFlying$") + "PacketPlayInLook", player, version);
    }

    private float yaw;
    private float pitch;
    private boolean onGround;

    @Override
    public void read(ProtocolByteBuf byteBuf) {
        this.onGround = byteBuf.readUnsignedByte() != 0;

        this.yaw = byteBuf.readFloat();
        this.pitch = byteBuf.readFloat();
    }

    public float getYaw() {
        return this.yaw;
    }

    public float getPitch() {
        return this.pitch;
    }

    public boolean isOnGround() {
        return this.onGround;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GPacketPlayClientLook that = (GPacketPlayClientLook) o;
        return Float.compare(that.yaw, yaw) == 0 && Float.compare(that.pitch, pitch) == 0 && onGround == that.onGround;
    }

    @Override
    public int hashCode() {
        return Objects.hash(yaw, pitch, onGround);
    }
}
