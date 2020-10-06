package com.passman.ui.views;

import com.passman.commons.Navigator;
import com.passman.commons.View;
import com.passman.commons.abstracts.ViewController;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.stage.Stage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit.ApplicationTest;

import java.util.Stack;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class LoginTest extends ControllerTest {
    @Override
    public void start(Stage primaryStage) {
        super.start(primaryStage);
        render(new View("login"));
    }

    @Test
    public void shouldPushSignUpScreenWhenHyperlinkIsClicked() {
        clickOn("#hyperlink");

        verify(navigator, times(1)).push(any());
    }
}