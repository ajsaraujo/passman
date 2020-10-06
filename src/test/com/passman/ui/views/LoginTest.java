package com.passman.ui.views;

import com.passman.commons.Navigator;
import com.passman.commons.View;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.stage.Stage;
import org.junit.After;
import org.junit.Test;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit.ApplicationTest;

import java.util.Stack;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class LoginTest extends ApplicationTest {
    private Navigator navigator;

    @Override
    public void start(Stage primaryStage) throws Exception {
        View view = new View("login");

        this.navigator = spy(new Navigator(primaryStage, new Stack<>()));

        // Pushing two times so the stack doesn't get empty after a pop.
        navigator.push(view);
    }

    @Test
    public void shouldPushSignUpScreenWhenHyperlinkIsClicked() {
        Platform.runLater(() -> {
            navigator.render();
            clickOn("#hyperlink");

            verify(navigator, times(1)).push(any());
        });
    }

    @After
    public void tearDown() throws Exception {
        FxToolkit.hideStage();
        release(new KeyCode[]{});
        release(new MouseButton[]{});
    }
}