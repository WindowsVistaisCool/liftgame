package com.quackings.liftgame.suppliers;

import java.util.Map;

import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.input.UserAction;

public interface UserActionSupplier {
    public UserAction supply(Entity entity, Map<String, Object> args);
}
