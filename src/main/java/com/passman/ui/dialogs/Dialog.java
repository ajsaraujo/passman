package com.passman.ui.dialogs;

import com.passman.utils.UIUtils;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public abstract class Dialog extends VBox {
    protected final Stage stage;

    public Dialog(Stage stage) {
        this.stage = stage;
    }

    public void show() {
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(UIUtils.makeStyledScene(this));
        stage.setTitle("Passman");

        stage.showAndWait();
        stage.centerOnScreen();
    }
}
