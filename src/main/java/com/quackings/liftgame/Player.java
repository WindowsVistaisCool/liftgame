package com.quackings.liftgame;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.physics.BoundingShape;
import com.almasb.fxgl.physics.HitBox;
import com.quackings.liftgame.constants.EntityTypes;
import com.quackings.liftgame.constants.InputFunctions;
import com.quackings.liftgame.suppliers.PlayerSpeedSupplier;

import javafx.scene.input.KeyCode;
import javafx.scene.text.Text;

public class Player extends Entity {
    public static Player playerInstance = null;
    
    public static boolean isFalling = true;
    public static int gravity = 500;
    public static double speed = 8.0;

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
        put(KeyCode.F, new HashMap<String, Object>() {{
            put("idSuffix", "FALL");
        }});
        put(KeyCode.R, new HashMap<String, Object>() {{}});
        put(KeyCode.SPACE, new HashMap<String, Object>() {{}});
    }};

    private Entity playerEntity;
    private PlayerEntityPhysics physics;
    private PlayerAnimationComponent animationComponent;

    public Player() {
        super();

        this.animationComponent = new PlayerAnimationComponent();
        this.playerEntity = FXGL.entityBuilder()
            .type(EntityTypes.PLAYER)
            .at(150, 150)
            .with(this.animationComponent)
            .bbox(new HitBox(BoundingShape.box(35, 70)))
            .collidable()
            .buildAndAttach();

        this.physics = new PlayerEntityPhysics(this.playerEntity, EntityTypes.PLAYER, gravity, () -> Player.isFalling);

        if (playerInstance == null) {
            playerInstance = this;
        }
    }

    public Player configureInput(InputManager inputManagerInstance, double speedOverride) {
        if (speedOverride != speed) {
            Player.speed = speedOverride;
            FXGL.set("speed", (int)speed*10);
        }

        List<KeyCode> keys = Arrays.asList(KeyCode.A, KeyCode.D);
        for (KeyCode key : keys) {
            inputManagerInstance.addAction(key, InputFunctions.suppliers.MOVE_PLAYER.supply(this.playerEntity, inputMaps.get(key)));
        }

        inputManagerInstance.addAction(KeyCode.SPACE, InputFunctions.suppliers.JUMP_PLAYER.supply(this.playerEntity, inputMaps.get(KeyCode.SPACE)));

        inputManagerInstance.addAction(KeyCode.Q, InputFunctions.suppliers.MODIFY_PLAYER_SPEED.supply(this.playerEntity, inputMaps.get(KeyCode.Q)));
        inputManagerInstance.addAction(KeyCode.E, InputFunctions.suppliers.MODIFY_PLAYER_SPEED.supply(this.playerEntity, inputMaps.get(KeyCode.E)));

        inputManagerInstance.addAction(KeyCode.R, InputFunctions.suppliers.RESET_PLAYER.supply(this.playerEntity, inputMaps.get(KeyCode.R)));

        return this;
    }
    public Player configureInput(InputManager inputManagerInstance) { return configureInput(inputManagerInstance, speed); }

    public Player configureSpeedText(Text text) {
        FXGL.set("speed", (int)speed*10);
        text.textProperty().bind(FXGL.getWorldProperties().intProperty("speed").asString("Speed: %d"));
        return this;
    }

    public EntityPhysics getPhysics() {
        return this.physics;
    }

    public void tick(double tpf) {
        this.physics.update(tpf);
    }

    public static void setSpeed(double speed) {
        Player.speed = speed;
        FXGL.set("speed", (int)speed*10);
    }

    public static Player getPlayerInstance() {
        return playerInstance;
    }
}

