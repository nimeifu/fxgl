import com.almasb.fxgl.entity.*;

public class Material implements EntityFactory {
    @Spawns("material")
    public Entity newMaterial(SpawnData data)
    {
        return Entities.builder()
            .from(data)
            .viewFromTexture("")
            .build();
    }
}
