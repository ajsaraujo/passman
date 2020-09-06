package com.passman.utils;

import com.passman.enums.View;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;

public class FileUtils {
    public static Scene loadView(View view) {
        URL url = FileUtils.class.getResource(view.getFileName());

        try {
            Parent root = FXMLLoader.load(url);
            return new Scene(root);
        } catch (IOException exception) {
            exception.printStackTrace();
            return new Scene(new Pane());
        }
    }
}
