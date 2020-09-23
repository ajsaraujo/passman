package com.passman.ui.views;

import com.passman.enums.View;
import com.passman.utils.NavigationUtils;
import javafx.fxml.FXML;

public class Login {
    @FXML
    public void hyperlinkClicked() {
        NavigationUtils.push(View.SIGN_UP);
    }
}
