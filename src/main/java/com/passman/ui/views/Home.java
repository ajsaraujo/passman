package com.passman.ui.views;

import com.passman.commons.abstracts.ViewController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;

public class Home extends ViewController {
    @FXML Button newCredentialButton;

    @FXML
    public void initialize() {
        newCredentialButton.setTooltip(new Tooltip("New credential"));
    }

    @FXML
    public void newCredentialButtonClicked() {

    }
}
