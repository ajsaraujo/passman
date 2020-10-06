package com.passman.commons;

import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.junit.Before;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;

import java.util.Stack;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class NavigatorTest extends ApplicationTest {
    private View mockView;
    private Scene mockScene;

    @Override
    public void start(Stage stage) throws Exception {
        this.mockView = mock(View.class);
        this.mockScene = new Scene(new Pane());

        when(mockView.loadScene()).thenReturn(mockScene);
    }

    @Test
    public void pushShouldPushToTheStackAndSetScene() {
        Platform.runLater(() -> {
            Stack<Scene> stack = new Stack<>();
            Stage stage = new Stage();

            Navigator navigator = new Navigator(stage, stack);
            navigator.push(mockView);

            assertEquals(stage.getScene(), mockScene);

            assertEquals(stack.peek(), mockScene);
        });
    }
}