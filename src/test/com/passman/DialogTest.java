package com.passman;

import javafx.scene.Parent;
import javafx.stage.Stage;

import static org.mockito.Mockito.spy;

// NOTE:
// On dialog tests, we can't just call dialog.show(). If we did so, TestFX
// wouldn't acknowledge the created Stage.

public class DialogTest extends UITest {
    protected Stage mockStage;
    protected Stage actualStage;

    @Override
    public void start(Stage primaryStage) {
        mockStage = spy(Stage.class);
        actualStage = primaryStage;
    }
}
