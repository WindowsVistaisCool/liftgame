package com.quackings.liftgame;

import com.almasb.fxgl.dsl.FXGL;

import javafx.scene.text.Text;

public class Debug {
    public static final boolean DEBUG_ENABLED = true;

    public static void newText(String text, int x, int y) {
        if (DEBUG_ENABLED) {
            FXGL.set(text, 0);
            Text t = new Text(text);
            FXGL.entityBuilder()
                .at(x, y)
                .view(t)
                .buildAndAttach();
            t.textProperty().bind(FXGL.getWorldProperties().intProperty(text).asString(text +": %d"));
        }
    }
}
