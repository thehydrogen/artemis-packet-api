package cc.ghast.packet.protocol.nms;

import cc.ghast.packet.nms.ProtocolVersion;
import cc.ghast.packet.protocol.ProtocolDirection;
import cc.ghast.packet.utils.PacketPair;
import cc.ghast.packet.protocol.EnumProtocol;
import ac.artemis.packet.spigot.wrappers.GPacket;
import cc.ghast.packet.wrapper.packet.PacketInfo;
import cc.ghast.packet.wrapper.packet.handshake.GPacketHandshakeClientSetProtocol;
import cc.ghast.packet.wrapper.packet.login.*;
import cc.ghast.packet.wrapper.packet.play.client.*;
import cc.ghast.packet.wrapper.packet.play.server.*;
import cc.ghast.packet.wrapper.packet.status.PacketStatusClientPing;
import cc.ghast.packet.wrapper.packet.status.PacketStatusClientStart;
import cc.ghast.packet.wrapper.packet.status.PacketStatusServerInfoServer;
import cc.ghast.packet.wrapper.packet.status.PacketStatusServerPing;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.SneakyThrows;

import java.lang.reflect.Constructor;
import java.util.Map;
import java.util.UUID;

/**
 * @author Ghast
 * @since 29/12/2020
 * ArtemisPacket © 2020
 */

@AllArgsConstructor
@Getter
public enum EnumProtocol_393 implements EnumProtocol {
    HANDSHAKE(
            new PacketPair(
                new PacketInfo[]{
                        new PacketInfo<>(0x00, GPacketHandshakeClientSetProtocol.class, "PacketHandshakingInSetProtocol"),
                        // Todo double check NMS name
                        new PacketInfo<>(0xFE, GPacketHandshakeLegacyServerListPing.class, "PacketHandshakingLegacyServerListPing")
                },
                new PacketInfo[]{}
            )
    ),

