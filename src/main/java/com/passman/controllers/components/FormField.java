package com.passman.controllers.components;

import com.passman.enums.Component;
import com.passman.utils.FileUtils;
import javafx.beans.NamedArg;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class FormField extends AnchorPane {
    @FXML Label label;
    @FXML TextField textField;

    private final String labelText;

    public FormField(@NamedArg("labelText") String labelText) {
        this.labelText = labelText;

        FileUtils.injectComponentController(Component.FORM_FIELD, this);
    }

    @FXML
    public void initialize() {
        label.setText(labelText);
        textField.setPromptText(null);
    }

    public String getLabelText() {
        return labelText;
    }
}
