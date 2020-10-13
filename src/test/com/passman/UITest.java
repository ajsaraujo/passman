package com.passman;

import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.stage.Stage;
import org.junit.After;
import org.junit.Before;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit.ApplicationTest;

public abstract class UITest extends ApplicationTest {
    protected Stage primaryStage;

    @Before
    public void setUp() {};

    @After
    public void tearDown() throws Exception {
        FxToolkit.hideStage();
        release(new KeyCode[]{});
        release(new MouseButton[]{});
    }

    protected void render(Scene scene) {
        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.toFront();
    }
}