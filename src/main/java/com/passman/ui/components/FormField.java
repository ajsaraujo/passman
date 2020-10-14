package com.passman.ui.components;

import com.passman.commons.ValidationResult;
import com.passman.commons.interfaces.ValidableField;
import com.passman.commons.interfaces.ValidableValue;
import com.passman.commons.Component;
import javafx.beans.NamedArg;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class FormField extends AnchorPane implements ValidableField {
    @FXML Label label;
    @FXML Label errorLabel;
    @FXML TextField textField;
    @FXML PasswordField passwordField;

    private final String labelText;
    private final boolean obscureText;
    private final boolean required;

    private ValidableValue validator;

    public FormField(
            @NamedArg("labelText") String labelText,
            @NamedArg("obscureText") boolean obscureText,
            @NamedArg("required") boolean required) {

        this.labelText = labelText;
        this.obscureText = obscureText;
        this.required = required;

        Component fxmlFile = new Component("form-field");
        fxmlFile.injectController(this);
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
        ValidationResult result = new ValidationResult();

        if (text.isEmpty() || text.isBlank()) {
            if (required) {
                result = new ValidationResult("This field is required.", false);
            } else {
                result = new ValidationResult(true);
            }
        } else {
            result = validator.validate(text);
        }

        errorLabel.setText(result.getMessage());
        return result.isValid();
    }

    public String getText() {
        return textField.getText();
    }

    public void setValidator(ValidableValue validator) {
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
