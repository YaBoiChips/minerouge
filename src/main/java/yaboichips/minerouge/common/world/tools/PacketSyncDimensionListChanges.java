package yaboichips.minerouge.common.world.tools;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import yaboichips.minerouge.MineRouge;
import yaboichips.minerouge.common.world.DynamicDimensionManager;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public record PacketSyncDimensionListChanges(Set<ResourceKey<Level>> newDimensions, Set<ResourceKey<Level>> removedDimensions) implements MinerPacket {

    public static final Type<PacketSyncDimensionListChanges> TYPE = new Type<>(ResourceLocation.fromNamespaceAndPath(MineRouge.MODID, "syncdimensionlistchanges"));

//    public static final StreamCodec<RegistryFriendlyByteBuf, PacketSyncDimensionListChanges> STREAM_CODEC = StreamCodec.composite(
////            ByteBufCodecs.fromCodec(),
//            PacketSyncDimensionListChanges::newDimensions,
//            ByteBufCodecs.VAR_INT,
//            PacketSyncDimensionListChanges::removedDimensions,
//            PacketSyncDimensionListChanges::new
//    );

    public PacketSyncDimensionListChanges(final Set<ResourceKey<Level>> newDimensions, final Set<ResourceKey<Level>> removedDimensions) {
        this.newDimensions = newDimensions;
        this.removedDimensions = removedDimensions;
    }

    public Set<ResourceKey<Level>> getNewDimensions() {
        return newDimensions;
    }

    public Set<ResourceKey<Level>> getRemovedDimensions() {
        return removedDimensions;
    }

    public void write(FriendlyByteBuf buf) {
        buf.writeVarInt(this.newDimensions.size());
        for (final ResourceKey<Level> key : this.newDimensions) {
            buf.writeResourceLocation(key.location());
        }

        buf.writeVarInt(this.removedDimensions.size());
        for (final ResourceKey<Level> key : this.removedDimensions) {
            buf.writeResourceLocation(key.location());
        }
    }

    @Override
    public void handle(Level level, Player player1) {
        if (player1 instanceof LocalPlayer player) {
            final Set<ResourceKey<Level>> commandSuggesterLevels = player.connection.levels();
            commandSuggesterLevels.addAll(this.newDimensions);
            for (final ResourceKey<Level> key : this.removedDimensions) {
                commandSuggesterLevels.remove(key);
            }
        }
    }
    

    public static PacketSyncDimensionListChanges create(FriendlyByteBuf buf) {
        Set<ResourceKey<Level>> newDimensions = new HashSet<>();
        Set<ResourceKey<Level>> removedDimensions = new HashSet<>();

        final int newDimensionCount = buf.readVarInt();
        for (int i = 0; i < newDimensionCount; i++) {
            final ResourceLocation worldID = buf.readResourceLocation();
            newDimensions.add(ResourceKey.create(Registries.DIMENSION, worldID));
        }

        final int removedDimensionCount = buf.readVarInt();
        for (int i = 0; i < removedDimensionCount; i++) {
            final ResourceLocation worldID = buf.readResourceLocation();
            removedDimensions.add(ResourceKey.create(Registries.DIMENSION, worldID));
        }
        return new PacketSyncDimensionListChanges(newDimensions, removedDimensions);
    }


    /**
     * Notifies clients that their list of dimension IDs needs to be updated.
     * This clientside list is normally only used for the command suggester.
     *
     * @param newDimensions     keys to add to clients' dimension lists
     * @param removedDimensions keys to remove from clients' dimension lists
     * @apiNote Internal; this is invoked by {@link DynamicDimensionManager}
     * when that's used to add or remove dynamic dimensions,
     * so mods shouldn't need to call this themselves
     */
//    public static void updateClientDimensionLists(MinecraftServer server, Set<ResourceKey<Level>> newDimensions, Set<ResourceKey<Level>> removedDimensions) {
//        ForgeNetworkHandler.sendToAllPlayers(server.getPlayerList().getPlayers(), new PacketSyncDimensionListChanges(newDimensions, removedDimensions));
//    }

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    public record Data(Set<ResourceKey<Level>> newDimensions, Set<ResourceKey<Level>> removedDimension) {
        static Codec<Set<ResourceKey<Level>>> setCodec = ResourceLocation.CODEC
                .xmap(loc -> ResourceKey.create(Registries.DIMENSION, loc), ResourceKey::location)
                .listOf()
                .xmap(HashSet::new, set -> set.stream().collect(Collectors.toList()));
//        public static final Codec<Data> CODEC = RecordCodecBuilder.create(builder ->
//                builder.group(
//                        setCodec.fieldOf("newDimension").forGetter(PacketSyncDimensionListChanges::getNewDimensions),
//                        setCodec.fieldOf("removedDimension").forGetter(PacketSyncDimensionListChanges::getRemovedDimensions)
//                ).apply(builder, Data::new)
//        );
    }
}
