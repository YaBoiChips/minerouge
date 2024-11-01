package yaboichips.minerouge.core;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import yaboichips.minerouge.common.blocks.TeleporterBlock;
import yaboichips.minerouge.common.entity.CanonEntity;

import static yaboichips.minerouge.MineRouge.MODID;

public class MREntities {

    public static final DeferredRegister<EntityType<?>> ENTITY = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, MODID);

    public static final RegistryObject<EntityType<CanonEntity>> CANON = ENTITY.register("canon", () -> EntityType.Builder.of(CanonEntity::new, MobCategory.MISC).sized(1, 0.5f).build("canon"));

}
