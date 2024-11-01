package yaboichips.minerouge.common.world;

import net.minecraft.Util;
import net.minecraft.client.Minecraft;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.loading.FMLLoader;
import net.minecraftforge.network.Channel;
import net.minecraftforge.network.ChannelBuilder;
import net.minecraftforge.network.payload.PayloadConnection;
import net.minecraftforge.network.payload.PayloadFlow;
import net.minecraftforge.network.payload.PayloadProtocol;
import yaboichips.minerouge.common.world.tools.MinerPacket;
import yaboichips.minerouge.common.world.tools.PacketSyncDimensionListChanges;

import java.util.List;

import static yaboichips.minerouge.MineRouge.MODID;

public class MinerMessages {
//    private static final String PROTOCOL_VERSION = "1";
//    public static final Channel<CustomPacketPayload> SIMPLE_CHANNEL = Util.make(() -> {
//        PayloadConnection<CustomPacketPayload> connection = ChannelBuilder.named(ResourceLocation.fromNamespaceAndPath(MODID, "network")).payloadChannel();
//
//        PayloadProtocol<RegistryFriendlyByteBuf, CustomPacketPayload> play = connection.play();
//
////        for (PacketSyncDimensionListChanges.Handler<?> packet : PacketSyncDimensionListChanges.PACKETS) {
//
//            if (packet.direction() == PacketSyncDimensionListChanges.PacketDirection.SERVER_TO_CLIENT) {
//                play = play.clientbound();
//                registerS2C(packet, (PayloadFlow) play);
//            }
//
//            if (packet.direction() == PacketSyncDimensionListChanges.PacketDirection.CLIENT_TO_SERVER) {
//                play = play.serverbound();
//                registerC2S(packet, (PayloadFlow) play);
//            }
//
//            if (packet.direction() == PacketSyncDimensionListChanges.PacketDirection.BI_DIRECTIONAL) {
//                play = play.bidirectional();
//                registerBiDirectional(packet, (PayloadFlow) play);
//            }
//        }
//
//        return ((PayloadFlow) play).build();
//    });
//
//    public static void init() {}
//
//
//    private static <T extends PacketSyncDimensionListChanges> void registerS2C(MinerPacket.Handler<?> handler, PayloadFlow<RegistryFriendlyByteBuf, T> flow) {
//        flow.addMain(handler.type(), handler.serializer(), (t, context) -> {
//            context.enqueueWork(() -> handler.handle().handle(t, context.getSender().level(), context.getSender()));
//            context.setPacketHandled(true);
//        });
//    }
//
//    private static <T extends PacketSyncDimensionListChanges> void registerC2S(MinerPacket.Handler<?> handler, PayloadFlow<RegistryFriendlyByteBuf, T> flow) {
//        flow.addMain(handler.type(), handler.serializer(), (t, context) -> {
//            context.enqueueWork(() -> Client.clientHandle(t, handler.handle()));
//            context.setPacketHandled(true);
//        });
//    }
//
//    private static <T extends PacketSyncDimensionListChanges> void registerBiDirectional(MinerPacket.Handler<?> handler, PayloadFlow<RegistryFriendlyByteBuf, T> flow) {
//        flow.addMain(handler.type(), handler.serializer(), (t, context) -> {
//            if (FMLLoader.getDist() == Dist.CLIENT) {
//                if (context.isClientSide()) {
//                    Client.clientHandle(t, handler.handle());
//                }
//            } else {
//                handler.handle().handle(t, context.getSender().level(), context.getSender());
//            }
//            context.setPacketHandled(true);
//        });
//    }
//
//    public static <T extends PacketSyncDimensionListChanges> void sendToPlayer(ServerPlayer playerEntity, T packet) {
//        SIMPLE_CHANNEL.send(packet, playerEntity.connection.getConnection());
//    }
//
//    public static <T extends PacketSyncDimensionListChanges> void sendToAllPlayers(List<ServerPlayer> playerEntities, T packet) {
//        for (ServerPlayer playerEntity : playerEntities) {
//            SIMPLE_CHANNEL.send(packet, playerEntity.connection.getConnection());
//        }
//    }
//
//    public static <T extends PacketSyncDimensionListChanges> void sendToServer(T packet) {
//        SIMPLE_CHANNEL.send(packet, Minecraft.getInstance().getConnection().getConnection());
//    }
//
//    private static class Client {
//        private static <T extends PacketSyncDimensionListChanges> void clientHandle(T packet, PacketSyncDimensionListChanges.Handle<T> handle) {
//            handle.handle(packet, Minecraft.getInstance().level, Minecraft.getInstance().player);
//        }
//    }
}