package com.passman.ui.dialogs;

import com.passman.DialogTest;
import com.passman.utils.ClipboardUtils;
import com.passman.utils.Randomizer;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.util.NodeQueryUtils.hasText;

public class NewCredentialDialogTest extends DialogTest {
    private Randomizer mockRandomizer = mock(Randomizer.class);

    public void start(Stage primaryStage) {
        super.start(primaryStage);

        NewCredentialDialog dialog = new NewCredentialDialog(mockStage, mockRandomizer);

        primaryStage.setScene(new Scene(dialog));
        primaryStage.show();
    }

    @Test
    public void shouldSetARandomPasswordInThePasswordField() {
        when(mockRandomizer.generatePassword()).thenReturn("Flyingdinosaur!1234");

        verifyThat("#passwordField", hasText("Flyingdinosaur!1234"));
    }

    @Test
    public void shouldCopyPasswordToClipboardWhenCopyIsClicked() {
        clickOn("#actionButton");

        assertEquals(ClipboardUtils.getClipboardContent(), "Flyingdinosaur!1234");
    };

    @Test
    public void shouldReturnTheNewlyCreatedCredential() {};

    @Test
    public void shouldReturnNullIfUserCancels() {};
}
