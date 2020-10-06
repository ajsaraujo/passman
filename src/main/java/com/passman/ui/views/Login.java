package com.passman.ui.views;

import com.passman.commons.abstracts.ViewController;
import com.passman.commons.Navigator;

import javafx.fxml.FXML;

public class Login extends ViewController {
    @FXML
    public void hyperlinkClicked() {
        navigator.push("sign-up");
    }
}
