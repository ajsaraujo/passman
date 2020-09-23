package com.passman.ui.views;

import com.passman.commons.Form;
import com.passman.commons.abstracts.ViewController;
import com.passman.ui.components.FormField;
import com.passman.commons.Navigator;
import javafx.fxml.FXML;

public class SignUp extends ViewController {
    @FXML FormField usernameField;
    @FXML FormField passwordField;
    @FXML FormField confirmPasswordField;

    private Form form;

    @FXML
    public void initialize() {
        form = new Form(usernameField, passwordField, confirmPasswordField);

        injectValidators();
    }

    @FXML
    public void confirmButtonClicked() {
        if (form.validate()) {
            navigator.pop();
        }
    }

    @FXML
    public void cancelButtonClicked() {
        navigator.pop();
    }

    private void injectValidators() {
        int USERNAME_MINIMAL_LENGTH = 3;
        int PASSWORD_MINIMAL_LENGTH = 4;

        usernameField.setValidator(value -> {
            String parsedValue = value.strip().toLowerCase();

            if (parsedValue.length() < USERNAME_MINIMAL_LENGTH) {
                return "Your username must have at least " + USERNAME_MINIMAL_LENGTH + " characters.";
            }

            if (!parsedValue.matches("^[a-z0-9]*$")) {
                return "Your username must only contain letters and numbers.";
            };

            return null;
        });

        passwordField.setValidator(value -> {
            if (value.length() < PASSWORD_MINIMAL_LENGTH) {
                return "Your password must have at least " + PASSWORD_MINIMAL_LENGTH + " characters.";
            }

            return null;
        });

        confirmPasswordField.setValidator(value -> {
            if (value.length() < PASSWORD_MINIMAL_LENGTH) {
                return "Your password must have at least " + PASSWORD_MINIMAL_LENGTH + " characters.";
            }

            if (!value.equals(passwordField.getText())) {
                return "The passwords don't match.";
            }

            return null;
        });
    }
}
