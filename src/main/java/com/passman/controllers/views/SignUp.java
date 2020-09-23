package com.passman.controllers.views;

import com.passman.commons.Form;
import com.passman.controllers.components.FormField;
import com.passman.utils.NavigationUtils;
import javafx.fxml.FXML;

public class SignUp {
    @FXML FormField usernameField;
    @FXML FormField passwordField;
    @FXML FormField confirmPasswordField;

    private Form form;

    @FXML
    public void initialize() {
        form = new Form(usernameField, passwordField, confirmPasswordField);

        usernameField.setValidator(value -> {
            if (value == null) {
                return "This field is required.";
            }

            String parsedValue = value.strip().toLowerCase();

            if (parsedValue.length() < 3) {
                return "Your username must have at least 3 characters.";
            }

            if (!parsedValue.matches("^[a-z0-9]*$")) {
                return "Your username must only contain letters and numbers.";
            };

            return null;
        });

        passwordField.setValidator(value -> {
            if (value.isEmpty()) {
                return "This field is required.";
            }

            return null;
        });

        confirmPasswordField.setValidator(value -> {
            if (value.isEmpty()) {
                return "This field is required.";
            }

            return null;
        });
    }

    @FXML
    public void confirmButtonClicked() {
        if (form.validate()) {
            NavigationUtils.pop();
        }
    }

    @FXML
    public void cancelButtonClicked() {
        NavigationUtils.pop();
    }
}
