package com.passman.ui.views;

import com.passman.commons.Form;
import com.passman.commons.abstracts.ViewController;
import com.passman.ui.components.ConfirmOrCancel;
import com.passman.ui.components.FileField;
import com.passman.ui.components.FormField;
import com.passman.utils.SerializingUtils;
import javafx.fxml.FXML;

import java.io.StreamCorruptedException;

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
            try {
                SerializingUtils.deserialize(fileField.getText(), passwordField.getText());
            } catch (StreamCorruptedException exception) {
                passwordField.showErrorMessage("Invalid password.");
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
    }
}
