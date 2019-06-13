/*
 * The MIT License (MIT)
 *
 * FXGL - JavaFX Game Library
 *
 * Copyright (c) 2015-2017 AlmasB (almaslvl@gmail.com)
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

import com.almasb.fxgl.app.FXGL;
import com.almasb.fxgl.core.math.FXGLMath;


import com.almasb.fxgl.entity.*;
import com.almasb.fxgl.entity.components.CollidableComponent;
import com.almasb.fxgl.extra.entity.components.HealthComponent;
import com.almasb.fxgl.particle.ParticleComponent;
import com.almasb.fxgl.particle.ParticleEmitter;
import com.almasb.fxgl.particle.ParticleEmitters;

import com.almasb.fxgl.texture.Texture;
import javafx.geometry.Point2D;
import javafx.scene.effect.BlendMode;
import javafx.scene.paint.Color;
import javafx.util.Duration;


import static com.almasb.fxgl.app.DSLKt.play;
import static com.almasb.fxgl.app.DSLKt.texture;


/**
 * @author Almas Baimagambetov (almaslvl@gmail.com)
 */
public class Factory implements EntityFactory {

    @Spawns("BG")
    public Entity bakcground(SpawnData data)
    {

        return Entities.builder()
                .at(0,0)
                .from (data)
                .viewFromNode(texture("background.jpg",1024,633))
                .renderLayer(RenderLayer.BACKGROUND)
                .build();
    }
    @Spawns("Player")
    public Entity newPlayer(SpawnData data) {
        Texture texture=texture("knight.jpg",62,180);
        return Entities.builder()
                .type(Type.PLAYER)
                .from(data)
                .viewFromNodeWithBBox(texture)
                .with(new CollidableComponent(true))
                .with(new playerComponet())
                .build();
    }
/*
    @Spawns("EnemyBullet")
    public Entity newEnemyBullet(SpawnData data) {


        return Entities.builder()
                .type(Type.ENEMY_BULLET)
                .from(data)
                .viewFromNodeWithBBox(FXGL.getAssetLoader().loadTexture("sprite_bullet.png", 22, 11))
                .with(new CollidableComponent(true))
                .with(new ProjectileComponent(new Point2D(-1, 0), 350),
                        new OffscreenCleanComponent())
                .build();
    }

    @Spawns("Bullet")
    public Entity newBullet(SpawnData data) {
        play("shoot" + FXGLMath.random(1, 4) + ".wav");

        return Entities.builder()
                .type(Type.BULLET)
                .from(data)
                .viewFromNodeWithBBox(FXGL.getAssetLoader().loadTexture("sprite_bullet.png", 22, 11))
                .with(new CollidableComponent(true))
                .with(new ProjectileComponent(new Point2D(1, 0), 1550),
                        new OffscreenCleanComponent())
                .build();
    }

    @Spawns("arrow")
    public Entity newLaser(SpawnData data) {
        play("shoot" + FXGLMath.random(1, 4) + ".wav");

        return Entities.builder()
                .type(Type.BULLET)
                .from(data)
                .viewFromNodeWithBBox(FXGL.getAssetLoader().loadTexture("sprite_laser.png"))
                .with(new CollidableComponent(true))
                .build();
    }
*/
    @Spawns("princess")
    public Entity princess(SpawnData data){
        return Entities.builder()
                .type(Type.princess)
                .from(data)
                .viewFromNodeWithBBox(FXGL.getAssetLoader().loadTexture("princess.png",62,165))
                .with((new CollidableComponent(true)))

                .build();
    }

    @Spawns("enemy")
    public Entity Enemy(SpawnData data) {
        return Entities.builder()
                .type(Type.ENEMY)
                .from(data)
                .viewFromNodeWithBBox(FXGL.getAssetLoader().loadTexture("enemy.png", 62, 170))
                .with(new CollidableComponent(true), new HealthComponent(5))

                .build();
    }






}
