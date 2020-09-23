package com.passman.controllers.components;

import com.passman.commons.Validable;
import com.passman.enums.Component;
import com.passman.utils.FileUtils;
import javafx.beans.NamedArg;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class FormField extends AnchorPane {
    @FXML Label label;
    @FXML Label errorLabel;
    @FXML TextField textField;

    private final String labelText;
    private Validable validator;

    public FormField(@NamedArg("labelText") String labelText) {
        this.labelText = labelText;

        FileUtils.injectComponentController(Component.FORM_FIELD, this);
    }

    @FXML
    public void initialize() {
        label.setText(labelText);
        textField.setPromptText(null);
    }

    public boolean validate() {
        String errorMessage = validator.validate(textField.getText());
        errorLabel.setText(errorMessage);
        
        boolean isValid = errorMessage == null;
        return isValid;
    }

    public String getLabelText() {
        return labelText;
    }

    public void setValidator(Validable validator) {
        this.validator = validator;
    }
}
