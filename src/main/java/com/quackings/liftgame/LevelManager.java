package com.quackings.liftgame;

import com.almasb.fxgl.dsl.FXGL;
import com.quackings.liftgame.constants.EntityTypes;

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
            .type(EntityTypes.PLATFORM)
            .at(100, 675)
            .viewWithBBox(new Rectangle(1400, 40, Color.BLACK))
            .collidable()
            .buildAndAttach();
    }

    public static LevelManager getInstance() {
        return levelManagerInstance;
    }

}
