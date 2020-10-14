package com.passman.ui.components;

import javafx.fxml.FXML;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class OpenFileField extends FileField {
    @FXML
    public void searchFileButtonClicked() throws IOException {
        File selectedFile = fileChooser.showOpenDialog(new Stage());

        updateTextFieldWithNewFile(selectedFile);
    }
}
