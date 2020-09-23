package com.passman.commons;

import com.passman.enums.View;
import com.passman.utils.FileUtils;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Stack;

public class Navigator {
    private Stage stage;
    private final Stack<Scene> sceneStack = new Stack<>();

    public void init(Stage stage, View view) {
        this.stage = stage;

        Scene scene = FileUtils.loadScene(view);
        sceneStack.push(scene);

        stage.setScene(scene);
        stage.setTitle("Passman");
        stage.centerOnScreen();
        stage.show();
    }

    public void push(View view) {
        Scene scene = FileUtils.loadScene(view);

        sceneStack.push(scene);
        stage.setScene(scene);
    }

    public void pop() {
        sceneStack.pop();
        stage.setScene(sceneStack.peek());
    }
}
