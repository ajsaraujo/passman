package com.passman.ui.views;

import com.passman.commons.Form;
import com.passman.commons.ValidationResult;
import com.passman.commons.abstracts.ViewController;
import com.passman.models.User;
import com.passman.ui.components.ConfirmOrCancel;
import com.passman.ui.components.FileField;
import com.passman.ui.components.FormField;
import com.passman.ui.dialogs.AlertDialog;
import com.passman.utils.SerializingUtils;
import javafx.fxml.FXML;
import javafx.stage.Stage;

public class SignUp extends ViewController {
    @FXML FormField nameField;
    @FXML FormField passwordField;
    @FXML FormField confirmPasswordField;
    @FXML FileField fileField;
    @FXML ConfirmOrCancel buttons;

    private Form form;

    @FXML
    public void initialize() {
        form = new Form(nameField, passwordField, confirmPasswordField, fileField);

        injectValidators();

        buttons.setNavigator(navigator);
        buttons.setOnConfirm(e -> confirmButtonClicked());
    }

    private void confirmButtonClicked() {
        if (form.validate()) {
            String password = passwordField.getText();
            User user = new User(nameField.getText());
            String path = fileField.getText();

            SerializingUtils.serialize(user, password, path);

            AlertDialog successDialog = new AlertDialog(new Stage(), "New Passman file created",
                    "New Passman file created at " + path + ". Click Ok to proceed.",
                    "Ok"
            );

            successDialog.show();
            navigator.pop();
        }
    }

    private void injectValidators() {
        int USERNAME_MINIMAL_LENGTH = 3;
        int PASSWORD_MINIMAL_LENGTH = 4;

        nameField.setValidator(value -> {
            String parsedValue = value.strip().toLowerCase();
            String errorMessage = null;

            if (parsedValue.length() < USERNAME_MINIMAL_LENGTH) {
                errorMessage = "Your username must have at least " + USERNAME_MINIMAL_LENGTH + " characters.";
            }

            if (!parsedValue.matches("^[a-z0-9]*$")) {
                errorMessage = "Your username must only contain letters and numbers.";
            };

            if (errorMessage == null) {
                return new ValidationResult(true);
            }

            return new ValidationResult(errorMessage, false);
        });

        passwordField.setValidator(value -> {
            if (value.length() < PASSWORD_MINIMAL_LENGTH) {
                return new ValidationResult(
                        "Your password must have at least " + PASSWORD_MINIMAL_LENGTH + " characters.",
                        false
                );
            }

            return new ValidationResult(true);
        });

        confirmPasswordField.setValidator(value -> {
            String errorMessage = null;

            if (value.length() < PASSWORD_MINIMAL_LENGTH) {
                errorMessage =  "Your password must have at least " + PASSWORD_MINIMAL_LENGTH + " characters.";
            }

            if (!value.equals(passwordField.getText())) {
                errorMessage = "The passwords don't match.";
            }

            if (errorMessage == null) {
                return new ValidationResult(true);
            }

            return new ValidationResult(errorMessage, false);
        });
    }
}
