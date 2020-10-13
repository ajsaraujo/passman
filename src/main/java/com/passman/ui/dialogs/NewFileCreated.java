package com.passman.ui.dialogs;

import com.passman.commons.Dialog;
import com.passman.utils.UIUtils;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class NewFileCreated extends VBox {
    public NewFileCreated() {
        Dialog fxmlFile = new Dialog("new-file-created");
        fxmlFile.injectController(this);
    }

    public void show() {
        Stage stage = new Stage();

        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(UIUtils.makeStyledScene(this));

        stage.show();
        stage.centerOnScreen();
    }
}
