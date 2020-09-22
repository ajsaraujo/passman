package com.passman.utils;

import com.passman.enums.Component;
import com.passman.enums.View;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import jfxtras.styles.jmetro.JMetro;
import jfxtras.styles.jmetro.Style;

import java.io.IOException;
import java.net.URL;

public class FileUtils {
    public static Scene loadScene(View view) {
        try {
            Parent root = FXMLLoader.load(view.getUrl());
            Scene styledScene = style(new Scene(root));

            return styledScene;
        } catch (IOException exception) {
            exception.printStackTrace();
            return new Scene(new Pane());
        }
    }

    public static void injectComponentController(Component component, Parent componentController) {
        URL url = component.getUrl();

        FXMLLoader loader = new FXMLLoader(url);

        loader.setRoot(componentController);
        loader.setController(componentController);

        try {
            loader.load();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    private static Scene style(Scene scene) {
        JMetro jMetro = new JMetro(Style.LIGHT);
        jMetro.setScene(scene);

        String rootStylesheet = FileUtils.class.getResource("/styles/root.css").toString();
        scene.getStylesheets().add(rootStylesheet);

        return scene;
    }
}
