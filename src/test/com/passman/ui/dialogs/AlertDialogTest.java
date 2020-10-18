package com.passman.ui.dialogs;

import com.passman.DialogTest;
import com.passman.UITest;
import com.passman.commons.DialogFile;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.junit.Ignore;
import org.junit.Test;

import static org.mockito.Mockito.*;

public class AlertDialogTest extends DialogTest {
    private AlertDialog dialog1;
    private AlertDialog dialog2;

    @Override
    public void start(Stage primaryStage) {
        super.start(primaryStage);

        dialog1 = new AlertDialog(mockStage, "Title", "Message", "Action");
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