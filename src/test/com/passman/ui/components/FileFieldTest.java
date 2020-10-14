package com.passman.ui.components;

import com.passman.UITest;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.matcher.base.NodeMatchers.isInvisible;

public class FileFieldTest extends UITest {
    private FileField field;

    @Override
    public void start(Stage primaryStage) {
        field = new FileField();

        primaryStage.setScene(new Scene(field));
        primaryStage.show();
    }

    @Before
    public void setUp() {
        field = new FileField();
    }

    @Test
    public void shouldHaveSomeTextByDefault() {
        String text = field.getText();

        assertNotNull(text);
        assertFalse(text.isEmpty());
        assertFalse(text.isBlank());
    }

    @Test
    public void errorMessageShouldNotBeVisibleByDefault() {
        verifyThat("#errorLabel", isInvisible());
    }

    // Could not write this test by getting the number of windows.
    // Since the FileChooser window is native, TestFX doesn't acknowledge it.
    @Test
    @Ignore
    public void clickingTheButtonShouldOpenAFileChooser() {
    }
}