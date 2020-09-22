package com.passman.utils;

import com.passman.enums.View;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Stack;

public class NavigationUtils {
    private static Stage stage;
    private static Stack<Scene> sceneStack = new Stack();

    public static void init(Stage stage, View view) {
        NavigationUtils.stage = stage;

        Scene scene = FileUtils.loadScene(view);
        sceneStack.push(scene);

        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle("Passman");
        stage.show();

    }
}
