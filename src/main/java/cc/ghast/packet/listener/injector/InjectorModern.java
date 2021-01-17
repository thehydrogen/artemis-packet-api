package cc.ghast.packet.listener.injector;

import cc.ghast.packet.PacketManager;
import cc.ghast.packet.listener.initializator.BukkitServerBootstrapper;
import cc.ghast.packet.profile.Profile;
import cc.ghast.packet.reflections.ReflectUtil;
import cc.ghast.packet.wrapper.packet.Packet;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import io.netty.channel.*;
import io.netty.channel.socket.SocketChannel;
import io.netty.util.AttributeKey;
import lombok.SneakyThrows;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginDescriptionFile;

import java.lang.reflect.Field;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * Massive credits to Myles. InjectReader is pretty much a paste of his solution with a lot changed around to
 * look nicer and more adequate; MIT license software ftw I guess.
 * @author Ghast, Myles
 * @since 18/08/2020
 * Artemis © 2020
 */
public class InjectorModern implements Injector {

    public InjectorModern() {
        System.out.println("[Artemis] Using Modern Encoder");
    }

    public static final AttributeKey<UUID> KEY_IDENTIFIER = AttributeKey.valueOf("artemis_id");

    private final ChannelFuture future = (ChannelFuture) ReflectUtil.getChannelFuture();
    private final Map<UUID, Profile> profiles = new WeakHashMap<>();
    private final Cache<Profile, Long> futureProfiles = CacheBuilder
            .newBuilder()
            .expireAfterWrite(30, TimeUnit.SECONDS)
            .build();

    private ChannelFuture serverBoot;

    @Override
    @SneakyThrows
    public void injectReader() {
        ChannelHandler serverBootstrap = future.channel().pipeline().first();
        ChannelInitializer<Channel> serverBootstrapInit = null;

        /*
         * Here we iterate through every single pipeline and attempt to find the one which corresponds to the
         * server bootstrap. Such one will contain a childHandler.
         */
        for (Map.Entry<String, ChannelHandler> stringChannelHandlerEntry : future.channel().pipeline()) {
            final ChannelHandler handler = stringChannelHandlerEntry.getValue();

            if (handler == null)
                continue;

            /*
             * This is quite unconventional but pretty much we attempt to get the field. If it does not exist or
             * produces an error, we can pretty much be confident it is not the server bootstrap.
             */
            try {
                Field field = handler.getClass().getDeclaredField("childHandler");
                field.setAccessible(true);
                serverBootstrapInit = (ChannelInitializer) field.get(handler);
                serverBootstrap = handler;
            } catch (Exception e){
                // Ignored
            }
        }

        /*
         * If there is no bootstrapper. Well... that's not good.
         */
        if (serverBootstrapInit == null) {
            throw new IllegalStateException("Not adequate bootstrapper found!");
        }

        try {
            ChannelInitializer<?> newBootstrapper = new BukkitServerBootstrapper(serverBootstrapInit);
            Field field = serverBootstrap.getClass().getDeclaredField("childHandler");
            field.setAccessible(true);
            field.set(serverBootstrap, newBootstrapper);
            this.serverBoot = future;
        } catch (NoSuchFieldException e) {
            // let's find who to blame!
            final ClassLoader cl = serverBootstrap.getClass().getClassLoader();
            final boolean isPlugin = cl.getClass().getName().equals("org.bukkit.plugin.java.PluginClassLoader");
            if (isPlugin) {
                PluginDescriptionFile yaml = (PluginDescriptionFile) cl.getClass().getField("description").get(cl);
                throw new IllegalStateException("Unable to inject, due to " + serverBootstrap.getClass().getName() + ", try without the plugin " + yaml.getName() + "?");
            }

            else {
                throw new IllegalStateException("Unable to find core component 'childHandler', please check your plugins. issue: " + serverBootstrap.getClass().getName());
            }
        }
    }

    @Override
    public void uninjectReader() {
        PacketManager.INSTANCE.fatal("We failed to inject ViaVersion, have you got late-bind enabled with something else?");
        //e.printStackTrace();
    }

    @Override
    public void injectPlayer(UUID uuid) {
        // ProtocolLib channel
        final Channel channel = (Channel) ReflectUtil.getChannel(uuid, Bukkit.getPlayer(uuid).getAddress().getAddress().getHostAddress());
        futureProfiles.asMap().entrySet().stream()
                .filter(e -> channel != null
                        && channel.attr(KEY_IDENTIFIER).get().equals(e.getKey().getId()))
                .findFirst().ifPresent(e -> {
            final Profile profile = e.getKey();
            profile.setUuid(uuid);
            this.profiles.put(uuid, profile);
            PacketManager.INSTANCE.info("Successfully injected into user of UUID " + uuid);
        });

    }

    @Override
    public void uninjectPlayer(UUID uuid) {
        if (this.profiles.containsKey(uuid)) {
            final Profile profile = profiles.get(uuid);
            final Channel channel = (Channel) profile.getChannel();

            if (channel.pipeline() != null) {
                if (channel.pipeline().get(clientBound) != null) {
                    channel.pipeline().remove(clientBound);
                }

                if (channel.pipeline().get(serverBound) != null) {
                    channel.pipeline().remove(serverBound);
                }

                if (channel.pipeline().get(encoder) != null) {
                    channel.pipeline().remove(encoder);
                }
            }

            this.profiles.remove(uuid);
        }

    }

    @Override
    public void injectFuturePlayer(Profile profile) {
        this.futureProfiles.put(profile, System.currentTimeMillis());
    }

    @Override
    public void uninjectFuturePlayer(Object channel) {
        new HashSet<>(this.futureProfiles.asMap().entrySet()).stream().filter(e -> e.getKey().getChannel().equals(channel)).findFirst().ifPresent(e -> {
            futureProfiles.asMap().remove(e.getKey());
        });
    }

    @Override
    public Profile getProfile(UUID uuid) {
        return this.profiles.get(uuid);
    }

    @Override
    public void writePacket(UUID target, Packet<?> packet) {
        final Profile profile = this.getProfile(target);

        if (profile == null) {
            throw new IllegalStateException("Attempt to send packet to an unregistered profile (uuid: "
                    + target + " packet: " + packet.getRealName());
        }

        final Channel channel = (Channel) profile.getChannel();

        if (channel == null) {
            throw new IllegalStateException("Attempt to send packet to a profile without a channel (uuid: "
                    + target + " packet: " + packet.getRealName());
        }

        final ChannelFuture channelfuture = channel.writeAndFlush(packet);
        channelfuture.addListener(ChannelFutureListener.FIRE_EXCEPTION_ON_FAILURE);
    }
}
