package com.passman.commons;

import com.passman.commons.abstracts.FXMLFile;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import java.io.IOException;

public class Component extends FXMLFile {
    // Subclasses need a constructor to call.
    protected Component() {};

    public Component(String name) {
        this.path = Component.class.getResource("/components/" + name + ".fxml");
    }

    public void injectController(Parent controller) {
        FXMLLoader loader = createLoader(controller);

        try {
            loader.load();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    protected FXMLLoader createLoader(Parent controller) {
        FXMLLoader loader = new FXMLLoader(path);

        loader.setRoot(controller);
        loader.setController(controller);
        return loader;
    }
}
