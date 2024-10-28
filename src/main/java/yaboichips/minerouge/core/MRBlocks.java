package yaboichips.minerouge.core;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import yaboichips.minerouge.common.blocks.TeleporterBlock;

import static yaboichips.minerouge.MineRouge.MODID;

public class MRBlocks {

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MODID);

    public static final RegistryObject<Block> TELEPORTER_BLOCK = BLOCKS.register("teleporter_block", () -> new TeleporterBlock(BlockBehaviour.Properties.of().mapColor(MapColor.STONE)));

}
