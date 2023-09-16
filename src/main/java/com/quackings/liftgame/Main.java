package com.quackings.liftgame;

import java.util.Map;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.dsl.FXGL;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class Main extends GameApplication {
    protected InputManager inputManager;
    
    private Player player;

    public Main () {
        super();

        this.inputManager = new InputManager(() -> FXGL.getInput());
    }

    @Override
    protected void initSettings(GameSettings settings) {
        settings.setWidth(1500);
        settings.setHeight(1000);
        settings.setTitle("Lift Game");
        settings.setVersion("v0.1-ALPHA");
    }

    @Override
    protected void initGameVars(Map<String, Object> vars) {
        vars.put("speed", 0);
    }
    
    @Override
    protected void initGame() {
        player = new Player(
            FXGL.entityBuilder()
                .at(150, 150)
                .view(new Rectangle(40, 40, Color.BLUE))
                .buildAndAttach()
        )
            .configureInput(inputManager);
    }

    @Override
    protected void initUI() {
        Text speed = new Text("Speed: 0");
        speed.setTranslateX(50);
        speed.setTranslateY(50);
        // uiElements.add(speed);
        FXGL.getGameScene().addUINode(speed);

        player.configureSpeedText(speed);
    }

    public static void main(String[] args) {
        launch(args);
    }

}