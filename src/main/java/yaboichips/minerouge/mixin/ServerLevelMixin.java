package yaboichips.minerouge.mixin;

import net.minecraft.resources.ResourceKey;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.progress.ChunkProgressListener;
import net.minecraft.world.RandomSequences;
import net.minecraft.world.level.CustomSpawner;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.dimension.LevelStem;
import net.minecraft.world.level.storage.LevelStorageSource;
import net.minecraft.world.level.storage.ServerLevelData;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import yaboichips.minerouge.client.IServerLevelMixin;

import java.util.List;
import java.util.concurrent.Executor;

@Mixin(ServerLevel.class)
public class ServerLevelMixin implements IServerLevelMixin {

    @Unique
    public LevelStem mineRouge$levelStem;
    @Unique
    public ChunkProgressListener mineRouge$chunkProgressListener;
    @Unique
    public List<CustomSpawner> mineRouge$customSpawner;
    @Inject(method = "<init>", at = @At(value = "TAIL"))
    private void getMethods(MinecraftServer p_214999_, Executor p_215000_, LevelStorageSource.LevelStorageAccess p_215001_, ServerLevelData p_215002_, ResourceKey<Level> p_215003_, LevelStem p_215004_, ChunkProgressListener p_215005_, boolean p_215006_, long p_215007_, List<CustomSpawner> p_215008_, boolean p_215009_, RandomSequences p_288977_, CallbackInfo ci){
        this.mineRouge$levelStem = p_215004_;
        this.mineRouge$chunkProgressListener = p_215005_;
        this.mineRouge$customSpawner = p_215008_;
    }

    @Override
    public LevelStem getLevelStem() {
        return mineRouge$levelStem;
    }

    @Override
    public ChunkProgressListener getListener() {
        return mineRouge$chunkProgressListener;
    }

    @Override
    public List<CustomSpawner> getSpawner() {
        return mineRouge$customSpawner;
    }
}
