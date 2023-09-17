package com.quackings.liftgame;

import com.almasb.fxgl.dsl.FXGL;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class LevelManager {

    public static LevelManager levelManagerInstance = null;

    public LevelManager() {
        if (levelManagerInstance == null) {
            levelManagerInstance = this;
        }
    }

    public void loadLevel() {
        FXGL.entityBuilder()
            .at(100, 500)
            .view(new Rectangle(1000, 20, Color.RED))
            .collidable()
            .buildAndAttach();
    }

    public static LevelManager getInstance() {
        return levelManagerInstance;
    }

}
