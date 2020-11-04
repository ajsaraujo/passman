package com.passman.utils;

import com.passman.ui.dialogs.NewCredentialDialog;
import javafx.stage.Stage;

public class DialogProvider {
    public NewCredentialDialog getNewCredentialDialog() {
        return new NewCredentialDialog(new Stage(), new Randomizer(), new ClipboardManager());
    }
}
