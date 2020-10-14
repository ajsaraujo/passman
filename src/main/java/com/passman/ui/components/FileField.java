package com.passman.ui.components;

import com.passman.commons.Component;
import com.passman.utils.FileUtils;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class FileField extends VBox {
    @FXML TextField textField;

    public FileField() {
        Component fxmlFile = new Component("file-field");
        fxmlFile.injectController(this);
    }

    @FXML
    public void initialize() {
        textField.setEditable(false);
        textField.setText(FileUtils.getAppFolder());
    }

    public void validate() {

    }
}
