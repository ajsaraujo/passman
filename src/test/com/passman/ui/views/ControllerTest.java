package com.passman.ui.views;

import com.passman.commons.Navigator;
import com.passman.commons.View;
import com.passman.commons.abstracts.ViewController;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.stage.Stage;
import org.junit.After;
import org.junit.Before;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit.ApplicationTest;

import java.util.Stack;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;

public abstract class ControllerTest extends ApplicationTest {
    protected Stage primaryStage;
    protected Navigator navigator;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.navigator = mock(Navigator.class);

        ViewController.setNavigator(navigator);
    }

    @Before
    public void setUp() {};

    @After
    public void tearDown() throws Exception {
        FxToolkit.hideStage();
        release(new KeyCode[]{});
        release(new MouseButton[]{});
    }

    protected void render(View view) {
        Scene scene = view.loadScene();
        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.toFront();
    }
}
