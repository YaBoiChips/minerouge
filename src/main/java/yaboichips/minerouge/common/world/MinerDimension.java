package yaboichips.minerouge.common.world;

import net.minecraft.Util;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.dimension.BuiltinDimensionTypes;
import net.minecraft.world.level.dimension.DimensionType;

import java.util.OptionalLong;

import static yaboichips.minerouge.MineRouge.MODID;

public class MinerDimension {
    public static final ResourceKey<Level> MINER_DIMENSION = ResourceKey.create(Registries.DIMENSION, ResourceLocation.fromNamespaceAndPath(MODID, "miner_dimension"));

//    public static DimensionType TEST = new DimensionType(OptionalLong.empty(), false, true, false, true, 1, false, false, -64, 256, 64, BlockTags.INFINIBURN_NETHER, BuiltinDimensionTypes.OVERWORLD_EFFECTS, 1, new DimensionType.MonsterSettings(false, true, UniformInt.of(0, 7), 0));
//
//    public ServerLevel createDimension(MinecraftServer server){
//        ServerLevel level = new ServerLevel(server, Util.backgroundExecutor(), );
//        return level;
//    }
}
