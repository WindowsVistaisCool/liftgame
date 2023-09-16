package com.quackings.liftgame;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.quackings.liftgame.constants.InputFunctions;
import com.quackings.liftgame.suppliers.PlayerSpeedSupplier;

import javafx.scene.input.KeyCode;
import javafx.scene.text.Text;

public class Player extends Entity {
    public static Player playerInstance = null;
    
    public static double speed = 5.0;
    private static Map<KeyCode, Map<String, Object>> inputMaps = new HashMap<KeyCode, Map<String, Object>>() {{
        put(KeyCode.W, new HashMap<String, Object>() {{
            put("idSuffix", "W");
            put("x", 0.0);
            put("y", -1.0);
            put("speed", (PlayerSpeedSupplier) () -> speed);
        }});
        put(KeyCode.A, new HashMap<String, Object>() {{
            put("idSuffix", "A");
            put("x", -1.0);
            put("y", 0.0);
            put("speed", (PlayerSpeedSupplier) () -> speed);
        }});
        put(KeyCode.S, new HashMap<String, Object>() {{
            put("idSuffix", "S");
            put("x", 0.0);
            put("y", 1.0);
            put("speed", (PlayerSpeedSupplier) () -> speed);
        }});
        put(KeyCode.D, new HashMap<String, Object>() {{
            put("idSuffix", "D");
            put("x", 1.0);
            put("y", 0.0);
            put("speed", (PlayerSpeedSupplier) () -> speed);
        }});
        put(KeyCode.Q, new HashMap<String, Object>() {{
            put("idSuffix", "SLOW");
            put("speed", (PlayerSpeedSupplier) () -> speed - 1.0 <= 0.0 ? speed : speed - 1.0);
        }});
        put(KeyCode.E, new HashMap<String, Object>() {{
            put("idSuffix", "SPEED");
            put("speed", (PlayerSpeedSupplier) () -> speed + 1.0);
        }});
    }};

    private Entity playerEntity;

    public Player(Entity fromBuilder) {
        super();

        this.playerEntity = fromBuilder;

        if (playerInstance == null) {
            playerInstance = this;
        }
    }

    public Player configureInput(InputManager inputManagerInstance, double speedOverride) {
        if (speedOverride != speed) {
            Player.speed = speedOverride;
            FXGL.set("speed", (int)speed*10);
        }

        List<KeyCode> keys = Arrays.asList(KeyCode.W, KeyCode.A, KeyCode.S, KeyCode.D);
        for (KeyCode key : keys) {
            inputManagerInstance.addAction(key, InputFunctions.suppliers.MOVE_PLAYER.supply(this.playerEntity, inputMaps.get(key)));
        }

        inputManagerInstance.addAction(KeyCode.Q, InputFunctions.suppliers.MODIFY_PLAYER_SPEED.supply(this.playerEntity, inputMaps.get(KeyCode.Q)));
        inputManagerInstance.addAction(KeyCode.E, InputFunctions.suppliers.MODIFY_PLAYER_SPEED.supply(this.playerEntity, inputMaps.get(KeyCode.E)));

        return this;
    }
    public Player configureInput(InputManager inputManagerInstance) { return configureInput(inputManagerInstance, speed); }

    public Player configureSpeedText(Text text) {
        FXGL.set("speed", (int)speed*10);
        text.textProperty().bind(FXGL.getWorldProperties().intProperty("speed").asString("Speed: %d"));
        return this;
    }

    public static void setSpeed(double speed) {
        Player.speed = speed;
        FXGL.set("speed", (int)speed*10);
    }

    public static Player getPlayerInstance() {
        return playerInstance;
    }
}
