import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.entity.Entities;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.components.CollidableComponent;
import com.almasb.fxgl.input.Input;
import com.almasb.fxgl.input.InputMapping;
import com.almasb.fxgl.input.OnUserAction;
import com.almasb.fxgl.input.UserAction;
import com.almasb.fxgl.physics.CollisionHandler;
import com.almasb.fxgl.settings.GameSettings;

import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

import java.text.SimpleDateFormat;
import java.util.Map;
public class BasicGameApp extends  GameApplication {


    private Entity player;
    private Entity NPC;

    private String displayMonth = "April";
    private String hitsound;
    private final int ScreenWidth = 1024;
    private final int ScreenHeight = 633;
    public enum EntityType
    {
        player,item
    }
    private playerComponet playerComponet;


    @Override
    protected void initSettings(GameSettings gameSettings) {
        gameSettings.setWidth(ScreenWidth);
        gameSettings.setHeight(ScreenHeight);
        gameSettings.setTitle("basic game");
        gameSettings.setVersion("Version beta");
        gameSettings.setMenuEnabled(false);
        gameSettings.setIntroEnabled(false);
        gameSettings.setProfilingEnabled(false);
    }

    @Override
    protected void initGame() {
        super.initGame();
        player = Entities.builder()
                .type(EntityType.player)
                .at(300, 300)
                .viewFromNode(new Rectangle(25, 25, Color.BLUE))
                .with(new CollidableComponent(true))
                .buildAndAttach(getGameWorld());

        NPC=    Entities.builder()
                .at(200,200)
                .with(new CollidableComponent(true))
                .buildAndAttach(getGameWorld());

        Entities.builder()
                .type(EntityType.item)
                .at(150,150)
                .with(new CollidableComponent(true))
                .buildAndAttach(getGameWorld());

    }

    @Override
    protected void initPhysics() {
        getPhysicsWorld().addCollisionHandler(new CollisionHandler(EntityType.player,EntityType.item) {
            @Override
            protected void onCollisionBegin(Entity a, Entity b) {
                super.onCollisionBegin(a, b);
            }
        });


    }

    @Override
    protected void initInput() {
        Input input = getInput();

        input.addInputMapping(new InputMapping("Move Left", KeyCode.A));
        input.addInputMapping(new InputMapping("Move Right", KeyCode.D));
        input.addInputMapping(new InputMapping("Move up", KeyCode.W));
        input.addInputMapping(new InputMapping("Move down", KeyCode.S));
        input.addInputMapping(new InputMapping("Slash", MouseButton.PRIMARY));
        input.addInputMapping(new InputMapping("shoot", MouseButton.SECONDARY));
    }
        @OnUserAction(name = "Move Left")
        public void moveleft()
        {
            playerComponet.left();
        }

        @OnUserAction(name = "Move Right")
        public void moveRight()
        {
            playerComponet.right();
        }

        @OnUserAction(name = "Move up")
        public void moveUp()
        {
            playerComponet.up();
        }

        @OnUserAction(name = "Move down")
        public void moveDown()
        {
            playerComponet.down();
        }

    @Override
    protected void initUI() {
        Text textPixels = new Text();
        textPixels.textProperty().bind(getGameState().intProperty("pixelsMoved").asString());
        textPixels.setTranslateX(50); // x = 50
        textPixels.setTranslateY(100); // y = 100

        getGameScene().addUINode(textPixels); // add to the scene graph
    }

    @Override
    protected void initGameVars(Map<String, Object> vars) {
        vars.put("pixelsMoved", 0);
    }


    public static void main(String[] args) {
        launch(args);
    }
}
