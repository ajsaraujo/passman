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
import static org.mockito.Mockito.*;

public class NavigatorTest extends ApplicationTest {
    private View mockView;
    private Scene mockScene;

    @Override
    public void start(Stage primaryStage) {
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

    @Test
    public void popShouldPopAndSetTopScene() {
        Platform.runLater(() -> {
            Scene bottomScene = new Scene(new Pane());
            Scene topScene = new Scene(new Pane());

            Stack<Scene> stack = new Stack<>();
            Stage stage = new Stage();

            stack.push(bottomScene);
            stack.push(topScene);

            Navigator navigator = new Navigator(stage, stack);

            navigator.pop();

            assertEquals(stack.peek(), bottomScene);
            assertEquals(stack.peek(), stage.getScene());
        });
    }

    @Test
    public void renderShouldRenderCurrentScene() {
        Platform.runLater(() -> {
            Scene scene = new Scene(new Pane());
            Stack<Scene> stack = new Stack<>();
            Stage stage = new Stage();

            stack.push(scene);

            Navigator navigator = new Navigator(stage, stack);

            navigator.render();

            // We can't test if .show() is called because it is final and thus can't be spied.
            assertEquals(stage.getScene(), scene);
            assertEquals(stage.getTitle(), "Passman");
        });
    }

    @Test
    public void pushReplacementShouldClearAndPush() {
        Platform.runLater(() -> {
            Scene scene = new Scene(new Pane());
            Stack<Scene> stack = new Stack<>();
            Stage stage = new Stage();

            for (int i = 0; i < 3; i++) {
                stack.push(scene);
            }

            Navigator navigator = spy(new Navigator(stage, stack));

            navigator.pushReplacement(mockView);

            assertEquals(1, stack.size());
            verify(navigator, times(1)).push(any());
        });
    }
}