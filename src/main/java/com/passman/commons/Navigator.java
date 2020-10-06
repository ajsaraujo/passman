package com.passman.commons;

import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Stack;

public class Navigator {
    private Stage stage;
    private final Stack<Scene> sceneStack;

    public Navigator(Stage stage, Stack<Scene> sceneStack) {
        this.stage = stage;
        this.sceneStack = sceneStack;
    }

    public void render() {
        Scene scene = sceneStack.peek();

        stage.setScene(scene);
        stage.setTitle("Passman");
        stage.centerOnScreen();
        stage.show();
    }

    public void push(View view) {
        Scene scene = view.loadScene();

        sceneStack.push(scene);
        stage.setScene(scene);
    }

    public void pop() {
        sceneStack.pop();
        stage.setScene(sceneStack.peek());
    }
}
