package com.quackings.liftgame;

import java.util.Map;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.dsl.FXGL;
import com.quackings.liftgame.constants.Constants;

import javafx.scene.text.Text;

public class Main extends GameApplication {
    private InputManager inputManager;
    private LevelManager levelManager;

    private Player player;

    public Main () {
        super();

        this.inputManager = new InputManager(() -> FXGL.getInput());
        this.levelManager = new LevelManager();
    }

    @Override
    protected void initSettings(GameSettings settings) {
        settings.setWidth(Constants.GAME_WIDTH);
        settings.setHeight(Constants.GAME_HEIGHT);
        settings.setTitle(Constants.GAME_TITLE);
        settings.setVersion(Constants.GAME_VERSION);
    }

    @Override
    protected void initGameVars(Map<String, Object> vars) {
        vars.put("speed", 0);
    }
    
    @Override
    protected void initGame() {
        FXGL.getGameScene().setBackgroundRepeat("bg.png");

        player = new Player()
            .configureInput(inputManager);

        levelManager.loadLevel();
    }

    // @Override
    // protected void initPhysics() {
    //     FXGL.getPhysicsWorld().setGravity(0, 0);
    // }

    @Override
    protected void initUI() {
        Text speed = new Text("Speed: ??");
        speed.setTranslateX(50);
        speed.setTranslateY(50);
        FXGL.getGameScene().addUINode(speed);

        player.configureSpeedText(speed);
    }

    // @Override
    // protected void onUpdate(double tfp) {
    //     player.update();
    // }

    public static void main(String[] args) {
        launch(args);
    }

}