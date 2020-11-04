package com.passman.commons;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.IOException;

public class DialogFile extends Component {
    public DialogFile(String name) {
        this.path = Component.class.getResource("/dialogs/" + name + ".fxml");
    }
}
