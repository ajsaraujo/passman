package com.passman.ui.components;

import com.passman.commons.interfaces.Validable;
import com.passman.enums.Component;
import com.passman.utils.FileUtils;
import javafx.beans.NamedArg;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class FormField extends AnchorPane {
    @FXML Label label;
    @FXML Label errorLabel;
    @FXML TextField textField;
    @FXML PasswordField passwordField;

    private final String labelText;
    private final boolean obscureText;
    private final boolean required;

    private Validable validator;

    public FormField(
            @NamedArg("labelText") String labelText,
            @NamedArg("obscureText") boolean obscureText,
            @NamedArg("required") boolean required) {

        this.labelText = labelText;
        this.obscureText = obscureText;
        this.required = required;

        FileUtils.injectComponentController(Component.FORM_FIELD, this);
    }

    @FXML
    public void initialize() {
        label.setText(labelText);
        textField.setPromptText(null);

        if (obscureText) {
            textField.setVisible(false);
            textField = passwordField;
        } else {
            passwordField.setVisible(false);
        }
    }

    public boolean validate() {
        String text = textField.getText();
        String errorMessage = null;

        if (text.isEmpty() || text.isBlank()) {
            if (required) {
                errorMessage = "This field is required.";
            }
        } else {
            errorMessage = validator.validate(text);
        }

        errorLabel.setText(errorMessage);

        boolean isValid = errorMessage == null;
        return isValid;
    }

    public String getText() {
        return textField.getText();
    }

    public void setValidator(Validable validator) {
        this.validator = validator;
    }

    // Accessors for test purposes
    public Label getLabel() {
        return label;
    }

    public Label getErrorLabel() {
        return errorLabel;
    }

    public TextField getTextField() { return textField; }

    public TextField getPasswordField() { return passwordField; }
}
