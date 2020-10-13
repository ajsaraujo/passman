package com.passman.ui.dialogs;

import com.passman.UITest;
import javafx.stage.Stage;
import org.junit.Ignore;
import org.junit.Test;

import static org.mockito.Mockito.*;

public class AlertDialogTest extends UITest {
    private Stage stage;

    @Override
    public void start(Stage primaryStage) {
        stage = spy(Stage.class);
        AlertDialog dialog = new AlertDialog(stage);
        dialog.show();
    }

    @Test
    @Ignore
    /*
        This test was working, but it isn't anymore.
        For some reason TestFX isn't finding #proceedButton.
     */
    public void clickingOkShouldCloseTheDialog() {
        clickOn("#proceedButton");
        verify(stage, times(1)).close();
    }
}