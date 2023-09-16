package com.quackings.liftgame.constants;

import com.almasb.fxgl.input.UserAction;
import com.quackings.liftgame.Player;
import com.quackings.liftgame.suppliers.PlayerSpeedSupplier;
import com.quackings.liftgame.suppliers.UserActionSupplier;

public class InputFunctions {
    public static class suppliers {
        public static UserActionSupplier MOVE_PLAYER = (entity, args) -> {
            return new UserAction("MOVE_PLAYER_" + args.get("idSuffix")) {
                @Override
                protected void onAction() {
                    entity.translateX((double)args.get("x")*((PlayerSpeedSupplier) args.get("speed")).getSpeed());
                    entity.translateY((double)args.get("y")*((PlayerSpeedSupplier) args.get("speed")).getSpeed());
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
