package com.passman.ui.views;

import com.passman.commons.Form;
import com.passman.commons.abstracts.ViewController;
import com.passman.ui.components.ConfirmOrCancel;
import com.passman.ui.components.FileField;
import com.passman.ui.components.FormField;
import javafx.fxml.FXML;

public class ImportPMANFile extends ViewController {
    @FXML ConfirmOrCancel buttons;
    @FXML FileField fileField;
    @FXML FormField passwordField;

    private Form form;

    @FXML
    public void initialize() {
        form = new Form(fileField, passwordField);

        buttons.setOnConfirm(e -> confirmButtonClicked());
        buttons.setNavigator(navigator);
    }

    private void confirmButtonClicked() {
        if (form.validate()) {
            // push something
        }
    }
}
