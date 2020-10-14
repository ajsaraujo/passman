package com.passman.ui.components;

import com.passman.utils.FileUtils;
import javafx.fxml.FXML;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class SaveFileField extends FileField {
    @FXML
    public void initialize() {
        super.initialize();

        textField.setText(FileUtils.getAppFolder() + File.separatorChar + "save.pman");
    }

    @FXML
    public void searchFileButtonClicked() throws IOException {
        fileChooser.setInitialFileName("save.pman");

        File selectedFile = fileChooser.showSaveDialog(new Stage());

        updateTextFieldWithNewFile(selectedFile);
    }
}
