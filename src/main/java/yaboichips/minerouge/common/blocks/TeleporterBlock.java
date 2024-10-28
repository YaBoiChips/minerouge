package yaboichips.minerouge.common.blocks;


import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.BiomeSource;
import net.minecraft.world.level.biome.BiomeSources;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.biome.FixedBiomeSource;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.world.level.dimension.LevelStem;
import net.minecraft.world.level.levelgen.NoiseBasedChunkGenerator;
import net.minecraft.world.level.levelgen.NoiseGeneratorSettings;
import net.minecraft.world.level.portal.DimensionTransition;
import net.minecraft.world.phys.BlockHitResult;
import yaboichips.minerouge.MineRouge;
import yaboichips.minerouge.common.world.DynamicDimensionManager;

import java.util.UUID;

import static yaboichips.minerouge.MineRouge.MODID;

public class TeleporterBlock extends Block {


    public TeleporterBlock(Properties p_49795_) {
        super(p_49795_);
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState p_60503_, Level level, BlockPos p_60505_, Player player, BlockHitResult p_60508_) {
        int dimID = level.random.nextInt();
        ResourceKey<Level> DIM = ResourceKey.create(Registries.DIMENSION, ResourceLocation.fromNamespaceAndPath(MODID, "miner_dimension"));
        if (!level.isClientSide()) {
            ServerLevel level1 = createWorld(level, "test");
            if (level1 != null) {
                player.changeDimension(new DimensionTransition(level1, player, DimensionTransition.DO_NOTHING));
            }
        }
        return super.useWithoutItem(p_60503_, level, p_60505_, player, p_60508_);
    }


    public ServerLevel createWorld(Level world, String name) {
        ResourceLocation id = ResourceLocation.fromNamespaceAndPath(MineRouge.MODID, name);

        ResourceKey<Level> key = ResourceKey.create(Registries.DIMENSION, ResourceLocation.fromNamespaceAndPath(MODID, name));


        RegistryAccess registryAccess = world.getServer().registryAccess();
        Holder<DimensionType> type = registryAccess.registryOrThrow(Registries.DIMENSION_TYPE).getHolderOrThrow(ResourceKey.create(Registries.DIMENSION_TYPE, ResourceLocation.fromNamespaceAndPath(MODID, name)));
        ServerLevel result = DynamicDimensionManager.getOrCreateLevel(world.getServer(), key,
                (server, registryKey) -> {
                    ;
                    return new LevelStem(type, new NoiseBasedChunkGenerator(new FixedBiomeSource(registryAccess.registryOrThrow(Registries.BIOME).getHolderOrThrow(Biomes.STONY_SHORE)), Holder.direct(NoiseGeneratorSettings.dummy())));
                });
        return result;
    }

//    public ServerLevel createRandLevel(MinecraftServer server, ResourceKey<Level> key) {
//
//        ServerLevel level = server.getLevel(key);
//
//        ServerLevel serverlevel1 = new ServerLevel(
//                server,
//                server.executor,
//                server.storageSource,
//                level.serverLevelData,
//                key,
//                ((IServerLevelMixin) level).getLevelStem(),
//                ((IServerLevelMixin) level).getListener(),
//                false,
//                121,
//                ImmutableList.of(),
//                false,
//                null
//        );
//        ResourceKey.create(Registries.DIMENSION, ResourceLocation.fromNamespaceAndPath(MODID, "miner_dimension"));
//        MinecraftForge.EVENT_BUS.post(new LevelEvent.Load(serverlevel1));
//        return serverlevel1;
//    }
}
