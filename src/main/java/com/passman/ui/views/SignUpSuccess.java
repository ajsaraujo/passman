package com.passman.ui.views;

import com.passman.commons.View;
import com.passman.commons.abstracts.ViewController;
import javafx.fxml.FXML;

public class SignUpSuccess extends ViewController {
    @FXML
    public void continueButtonClicked() {
        navigator.pushReplacement(new View("login"));
    }
}
