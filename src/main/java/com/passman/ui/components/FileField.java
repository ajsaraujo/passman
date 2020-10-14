package com.passman.ui.components;

import com.passman.commons.Component;
import com.passman.utils.FileUtils;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class FileField extends VBox {
    @FXML TextField textField;
    @FXML Label errorLabel;

    private FileChooser fileChooser;

    public FileField() {
        Component fxmlFile = new Component("file-field");
        fxmlFile.injectController(this);
    }

    @FXML
    public void initialize() {
        textField.setEditable(false);
        textField.setText(FileUtils.getAppFolder() + File.separatorChar + "save.pman");

        errorLabel.setText(null);
        errorLabel.setVisible(false);
    }

    @FXML
    public void searchFileButtonClicked() throws IOException {
        if (fileChooser == null) {
            fileChooser = new FileChooser();
        }

        FileChooser.ExtensionFilter pmanExtension = new FileChooser.ExtensionFilter("Passman Files (*.pman)", ".pman");

        fileChooser.setInitialFileName("save.pman");
        fileChooser.getExtensionFilters().add(pmanExtension);
        fileChooser.setSelectedExtensionFilter(pmanExtension);

        File selectedFile = fileChooser.showSaveDialog(new Stage());

        if (selectedFile != null) {
            textField.setText(selectedFile.getCanonicalPath());
        }
    };

    // This setter exists for mocking purposes. We can't pass
    // the FileChooser through the constructor because the FileField
    // object is instantiated in FXML.
    public void setFileChooser(FileChooser fileChooser) {
        this.fileChooser = fileChooser;
    }

    public String getText() {
        return textField.getText();
    }
}
