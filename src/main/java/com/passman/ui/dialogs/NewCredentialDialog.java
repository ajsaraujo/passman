package com.passman.ui.dialogs;

import com.passman.commons.DialogFile;
import com.passman.commons.Form;
import com.passman.models.Credential;
import com.passman.ui.components.ConfirmOrCancel;
import com.passman.ui.components.FormField;
import com.passman.utils.Randomizer;
import com.passman.utils.ClipboardManager;
import javafx.fxml.FXML;
import javafx.stage.Stage;


public class NewCredentialDialog extends Dialog {
    @FXML FormField usernameField;
    @FXML FormField serviceField;
    @FXML FormField passwordField;
    @FXML ConfirmOrCancel buttons;

    private final Randomizer randomizer;
    private final ClipboardManager clipboard;
    private Form form;
    private Credential createdCredential;

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

        form = new Form(serviceField, usernameField, passwordField);

        buttons.setOnConfirm(e -> confirmButtonClicked());
        buttons.setStage(this.stage);
    }

    public void confirmButtonClicked() {
        if (form.validate()) {
            createdCredential = new Credential(serviceField.getText(), usernameField.getText(), passwordField.getText());
            stage.close();
        }
    }

    private void copyPasswordToClipboard() {
        clipboard.copy(passwordField.getText());
    }

    public Credential promptForCredential() {
        super.show();

        return createdCredential;
    }

    // Getters and setters for test purposes.
    public String getPasswordFieldContent() {
        return passwordField.getText();
    }

    public void fillForm(String service, String username, String password) {
        serviceField.setText(service);
        usernameField.setText(username);
        passwordField.setText(password);
    }
}
