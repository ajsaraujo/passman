package com.passman.ui.views;

import com.passman.commons.Navigator;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.stage.Stage;
import org.junit.After;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit.ApplicationTest;

import java.util.Stack;

import static org.mockito.Mockito.spy;

public abstract class ControllerTest extends ApplicationTest {
    protected Navigator navigator;

    @Override
    public void start(Stage primaryStage) throws Exception {
        navigator = spy(new Navigator(primaryStage, new Stack<>()));
    }

    @After
    public void tearDown() throws Exception {
        FxToolkit.hideStage();
        release(new KeyCode[]{});
        release(new MouseButton[]{});
    }
}
