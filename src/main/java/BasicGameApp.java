import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.entity.Entities;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.input.Input;
import com.almasb.fxgl.input.UserAction;
import com.almasb.fxgl.settings.GameSettings;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import java.util.Map;
public class BasicGameApp extends  GameApplication{


    @Override
    protected void initSettings(GameSettings gameSettings) {
        gameSettings.setWidth(1000);
        gameSettings.setHeight(1000);
        gameSettings.setTitle("basic game");
        gameSettings.setVersion("Version beta");
    }

    private Entity player;

    @Override
    protected void initGame() {
        player = Entities.builder()
                .at(300, 300)
                .viewFromNode(new Rectangle(25, 25, Color.BLUE))
                .buildAndAttach(getGameWorld());
    }

    @Override
    protected void initInput() {
        Input input = getInput();

        input.addAction(new UserAction("Move Right") {
            @Override
            protected void onAction() {
                player.translateX(1); // move right 5 pixels

            }
        }, KeyCode.D);

        input.addAction(new UserAction("Move Left") {
            @Override
            protected void onAction() {
                player.translateX(-1); // move left 5 pixels

            }
        }, KeyCode.A);

        input.addAction(new UserAction("Move Up") {
            @Override
            protected void onAction() {
                player.translateY(-1); // move up 5 pixels

            }
        }, KeyCode.W);

        input.addAction(new UserAction("Move Down") {
            @Override
            protected void onAction() {
                player.translateY(1); // move down 5 pixels

            }
        }, KeyCode.S);


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


    public static void main(String[] args)
    {
        launch(args);
    }
}