    PLAY(
            new PacketPair(
                new PacketInfo[] {
                        new PacketInfo<>(0x00, GPacketPlayClientConfirmTeleport.class, "PacketPlayInTeleportAccept"),
                        // Todo figure out NMS name for this
                        new PacketInfo<>(0x01, GPacketPlayClientBlockMetadataQuery.class, "PacketPlayInQueryBlockNBT"),
                        new PacketInfo<>(0x02, GPacketPlayClientChat.class, "PacketPlayInChat"),
                        new PacketInfo<>(0x03, GPacketPlayClientClientCommand.class,"PacketPlayInClientCommand"),
                        new PacketInfo<>(0x04, GPacketPlayClientSettings.class,"PacketPlayInSettings"),
                        new PacketInfo<>(0x05, GPacketPlayClientTabComplete.class, "PacketPlayInTabComplete"),
                        new PacketInfo<>(0x06, GPacketPlayClientTransaction.class,"PacketPlayInTransaction"),
                        new PacketInfo<>(0x07, GPacketPlayClientEnchantItem.class,"PacketPlayInEnchantItem"),
                        new PacketInfo<>(0x08, GPacketPlayClientWindowClick.class,"PacketPlayInWindowClick"),
                        new PacketInfo<>(0x09, GPacketPlayClientWindowClose.class,"PacketPlayInCloseWindow"),
                        // AKA Plugin Message
                        new PacketInfo<>(0x0A, GPacketPlayClientCustomPayload.class,"PacketPlayInCustomPayload"),
                        // Todo find NMS name
                        new PacketInfo<>(0x0B, GPacketPlayClientBookEdit.class, "PacketPlayInEditBook"),
                        // Todo find NMS name
                        new PacketInfo<>(0x0C, PacketPlayClientEntityMetadataQuery.class, "PacketPlayInQueryEntityNBT"),
                        new PacketInfo<>(0x0D, GPacketPlayClientUseEntity.class, "PacketPlayInUseEntity"),
                        new PacketInfo<>(0x0E, GPacketPlayClientKeepAlive.class, "PacketPlayInKeepAlive"),
                        new PacketInfo<>(0x0F, GPacketPlayClientFlying.class, "PacketPlayInFlying"),
                        new PacketInfo<>(0x10, GPacketPlayClientFlying.PacketPlayClientPosition.class,"PacketPlayInPosition"),
                        new PacketInfo<>(0x11, GPacketPlayClientFlying.PacketPlayClientPositionLook.class,"PacketPlayInPositionLook"),
                        new PacketInfo<>(0x12, GPacketPlayClientFlying.PacketPlayClientLook.class,"PacketPlayInLook"),
                        new PacketInfo<>(0x13, GPacketPlayClientVehicleMove.class,"PacketPlayInVehicleMove"),
                        new PacketInfo<>(0x14, GPacketPlayClientBoatMove.class, "PacketPlayInSteerBoat"),
                        // Todo find NMS name
                        new PacketInfo<>(0x15, PacketPlayClientItemPick.class, "PacketPlayInPickItem"),
                        // Todo find NMS name
                        new PacketInfo<>(0x16, GPacketPlayClientCraftingPrepareGrid.class, "PacketPlayInCraftingPrepareGrid"),
                        new PacketInfo<>(0x17, GPacketPlayClientAbilities.class,"PacketPlayInAbilities"),
                        new PacketInfo<>(0x18, GPacketPlayClientBlockDig.class,"PacketPlayInBlockDig"),
                        new PacketInfo<>(0x19, GPacketPlayClientEntityAction.class,"PacketPlayInEntityAction"),
                        new PacketInfo<>(0x1A, GPacketPlayClientSteerVehicle.class,"PacketPlayInSteerVehicle"),
                        new PacketInfo<>(0x1B, GPacketPlayClientCraftingBookData.class, "PacketPlayInCraftBookData"),
                        // Todo find NMS name
                        new PacketInfo<>(0x1C, PacketPlayClientItemName.class, "PacketPlayInNameItem"),
                        new PacketInfo<>(0x1D, GPacketPlayClientResourcePackStatus.class, "PacketPlayInResourcePackStatus"),
                        new PacketInfo<>(0x1E, GPacketPlayClientAdvancementTab.class, "PacketPlayInAdvancementTab"),
                        // Todo find NMS name
                        new PacketInfo<>(0x1F, PacketPlayClientTradeSelect.class, "PacketPlayInSelectTrade"),
                        new PacketInfo<>(0x20, PacketPlayClientEffectBeaconSet.class, "PacketPlayInSetBeaconEffect"),
                        new PacketInfo<>(0x21, GPacketPlayClientHeldItemSlot.class,"PacketPlayInHeldItemSlot"),
                        // Todo find NMS name
                        new PacketInfo<>(0x22, PacketPlayClientUpdateCommandBlock.class,"PacketPlayInUpdateCommandBlock"),
                        // Todo find NMS name
                        new PacketInfo<>(0x23, PacketPlayClientUpdateCommandMinecart.class, "PacketPlayInUpdateCommandBlockMinecart"),
                        new PacketInfo<>(0x24, GPacketPlayClientSetCreativeSlot.class,"PacketPlayInSetCreativeSlot"),
                        // Todo find NMS name
                        new PacketInfo<>(0x25, PacketPlayClientUpdateStructureBlock.class, "PacketPlayInUpdateStructureBlock"),
                        new PacketInfo<>(0x26, GPacketPlayClientUpdateSign.class,"PacketPlayInUpdateSign"),
                        new PacketInfo<>(0x27, GPacketPlayClientArmAnimation.class,"PacketPlayInArmAnimation"),
                        new PacketInfo<>(0x28, GPacketPlayClientSpectate.class, "PacketPlayInSpectate"),
                        new PacketInfo<>(0x29, GPacketPlayClientBlockPlace.class,"PacketPlayInBlockPlace"),
                        new PacketInfo<>(0x2A, GPacketPlayClientItemUse.class, "PacketPlayInUseItem")

                },
                new PacketInfo[] {
                        new PacketInfo<>(0x00, GPacketPlayServerSpawnObject.class, "PacketPlayOutSpawnEntity"),
                        new PacketInfo<>(0x01, PacketPlayServerSpawnEntityExperienceOrb.class, "PacketPlayOutSpawnEntityExperienceOrb"),
                        /*
                         * Corresponds to wiki.vg's 'global entity'
                         */
                        new PacketInfo<>(0x02, GPacketPlayServerSpawnEntityWeather.class, "PacketPlayOutSpawnEntity"),
                        new PacketInfo<>(0x03, PacketPlayServerSpawnEntityLiving.class, "PacketPlayOutSpawnEntityLiving"),
                        new PacketInfo<>(0x04, PacketPlayServerSpawnEntityPainting.class, "PacketPlayOutSpawnEntityPainting"),
                        new PacketInfo<>(0x05, GPacketPlayServerSpawnNamedEntity.class, "PacketPlayOutNamedEntitySpawn"),
                        new PacketInfo<>(0x06, GPacketPlayServerAnimation.class, "PacketPlayOutAnimation"),
                        new PacketInfo<>(0x07, PacketPlayServerStatistic.class, "PacketPlayOutStatistic"),
                        new PacketInfo<>(0x08, GPacketPlayServerBlockBreakAnimation.class, "PacketPlayOutBlockBreakAnimation"),
                        new PacketInfo<>(0x09, PacketPlayServerTileEntityData.class, "PacketPlayOutTileEntityData"),
                        new PacketInfo<>(0x0A, GPacketPlayServerBlockAction.class, "PacketPlayOutBlockAction"),
                        new PacketInfo<>(0x0B, GPacketPlayServerBlockChange.class, "PacketPlayOutBlockChange"),
                        new PacketInfo<>(0x0C, PacketPlayServerBossBar.class, "PacketPlayOutBoss"),
                        new PacketInfo<>(0x0D, PacketPlayServerServerDifficulty.class, "PacketPlayOutServerDifficulty"),
                        new PacketInfo<>(0x0E, GPacketPlayServerChat.class, "PacketPlayOutChat"),
                        new PacketInfo<>(0x0F, GPacketPlayServerBlockChangeMulti.class, "PacketPlayOutMultiBlockChange"),
                        new PacketInfo<>(0x10, GPacketPlayServerTabComplete.class, "PacketPlayOutTabComplete"),
                        // Todo figure out NMS name for this
                        new PacketInfo<>(0x11, PacketPlayServerCommandsDeclare.class, "PacketPlayOutDeclareCommands"),
                        new PacketInfo<>(0x12, GPacketPlayServerTransaction.class, "PacketPlayOutTransaction"),
                        new PacketInfo<>(0x13, PacketPlayClientWindowClose.class, "PacketPlayInCloseWindow"),
                        new PacketInfo<>(0x14, GPacketPlayServerWindowOpen.class, "PacketPlayOutOpenWindow"),
                        new PacketInfo<>(0x15, PacketPlayServerWindowItems.class, "PacketPlayOutWindowItems"),
                        new PacketInfo<>(0x16, PacketPlayServerWindowData.class, "PacketPlayOutWindowData"),
                        new PacketInfo<>(0x17, PacketPlayServerSetSlot.class, "PacketPlayOutSetSlot"),
                        new PacketInfo<>(0x18, PacketPlayServerSetCooldown.class, "PacketPlayOutSetCooldown"),
                        new PacketInfo<>(0x19, GPacketPlayServerCustomPayload.class, "PacketPlayOutCustomPayload"),
                        new PacketInfo<>(0x1A, PacketPlayServerSoundEffectNamed.class, "PacketPlayOutNameSoundEffect"),
                        new PacketInfo<>(0x1B, GPacketPlayServerKickDisconnect.class, "PacketPlayOutKickDisconnect"),
                        new PacketInfo<>(0x1C, GPacketPlayServerEntityStatus.class, "PacketPlayOutEntityStatus"),
                        // Todo figure out NMS name for this, AKA NBT Query Response
                        new PacketInfo<>(0x1D, PacketPlayServerMetadataResponse.class, "PacketPlayOutMetadataResponse"),
                        new PacketInfo<>(0x1E, GPacketPlayServerExplosion.class, "PacketPlayOutExplosion"),
                        new PacketInfo<>(0x1F, GPacketPlayServerChunkUnload.class, "PacketPlayOutUnloadChunk"),
                        new PacketInfo<>(0x20, PacketPlayServerGameStateChange.class, "PacketPlayOutGameStateChange"),
                        new PacketInfo<>(0x21, GPacketPlayServerKeepAlive.class, "PacketPlayOutKeepAlive"),
                        // AKA Chunk Data
                        new PacketInfo<>(0x22, GPacketPlayServerChunkLoad.class, "PacketPlayOutMap"),
                        // AKA Effect
                        new PacketInfo<>(0x23, PacketPlayServerWorldEvent.class, "PacketPlayOutWorldEvent"),
                        // AKA Particles
                        new PacketInfo<>(0x24, PacketPlayServerWorldParticles.class, "PacketPlayOutWorldParticles"),
                        // AKA Join Game
                        new PacketInfo<>(0x25, GPacketPlayServerLogin.class, "PacketPlayOutLogin"),
                        // AKA Map
                        new PacketInfo<>(0x26, PacketPlayServerMap.class, "PacketPlayOutMap"),
                        new PacketInfo<>(0x27, GPacketPlayServerEntity.class, "PacketPlayOutEntity"),
                        new PacketInfo<>(0x28, GPacketPlayServerEntity.GPacketPlayServerRelEntityMove.class, "PacketPlayOutRelEntityMove"),
                        new PacketInfo<>(0x29, GPacketPlayServerEntity.GPacketPlayServerRelEntityMoveLook.class, "PacketPlayOutRelEntityMoveLook"),
                        new PacketInfo<>(0x2A, GPacketPlayServerEntity.GPacketPlayServerEntityLook.class, "PacketPlayOutEntityLook"),
                        new PacketInfo<>(0x2B, PacketPlayClientVehicleMove.class, "PacketPlayInVehicleMove"),
                        new PacketInfo<>(0x2C, PacketPlayServerOpenSignEditor.class, "PacketPlayOutOpenSignEditor"),
                        // Todo figure out the NMS name for this
                        new PacketInfo<>(0x2D, PacketPlayServerCraftingRecipeResponse.class, "PacketPlayOutCraftingResponse"),
                        new PacketInfo<>(0x2E, GPacketPlayServerAbilities.class, "PacketPlayOutAbilities"),
                        new PacketInfo<>(0x2F, PacketPlayServerCombatEvent.class, "PacketPlayOutCombatEvent"),
                        // AKA Packet List Item
                        new PacketInfo<>(0x30, PacketPlayServerPlayerInfo.class, "PacketPlayOutPlayerInfo"),
                        // Todo figure out NMS name for this
                        new PacketInfo<>(0x31, PacketPlayServerPlayerFace.class, "PacketPlayOutFacePlayer"),
                        new PacketInfo<>(0x32, GPacketPlayServerPosition.class, "PacketPlayOutPosition"),
                        new PacketInfo<>(0x33, GPacketPlayServerBed.class, "PacketPlayOutBed"),
                        new PacketInfo<>(0x34, PacketPlayServerCraftingRecipeUnlock.class, "PacketPlayOutUnlockRecipe"),
                        new PacketInfo<>(0x35, GPacketPlayServerEntityDestroy.class, "PacketPlayOutEntityDestroy"),
                        new PacketInfo<>(0x36, GPacketPlayServerEntityEffectRemove.class, "PacketPlayOutRemoveEntityEffect"),
                        new PacketInfo<>(0x37, GPacketPlayServerResourcePackSend.class, "PacketPlayOutResourcePackSend"),
                        new PacketInfo<>(0x38, GPacketPlayServerRespawn.class, "PacketPlayOutRespawn"),
                        new PacketInfo<>(0x39, GPacketPlayServerEntityHeadRotation.class, "PacketPlayOutEntityHeadRotation"),
                        // Todo figure out the NMS name for this
                        new PacketInfo<>(0x3A, PacketPlayServerAdvancementProgress.class, "PacketPlayOutAdvancementProgress"),
                        new PacketInfo<>(0x3B, PacketPlayServerWorldBorder.class, "PacketPlayOutWorldBorder"),
                        new PacketInfo<>(0x3C, PacketPlayServerCamera.class, "PacketPlayOutCamera"),
                        new PacketInfo<>(0x3D, GPacketPlayServerHeldItemSlot.class, "PacketPlayOutHeldItemSlot"),
                        new PacketInfo<>(0x3E, PacketPlayServerScoreboardDisplayObjective.class, "PacketPlayOutScoreboardDisplayObjective"),
                        new PacketInfo<>(0x3F, GPacketPlayServerEntityMetadata.class, "PacketPlayOutEntityMetadata"),
                        new PacketInfo<>(0x40, GPacketPlayServerEntityAttach.class, "PacketPlayOutAttachEntity"),
                        new PacketInfo<>(0x41, GPacketPlayServerEntityVelocity.class, "PacketPlayOutEntityVelocity"),
                        new PacketInfo<>(0x42, GPacketPlayServerEntityEquipment.class, "PacketPlayOutEntityEquipment"),
                        new PacketInfo<>(0x43, PacketPlayServerExperience.class, "PacketPlayOutExperience"),
                        new PacketInfo<>(0x44, PacketPlayServerUpdateHealth.class, "PacketPlayOutUpdateHealth"),
                        new PacketInfo<>(0x45, PacketPlayServerScoreboardObjective.class, "PacketPlayOutScoreboardObjective"),
                        new PacketInfo<>(0x46, PacketPlayServerSetPassengers.class, "PacketPlayOutMount"),
                        new PacketInfo<>(0x47, PacketPlayServerScoreboardTeam.class, "PacketPlayOutScoreboardTeam"),
                        new PacketInfo<>(0x48, PacketPlayServerScoreboardScore.class, "PacketPlayOutScoreboardScore"),
                        new PacketInfo<>(0x49, PacketPlayServerSpawnPosition.class, "PacketPlayOutSpawnPosition"),
                        new PacketInfo<>(0x4A, PacketPlayServerUpdateTime.class, "PacketPlayOutUpdateTime"),
                        new PacketInfo<>(0x4B, PacketPlayServerTitle.class, "PacketPlayOutTitle"),
                        // Todo find NMS name
                        new PacketInfo<>(0x4C, PacketPlayServerSoundEffectStop.class, "PacketPlayOutStopSound"),
                        new PacketInfo<>(0x4D, PacketPlayServerSoundEffectCustom.class, "PacketPlayOutCustomSoundEffect"),
                        new PacketInfo<>(0x4E, PacketPlayServerPlayerListHeaderFooter.class, "PacketPlayOutPlayListHeaderFooter"),
                        new PacketInfo<>(0x4F, PacketPlayServerCollect.class, "PacketPlayOutCollect"),
                        new PacketInfo<>(0x50, GPacketPlayServerEntityTeleport.class, "PacketPlayOutEntityTeleport"),
                        // Todo find NMS name
                        new PacketInfo<>(0x51, PacketPlayServerAdvancements.class, "PacketPlayOutAdvancements"),
                        new PacketInfo<>(0x52, GPacketPlayServerUpdateAttributes.class,  "PacketPlayOutUpdateAttributes"),
                        new PacketInfo<>(0x53, GPacketPlayServerEntityEffect.class,"PacketPlayOutEntityEffect"),
                        // Todo find NMS name
                        new PacketInfo<>(0x54, PacketPlayServerCraftingRecipeDeclare.class, "PacketPlayOutDeclareRecipes"),
                        new PacketInfo<>(0x55, PacketPlayServerMetadata.class, "PacketPlayOutMetadata")
                }
            )
    ),

