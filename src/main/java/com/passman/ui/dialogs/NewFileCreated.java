package com.passman.ui.dialogs;

import com.passman.commons.Dialog;
import com.passman.utils.UIUtils;
import javafx.fxml.FXML;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class NewFileCreated extends VBox {
    private final Stage stage;

    public NewFileCreated(Stage stage) {
        this.stage = stage;

        Dialog fxmlFile = new Dialog("new-file-created");
        fxmlFile.injectController(this);
    }

    @FXML
    public void proceedButtonClicked() {
        stage.close();
    };

    public void show() {
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(UIUtils.makeStyledScene(this));
        stage.setTitle("Passman");

        stage.showAndWait();
        stage.centerOnScreen();
    }
}
