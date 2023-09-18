package com.quackings.liftgame.constants;

import com.almasb.fxgl.input.UserAction;
import com.quackings.liftgame.Player;
import com.quackings.liftgame.PlayerAnimationComponent;
import com.quackings.liftgame.suppliers.PlayerSpeedSupplier;
import com.quackings.liftgame.suppliers.UserActionSupplier;

public class InputFunctions {
    public static class suppliers {
        public static UserActionSupplier MOVE_PLAYER = (entity, args) -> {
            return new UserAction("MOVE_PLAYER_" + args.get("idSuffix")) {
                @Override
                protected void onAction() {
                    entity.getComponent(PlayerAnimationComponent.class).moveX((int)((double)args.get("x")*((PlayerSpeedSupplier) args.get("speed")).getSpeed()));
                    // entity.translateY((double)args.get("y")*((PlayerSpeedSupplier) args.get("speed")).getSpeed());
                }
            };
        };

        public static UserActionSupplier MODIFY_PLAYER_SPEED = (entity, args) -> {
            return new UserAction("MODIFY_PLAYER_SPEED_" + args.get("idSuffix")) {
                @Override
                protected void onActionBegin() {
                    Player.setSpeed(((PlayerSpeedSupplier) args.get("speed")).getSpeed());
                }
            };
        };

        public static UserActionSupplier MODIFY_PLAYER_FALL = (entity, args) -> {
            return new UserAction("MODIFY_PLAYER_FALL_" + args.get("idSuffix")) {
                @Override
                protected void onActionBegin() {
                    Player.isFalling = !Player.isFalling;
                }
            };
        };

        public static UserActionSupplier JUMP_PLAYER = (entity, args) -> {
            return new UserAction("JUMP_PLAYER") {
                @Override
                protected void onAction() {
                    entity.getComponent(PlayerAnimationComponent.class).jump();
                }
            };
        };

        public static UserActionSupplier RESET_PLAYER = (entity, args) -> {
            return new UserAction("RESET_PLAYER") {
                @Override
                protected void onActionBegin() {
                    entity.getComponent(PlayerAnimationComponent.class).reset();
                }
            };
        };
    }

    public static UserAction print = new UserAction("Print Line") {
        @Override
        protected void onActionBegin() {
            System.out.println("Action Begin");
        }

        @Override
        protected void onAction() {
            System.out.println("On Action");
        }

        @Override
        protected void onActionEnd() {
            System.out.println("Action End");
        }
    };
}
