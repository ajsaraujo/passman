package com.passman.commons;

import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Stack;

public class Navigator {
    private Stage stage;
    private final Stack<Scene> sceneStack = new Stack<>();

    public Navigator(Stage stage, String firstViewFileName) {
        this.stage = stage;

        View firstView = new View(firstViewFileName);
        Scene scene = firstView.loadScene();
        sceneStack.push(scene);
    }

    public void renderView() {
        Scene scene = sceneStack.peek();

        stage.setScene(scene);
        stage.setTitle("Passman");
        stage.centerOnScreen();
        stage.show();
    }

    public void push(String viewName) {
        View view = new View(viewName);
        Scene scene = view.loadScene();
        sceneStack.push(scene);

        stage.setScene(scene);
    }

    public void pop() {
        sceneStack.pop();
        stage.setScene(sceneStack.peek());
    }
}
