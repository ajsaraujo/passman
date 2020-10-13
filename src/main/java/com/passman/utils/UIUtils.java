package com.passman.utils;

import com.passman.commons.abstracts.FXMLFile;
import javafx.scene.Parent;
import javafx.scene.Scene;
import jfxtras.styles.jmetro.JMetro;
import jfxtras.styles.jmetro.Style;

public class UIUtils {
    public static Scene style(Scene scene) {
        JMetro jMetro = new JMetro(Style.LIGHT);
        jMetro.setScene(scene);

        String rootStylesheet = FXMLFile.class.getResource("/styles/root.css").toString();
        scene.getStylesheets().add(rootStylesheet);

        return scene;
    }

    public static Scene makeStyledScene(Parent root) {
        return style(new Scene(root));
    }
}
