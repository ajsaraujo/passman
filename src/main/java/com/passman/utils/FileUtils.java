package com.passman.utils;

import com.passman.enums.View;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import jfxtras.styles.jmetro.JMetro;
import jfxtras.styles.jmetro.Style;

import java.io.IOException;
import java.net.URL;

public class FileUtils {
    public static Scene loadView(View view) {
        URL url = FileUtils.class.getResource(view.getFileName());

        try {
            Parent root = FXMLLoader.load(url);
            Scene styledScene = style(new Scene(root));

            return styledScene;
        } catch (IOException exception) {
            exception.printStackTrace();
            return new Scene(new Pane());
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
