package com.passman.ui.components;

import com.passman.commons.ValidationResult;
import com.passman.commons.interfaces.ValidableField;
import com.passman.commons.interfaces.ValidableValue;
import com.passman.commons.Component;
import com.passman.utils.FileUtils;
import javafx.beans.NamedArg;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class FormField extends AnchorPane implements ValidableField {
    @FXML Label label;
    @FXML Label errorLabel;
    @FXML TextField textField;
    @FXML PasswordField passwordField;
    @FXML Button actionButton;

    private final String labelText;
    private final boolean obscureText;
    private final boolean required;
    private final String iconName;
    private final String actionTooltip;
    private final boolean hasAction;

    private ValidableValue validator;

    public FormField(
            @NamedArg("labelText") String labelText,
            @NamedArg("obscureText") boolean obscureText,
            @NamedArg("required") boolean required,
            @NamedArg("iconName") String iconName,
            @NamedArg("String") String actionTooltip) {

        this.labelText = labelText;
        this.obscureText = obscureText;
        this.required = required;
        this.iconName = iconName;
        this.actionTooltip = actionTooltip;
        this.hasAction = iconName != null;

        Component fxmlFile = new Component("form-field");
        fxmlFile.injectController(this);
    }

    @FXML
    public void initialize() {
        initLabelAndPromptText();

        if (hasAction) {
            initActionButton();
        } else {
            actionButton.setVisible(false);
        }

        initInputFields();
    }

    private void initInputFields() {
        // By default, FormField accepts anything.
        validator = e -> new ValidationResult(true);

        if (obscureText) {
            textField.setVisible(false);
            textField = passwordField;
        } else {
            passwordField.setVisible(false);
        }
    }

    private void initLabelAndPromptText() {
        label.setText(labelText);
        textField.setPromptText(null);
    }

    private void initActionButton() {
        ImageView imageView = FileUtils.loadImageView(iconName);
        actionButton.setGraphic(imageView);

        if (actionTooltip != null) {
            actionButton.setTooltip(new Tooltip(actionTooltip));
        }

        // We need to add some padding so the button doesn't cover the text.
        textField.setPadding(new Insets(0, 60, 0, 0));
        textField.setPadding(new Insets(0, 60, 0, 0));
    }

    public boolean validate() {
        String text = textField.getText();
        ValidationResult result;

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

    public void showErrorMessage(String errorMessage) {
        errorLabel.setText(errorMessage);
    }

    public String getText() {
        return textField.getText();
    }

    public void setText(String text) {
        textField.setText(text);
    }

    public void setValidator(ValidableValue validator) {
        this.validator = validator;
    }

    public void setOnAction(EventHandler<ActionEvent> handler) {
        actionButton.setOnAction(handler);
    }

    // Accessors for test purposes
    public Button getActionButton() { return actionButton; }

    public Label getLabel() {
        return label;
    }

    public Label getErrorLabel() {
        return errorLabel;
    }

    public TextField getTextField() { return textField; }

    public TextField getPasswordField() { return passwordField; }

    public boolean hasAction() {
        return hasAction;
    }
}