    STATUS(new PacketPair(
                new PacketInfo[] {
                        new PacketInfo<>(0x00, PacketStatusClientStart.class, "PacketStatusInStart"),
                        new PacketInfo<>(0x01, PacketStatusClientPing.class, "PacketStatusInPing")
                },

                new PacketInfo[] {
                        new PacketInfo<>(0x00, PacketStatusServerInfoServer.class, "PacketStatusOutServerInfo"),
                        new PacketInfo<>(0x01, PacketStatusServerPing.class, "PacketStatusOutPong")
                }
            )
    ),
    LOGIN(
            new PacketPair(
                new PacketInfo[] {
                        new PacketInfo<>(0x00, GPacketLoginClientStart.class, "PacketLoginInStart"),
                        new PacketInfo<>(0x01, GPacketLoginClientEncryptionBegin.class, "PacketLoginInEncryptionBegin"),
                        // Todo find NMS name
                        new PacketInfo<>(0x02, GPacketLoginClientPluginRequest.class, "PacketLoginInPluginRequest")
                    },

                new PacketInfo[] {
                        new PacketInfo<>(0x00, GPacketLoginServerDisconnect.class, "PacketLoginOutDisconnect"),
                        new PacketInfo<>(0x01, GPacketLoginServerEncryptionBegin.class, "PacketLoginOutEncryptionBegin"),
                        new PacketInfo<>(0x02, GPacketLoginServerSuccess.class, "PacketLoginOutSuccess"),
                        new PacketInfo<>(0x03, GPacketLoginServerSetCompression.class, "PacketLoginOutSetCompression"),
                        // Todo find NMS name
                        new PacketInfo<>(0x04, GPacketLoginServerPluginResponse.class, "PacketLoginPluginResponse")
                }
            )
    );


