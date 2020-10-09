package com.passman.ui.views;

import com.passman.commons.Form;
import com.passman.commons.ValidationResult;
import com.passman.commons.abstracts.ViewController;
import com.passman.ui.components.FormField;
import com.passman.utils.FileUtils;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class SignUp extends ViewController {
    @FXML FormField nameField;
    @FXML FormField passwordField;
    @FXML FormField confirmPasswordField;
    @FXML Button searchFileButton;
    @FXML TextField fileLocationField;
    @FXML Label locationErrorLabel;

    private Form form;
    private File selectedFile;

    @FXML
    public void initialize() {
        String filePath = FileUtils.getAppFolder() + File.separatorChar + "save.pman";
        selectedFile = new File(filePath);

        fileLocationField.setText(filePath);
        fileLocationField.setEditable(false);

        locationErrorLabel.setVisible(false);

        form = new Form(nameField, passwordField, confirmPasswordField);

        injectValidators();
    }

    @FXML
    public void confirmButtonClicked() {
        ValidationResult result = validateLocation();

        if (result.isValid()) {
            locationErrorLabel.setVisible(false);
        } else {
            locationErrorLabel.setVisible(true);
            locationErrorLabel.setText(result.getMessage());
        }

        if (form.validate() && result.isValid()) {
            navigator.pop();
        }
    }

    private ValidationResult validateLocation() {
        String path = fileLocationField.getText();

        if (path.isEmpty() || path.isBlank()) {
            return new ValidationResult("This field is required.", false);
        }

        if (!FileUtils.isValid(selectedFile)) {
            return new ValidationResult("File path is not valid.", false);
        }

        return new ValidationResult(true);
    }

    @FXML
    public void searchFileButtonClicked() throws IOException {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter pmanExtension = new FileChooser.ExtensionFilter("Passman Files (*.pman)", ".pman");

        fileChooser.setInitialFileName("save.pman");
        fileChooser.getExtensionFilters().add(pmanExtension);
        fileChooser.setSelectedExtensionFilter(pmanExtension);

        File selectedFile = fileChooser.showSaveDialog(new Stage());

        if (selectedFile != null) {
            this.selectedFile = selectedFile;
            fileLocationField.setText(selectedFile.getCanonicalPath());
        }
    }

    @FXML
    public void cancelButtonClicked() {
        navigator.pop();
    }

    private void injectValidators() {
        int USERNAME_MINIMAL_LENGTH = 3;
        int PASSWORD_MINIMAL_LENGTH = 4;

        nameField.setValidator(value -> {
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
