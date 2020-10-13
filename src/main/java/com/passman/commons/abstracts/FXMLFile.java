package com.passman.commons.abstracts;

import com.passman.utils.UIUtils;
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

    public Scene loadScene() {
        try {
            Parent root = FXMLLoader.load(path);
            Scene scene = new Scene(root);

            return UIUtils.style(scene);
        } catch (IOException exception) {
            exception.printStackTrace();

            return new Scene(new Pane());
        }
    }
}