    private final PacketPair packetPair;


    @Override
    @SneakyThrows
    public GPacket getPacket(ProtocolDirection direction, int id, UUID playerId, ProtocolVersion version) {
        final Map<Integer, PacketInfo> packets = direction.equals(ProtocolDirection.IN) ? packetPair.getClient() : packetPair.getServer();

        final PacketInfo packetInfo = packets.get(id);

        if (packetInfo == null) {
            throw new IllegalStateException(playerId + " -> Packet of id " + id + " had no associated information (dir: "
                    + direction + " ver: " + version + " type: " + ordinal() + ")");
        }

        final Constructor<? extends GPacket> packetConstructor = packetInfo.getConstructor();

        if (packetConstructor == null) {
            throw new IllegalStateException(playerId + " -> Packet of id " + id
                    + " had no associated constructor (dir: " + direction + " ver: " + version + " type: " + ordinal() + ")");
        }

        return packetConstructor.newInstance(playerId, version);
    }

    @Override
    public int getPacketId(ProtocolDirection direction, GPacket packet) {
        final Map<Integer, PacketInfo> packets = direction.equals(ProtocolDirection.IN) ? packetPair.getClient() : packetPair.getServer();
        return packets.values()
                .stream()
                .filter(packetInfo -> packetInfo.getClazz().equals(packet.getClass()))
                .map(PacketInfo::getId)
                .findFirst()
                .orElse(-1);
    }

    @Override
    public Class<? extends GPacket> getPacketClass(ProtocolDirection direction, String name) {
        final Map<Integer, PacketInfo> packets = direction.equals(ProtocolDirection.IN) ? packetPair.getClient() : packetPair.getServer();
        return packets.values()
                .stream()
                .filter(packetInfo -> packetInfo.getNmsName().equals(name))
                .map(PacketInfo::getClazz)
                .findFirst()
                .orElse(null);
    }

    @Override
    public int getOrdinal() {
        return ordinal();
    }

    @Override
    public EnumProtocol[] getValues() {
        return values();
    }
}
