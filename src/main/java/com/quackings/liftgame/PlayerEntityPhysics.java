package com.quackings.liftgame;

import java.util.function.BooleanSupplier;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.physics.CollisionHandler;
import com.quackings.liftgame.constants.EntityTypes;

public class PlayerEntityPhysics extends EntityPhysics {

    public PlayerEntityPhysics(Entity entity, EntityTypes entityType, int gravity, BooleanSupplier isCurrentlyFalling) {
        super(entity, entityType, gravity, isCurrentlyFalling);
    }

    @Override
    public void setupCollision() {
        FXGL.getPhysicsWorld().addCollisionHandler(new CollisionHandler(entityType, EntityTypes.PLATFORM) {
            @Override
            protected void onCollisionBegin(Entity player, Entity platform) {
                Player.isFalling = false;
                FXGL.set("IsFalling", 1);
            }

            @Override
            protected void onCollisionEnd(Entity player, Entity platform) {
                Player.isFalling = true;
                FXGL.set("IsFalling", 0);
            }
        });
    }
    
}
