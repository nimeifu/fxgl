import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.entity.Entities;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.EntityFactory;
import com.almasb.fxgl.entity.components.CollidableComponent;
import com.almasb.fxgl.extra.entity.components.HealthComponent;
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

    private playerComponet playerComponet;


    @Override
    protected void initSettings(GameSettings gameSettings) {
        gameSettings.setWidth(ScreenWidth);
        gameSettings.setHeight(ScreenHeight);
        gameSettings.setTitle("basic game");
        gameSettings.setConfigClass(Config.class);

    }

    @Override
    protected void initGame() {
       getGameWorld().addEntityFactory(new Factory());
       Entity player =getGameWorld().spawn("Player",ScreenWidth/2,ScreenHeight/2);
       Entity princess=getGameWorld().spawn("princess",850,ScreenHeight/2);
       Entity enemy=getGameWorld().spawn("enemy",100,ScreenHeight/2);
       playerComponet=player.getComponent(playerComponet.class);
       getGameWorld().spawn("BG");


    }

    @Override
   protected void initPhysics() {
       getPhysicsWorld().addCollisionHandler(new CollisionHandler(Type.PLAYER,Type.ENEMY) {
           @Override
           protected void onCollisionBegin(Entity Player, Entity Enemy) {
               Enemy.removeFromWorld();
           }
       });


    }

    @Override
    protected void initInput() {
        getInput().addAction(new UserAction("Move Up") {
            @Override
            protected void onAction() {
               playerComponet.up();
            }
        },KeyCode.W);

        getInput().addAction(new UserAction("Move Down") {
            @Override
            protected void onAction() {
                playerComponet.down();
            }
        },KeyCode.S);

        getInput().addAction(new UserAction("Move left") {
            @Override
            protected void onAction() {
                playerComponet.left();
            }
        },KeyCode.A);

        getInput().addAction(new UserAction("Move Right") {
            @Override
            protected void onAction() {
                playerComponet.right();
            }
        }, KeyCode.D);
    }
    @Override
    protected void initUI() {

    }

    @Override
    protected void initGameVars(Map<String, Object> vars) {
        vars.put("bullets",10);
        vars.put("lives",100);

    }


    public static void main(String[] args) {
        launch(args);
    }
}
