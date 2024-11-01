package yaboichips.minerouge.common.world.tools;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;

import java.util.Set;
import java.util.logging.Level;

public class LevelKeysCodec {
    private final Set<ResourceKey<Level>> firstSet;
    private final Set<ResourceKey<Level>> secondSet;

    // Constructor
    public LevelKeysCodec(Set<ResourceKey<Level>> firstSet, Set<ResourceKey<Level>> secondSet) {
        this.firstSet = firstSet;
        this.secondSet = secondSet;
    }

    // Getters
    public Set<ResourceKey<Level>> getFirstSet() {
        return firstSet;
    }

    public Set<ResourceKey<Level>> getSecondSet() {
        return secondSet;
    }

    // Codec for encoding and decoding using existing sets
//    public static Codec<LevelKeysCodec> codec(Set<ResourceKey<Level>> firstSet) {
//        Codec<Set<ResourceKey<Level>>> setCodec = ResourceLocation.CODEC
//                .xmap(loc -> ResourceKey.create(Registries.DIMENSION, loc), ResourceKey::location)
//                .listOf()
//                .xmap(list -> {
//                    firstSet.clear();
//                    firstSet.addAll(list);
//                    return firstSet;
//                }, set -> set.stream().toList());
//
//        return RecordCodecBuilder.create(instance -> instance.group(
//                setCodec.fieldOf("firstSet").forGetter(LevelKeysCodec::getFirstSet),
//                setCodec.fieldOf("secondSet").forGetter(LevelKeysCodec::getSecondSet)
//        ).apply(instance, LevelKeysCodec::new));
//    }
}
