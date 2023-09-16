package com.quackings.liftgame;

import java.util.ArrayList;
import java.util.List;

import com.almasb.fxgl.input.Input;
import com.almasb.fxgl.input.UserAction;
import com.quackings.liftgame.suppliers.InputSupplier;

import javafx.scene.input.KeyCode;

public class InputManager {
    private InputSupplier actionConsumer;

    public List<Object> actions;

    public InputManager(InputSupplier actionConsumer) {
        this.actionConsumer = actionConsumer;
        this.actions = new ArrayList<>();
    }

    public void addAction(KeyCode key, UserAction action) {
        this.actions.add(new ArrayList<>(){{
            add(key);
            add(action);
        }});

        Input input = this.actionConsumer.getInput();
        
        input.addAction(action, key);
    }

    public List<Object> getActions() {
        return this.actions;
    }
}
