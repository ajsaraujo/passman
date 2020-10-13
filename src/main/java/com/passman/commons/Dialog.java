package com.passman.commons;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.IOException;

public class Dialog extends Component {
    public Dialog(String name) {
        this.path = Component.class.getResource("/dialogs/" + name + ".fxml");
    }
}
