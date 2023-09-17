package com.quackings.liftgame;

import com.almasb.fxgl.core.math.FXGLMath;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.texture.AnimatedTexture;
import com.almasb.fxgl.texture.AnimationChannel;

import javafx.geometry.Point2D;

public class PlayerAnimationComponent extends Component {

    private int speed = 1;
    private boolean isFlipped = false;

    private AnimatedTexture texture;
    private AnimationChannel animIdle, animWalk, animRun;

    public PlayerAnimationComponent() {
        animIdle = new AnimationChannel(FXGL.image("playerIdle.png"), 8, 58, 70, javafx.util.Duration.seconds(1), 0, 7);
        animWalk = new AnimationChannel(FXGL.image("playerWalk.png"), 8, 64, 70, javafx.util.Duration.seconds(1), 0, 6);
        animRun = new AnimationChannel(FXGL.image("playerRun.png"), 8, 60, 70, javafx.util.Duration.seconds(1), 0, 7);

        texture = new AnimatedTexture(animIdle);
    }

    @Override
    public void onAdded() {
        entity.getTransformComponent().setScaleOrigin(new Point2D(16, 21));
        entity.getViewComponent().addChild(texture);
    }

    @Override
    public void onUpdate(double tpf) {
        entity.translateX(speed * tpf);

        if (speed != 0) {
            if (texture.getAnimationChannel() == animIdle) {
                texture.loopAnimationChannel(animRun);
            }

            speed = (int) (speed * 0.7); // decelerate constantly (will be overriden while key is held down)

            if (FXGLMath.abs(speed) < 1) {
                speed = 0;
                texture.loopAnimationChannel(animIdle);
            }
        }
    }

    public void moveX(int speed) {
        this.speed = speed*50;

        getEntity().setScaleX(speed > 0 ? 1 : -1);
        
        if (isFlipped != (speed < 0)) {
            entity.translateX(speed > 0 ? -31 : 31); // flip offset
        }

        isFlipped = speed < 0;
    }
    
}
