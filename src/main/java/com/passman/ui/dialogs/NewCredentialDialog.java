package com.passman.ui.dialogs;

import com.passman.commons.DialogFile;
import com.passman.utils.Randomizer;
import javafx.stage.Stage;

import java.util.Random;

public class NewCredentialDialog extends Dialog {
    public NewCredentialDialog(Stage stage, Randomizer randomizer) {
        super(stage);

        DialogFile fxmlFile = new DialogFile("new-credential-dialog");
        fxmlFile.injectController(this);
    }
}
