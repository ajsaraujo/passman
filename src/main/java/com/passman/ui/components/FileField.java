package com.passman.ui.components;

import com.passman.commons.Component;
import com.passman.commons.interfaces.ValidableField;
import com.passman.utils.FileUtils;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public abstract class FileField extends VBox implements ValidableField {
    @FXML TextField textField;
    @FXML Label errorLabel;

    protected FileChooser fileChooser;

    public FileField() {
        Component fxmlFile = new Component("file-field");
        fxmlFile.injectController(this);
    }

    @FXML
    public void initialize() {
        textField.setEditable(false);

        errorLabel.setText(null);
        errorLabel.setVisible(false);

        fileChooser = createFileChooser();
    }

    @FXML
    public abstract void searchFileButtonClicked() throws IOException;

    public boolean validate() {
        String path = getText();
        String errorMessage = null;

        if (path.isEmpty() || path.isBlank()) {
            errorMessage = "You should pick a file location.";
        } else if (!FileUtils.isValid(new File(path))) {
            errorMessage = "Please pick another location.";
        }

        boolean isValid = errorMessage == null;

        errorLabel.setVisible(!isValid);
        errorLabel.setText(errorMessage);

        return isValid;
    }

    public String getText() {
        return textField.getText();
    }

    private FileChooser createFileChooser() {
        FileChooser.ExtensionFilter pmanExtension = new FileChooser.ExtensionFilter("Passman Files (*.pman)", ".pman");

        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(pmanExtension);
        fileChooser.setSelectedExtensionFilter(pmanExtension);

        return fileChooser;
    }

    protected void updateTextFieldWithNewFile(File selectedFile) throws IOException {
        if (selectedFile != null) {
            textField.setText(selectedFile.getCanonicalPath());
        }
    }
}
