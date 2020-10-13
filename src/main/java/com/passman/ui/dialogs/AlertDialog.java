package com.passman.ui.dialogs;

import com.passman.commons.Dialog;
import com.passman.utils.UIUtils;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AlertDialog extends VBox {
    @FXML Label titleLabel;
    @FXML Label messageLabel;
    @FXML Button okButton;

    private final String title;
    private final String message;
    private final String buttonText;
    private final Stage stage;

    public AlertDialog(Stage stage, String title, String message, String buttonText) {
        this.stage = stage;
        this.title = title;
        this.message = message;
        this.buttonText = buttonText;

        Dialog fxmlFile = new Dialog("alert-dialog");
        fxmlFile.injectController(this);
    }

    @FXML
    public void initialize() {
        titleLabel.setText(title);
        messageLabel.setText(message);
        okButton.setText(buttonText);
    }

    @FXML
    public void okButtonClicked() {
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
