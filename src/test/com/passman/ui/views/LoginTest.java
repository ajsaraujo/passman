package com.passman.ui.views;

import com.passman.ControllerTest;
import com.passman.commons.View;
import javafx.stage.Stage;
import org.junit.Test;
import static org.mockito.Mockito.*;

public class LoginTest extends ControllerTest {
    @Override
    public void start(Stage primaryStage) {
        super.start(primaryStage);
        render(new View("login"));
    }

    @Test
    public void shouldPushSignUpWhenCreateButtonIsClicked() {
        clickOn("#createFileButton");

        verify(navigator, times(1)).push(any());
    }
}