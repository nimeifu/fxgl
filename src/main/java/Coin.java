import com.almasb.fxgl.entity.*;
import javafx.scene.shape.Rectangle;

public class Coin implements EntityFactory {
    @Spawns("coin")
    public Entity newCoin(SpawnData data)
    {
        return Entities.builder()
                .from(data)
                .viewFromTexture("")
                .build();
    }
}
