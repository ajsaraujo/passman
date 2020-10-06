package com.passman.commons.abstracts;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import jfxtras.styles.jmetro.JMetro;
import jfxtras.styles.jmetro.Style;

import java.io.IOException;
import java.net.URL;

public abstract class FXMLFile {
    protected URL path;

    public final Scene loadScene() {
        try {
            Parent root = FXMLLoader.load(path);
            Scene scene = new Scene(root);

            return styled(scene);
        } catch (IOException exception) {
            exception.printStackTrace();

            return new Scene(new Pane());
        }
    }

    private Scene styled(Scene scene) {
        JMetro jMetro = new JMetro(Style.LIGHT);
        jMetro.setScene(scene);

        String rootStylesheet = FXMLFile.class.getResource("/styles/root.css").toString();
        scene.getStylesheets().add(rootStylesheet);

        return scene;
    }
}
