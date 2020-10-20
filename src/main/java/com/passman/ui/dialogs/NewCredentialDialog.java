package com.passman.ui.dialogs;

import com.passman.commons.DialogFile;
import com.passman.ui.components.FormField;
import com.passman.utils.Randomizer;
import com.passman.utils.ClipboardManager;
import javafx.fxml.FXML;
import javafx.stage.Stage;


public class NewCredentialDialog extends Dialog {
    @FXML FormField passwordField;

    private Randomizer randomizer;
    private ClipboardManager clipboard;

    public NewCredentialDialog(Stage stage, Randomizer randomizer, ClipboardManager clipboard) {
        super(stage);

        this.randomizer = randomizer;
        this.clipboard = clipboard;

        DialogFile fxmlFile = new DialogFile("new-credential-dialog");
        fxmlFile.injectController(this);
    }

    @FXML
    public void initialize() {
        String password = randomizer.generatePassword();

        passwordField.setText(password);
        passwordField.setOnAction(e -> copyPasswordToClipboard());
    }

    private void copyPasswordToClipboard() {
        clipboard.copy(passwordField.getText());
    }

    // Accessor for test purposes.
    public String getPasswordFieldContent() {
        return passwordField.getText();
    }
}
