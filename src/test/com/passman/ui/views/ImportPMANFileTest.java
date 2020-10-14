package com.passman.ui.views;

import com.passman.ControllerTest;
import com.passman.commons.View;
import javafx.stage.Stage;
import org.junit.Test;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class ImportPMANFileTest extends ControllerTest {
    @Override
    public void start(Stage primaryStage) {
        super.start(primaryStage);
        render(new View("import-pman-file"));
    }

    @Test
    public void shouldNotProceedIfUserDidntPickAFile() { }

    @Test
    public void shouldNotProceedIfPasswordIsNotCorrect() {}

    @Test
    public void shouldProceedIfEverythingIsFine() {}

    private void writeOnPasswordField(String content) {
        clickOn("#passwordField");
        write(content);
    }
}