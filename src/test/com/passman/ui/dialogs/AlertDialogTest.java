package com.passman.ui.dialogs;

import com.passman.UITest;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.junit.Ignore;
import org.junit.Test;

import static org.mockito.Mockito.*;

public class AlertDialogTest extends UITest {
    private Stage actualStage;
    private Stage mockStage;
    private AlertDialog dialog1;
    private AlertDialog dialog2;

    @Override
    @Ignore
    public void start(Stage primaryStage) {
        mockStage = spy(Stage.class);
        dialog1 = new AlertDialog(mockStage, "Title", "Message", "Action");

        // We can't just call dialog.show() because doing this, TestFX doesn't acknowledge
        // the newly created stage. We also have to create a new dialog since the same node
        // can't be the root of two different scenes.
        dialog2 = new AlertDialog(mockStage, "Title", "Message", "Action");
        actualStage = new Stage();
        actualStage.setScene(new Scene(dialog2));
        actualStage.show();
    }

    @Test
    public void clickingOkShouldCloseTheDialog() {
        clickOn("#okButton");
        verify(mockStage, times(1)).close();
    }
    
    @Test
    public void showShouldShowTheDialog() {
        // We have to call later since there's a call to Stage.show().
        Platform.runLater(() -> {
            dialog1.show();
            verify(mockStage, times(1)).showAndWait();
        });
    }
}