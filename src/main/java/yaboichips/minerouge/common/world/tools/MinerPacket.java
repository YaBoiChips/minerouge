package yaboichips.minerouge.common.world.tools;

import net.minecraft.Util;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

import java.util.ArrayList;
import java.util.List;

public interface MinerPacket extends CustomPacketPayload {
//    List<Handler<?>> PACKETS = Util.make(new ArrayList<>(), list -> {
//        list.add(new Handler<>(PacketSyncDimensionListChanges.TYPE, PacketSyncDimensionListChanges.PacketDirection.SERVER_TO_CLIENT, PacketSyncDimensionListChanges.STREAM_CODEC, PacketSyncDimensionListChanges::handle));
//    });


    void handle(Level level, Player player);


    record Handler<T extends MinerPacket>(Type<T> type, PacketSyncDimensionListChanges.PacketDirection direction,
                                       StreamCodec<RegistryFriendlyByteBuf, T> serializer, Handle<T> handle) {
    }

    enum PacketDirection {
        SERVER_TO_CLIENT,
        CLIENT_TO_SERVER,
        BI_DIRECTIONAL
    }
    

    @FunctionalInterface
    interface Handle<T extends MinerPacket> {
        void handle(T packet, Level level, Player player);
    }
}
