package yaboichips.minerouge.common.entity;

import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.animatable.GeoAnimatable;
import software.bernie.geckolib.model.GeoModel;

import static yaboichips.minerouge.MineRouge.MODID;

public class CanonEntityModel<T extends GeoAnimatable> extends GeoModel<T> {
    @Override
    public ResourceLocation getModelResource(T t) {
        return ResourceLocation.fromNamespaceAndPath(MODID, "geo/canon.geo.json") ;
    }

    @Override
    public ResourceLocation getTextureResource(T t) {
        return ResourceLocation.fromNamespaceAndPath(MODID, "textures/entity/canon.png") ;
    }

    @Override
    public ResourceLocation getAnimationResource(T t) {
        return null;
    }
}
