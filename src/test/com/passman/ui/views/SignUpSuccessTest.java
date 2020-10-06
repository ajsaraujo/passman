package com.passman.ui.views;

import com.passman.commons.View;
import javafx.stage.Stage;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class SignUpSuccessTest extends ControllerTest {
    @Override
    public void start(Stage primaryStage) {
        super.start(primaryStage);
        render(new View("sign-up-success"));
    }

    @Test
    public void continueShouldPushReplacement() {
        clickOn("#continueButton");
        verify(navigator, times(1)).pushReplacement(any());
    }
}