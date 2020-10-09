package com.passman.ui.views;

import com.passman.commons.View;
import com.passman.commons.abstracts.ViewController;
import com.passman.utils.FileUtils;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;

public class Login extends ViewController {
    @FXML Button createFileButton;
    @FXML Button importFileButton;

    @FXML
    public void initialize() {
        ImageView addIcon = FileUtils.loadImageView("add");
        ImageView importIcon = FileUtils.loadImageView("import");

        createFileButton.setGraphic(addIcon);
        importFileButton.setGraphic(importIcon);
    }

    @FXML
    public void createFileClicked() {
        navigator.push(new View("sign-up"));
    }
}
