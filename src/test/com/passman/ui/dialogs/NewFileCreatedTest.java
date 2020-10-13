package com.passman.ui.dialogs;

import com.passman.UITest;
import javafx.stage.Stage;
import org.junit.Test;

import static org.mockito.Mockito.*;

public class NewFileCreatedTest extends UITest {
    private Stage stage;

    @Override
    public void start(Stage primaryStage) {
        stage = spy(Stage.class);
        NewFileCreated dialog = new NewFileCreated(stage);
        dialog.show();
    }

    @Test
    public void clickingOkShouldCloseTheDialog() {
        clickOn("#proceedButton");
        verify(stage, times(1)).close();
    }
}