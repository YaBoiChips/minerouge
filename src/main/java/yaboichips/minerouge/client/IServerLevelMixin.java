package yaboichips.minerouge.client;

import net.minecraft.server.level.progress.ChunkProgressListener;
import net.minecraft.world.level.CustomSpawner;
import net.minecraft.world.level.dimension.LevelStem;

import java.util.List;

public interface IServerLevelMixin {

    default LevelStem getLevelStem() {
        return null;
    }
    default ChunkProgressListener getListener(){
        return null;
    }
    default List<CustomSpawner> getSpawner() {
        return null;
    }
}
