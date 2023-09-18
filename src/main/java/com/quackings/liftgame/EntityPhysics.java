package com.quackings.liftgame;

import java.util.function.BooleanSupplier;

import com.almasb.fxgl.entity.Entity;
import com.quackings.liftgame.constants.EntityTypes;

public class EntityPhysics {
    
    protected Entity entity;
    protected EntityTypes entityType;
    protected int gravity;
    protected BooleanSupplier isCurrentlyFalling;

    public EntityPhysics(Entity entity, EntityTypes entityType, int gravity, BooleanSupplier isCurrentlyFalling) {
        this.entity = entity;
        this.entityType = entityType;
        this.gravity = gravity;
        this.isCurrentlyFalling = isCurrentlyFalling;
    }

    public void setupCollision() {
        
    }

    public void update(double tfp) {
        if (isCurrentlyFalling.getAsBoolean()) {
            this.entity.translateY(this.gravity*tfp);
        }
    }
}
