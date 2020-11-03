package com.passman.ui.dialogs;

import com.passman.DialogTest;
import com.passman.models.Credential;
import com.passman.utils.ClipboardManager;
import com.passman.utils.Randomizer;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.junit.Test;

import java.awt.datatransfer.Clipboard;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class NewCredentialDialogTest extends DialogTest {
    private Randomizer mockRandomizer;
    private ClipboardManager mockClipboard;
    private NewCredentialDialog dialog;

    public void start(Stage primaryStage) {
        super.start(primaryStage);

        mockRandomizer = mock(Randomizer.class);
        mockClipboard = mock(ClipboardManager.class);

        when(mockRandomizer.generatePassword()).thenReturn("Flyingdinosaur!1234");

        dialog = new NewCredentialDialog(mockStage, mockRandomizer, mockClipboard);

        primaryStage.setScene(new Scene(dialog));
        primaryStage.show();
    }

    @Test
    public void shouldSetARandomPasswordInThePasswordField() {
        assertEquals(dialog.getPasswordFieldContent(), "Flyingdinosaur!1234");
    }

    @Test
    public void shouldCopyPasswordToClipboardWhenCopyIsClicked() {
        clickOn("#actionButton");

        verify(mockClipboard, times(1)).copy("Flyingdinosaur!1234");
    }

    @Test
    public void shouldReturnTheNewlyCreatedCredential() {
    }

    @Test
    public void shouldReturnNullIfUserCancels() {};
}
