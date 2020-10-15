package com.passman.ui.views;

import com.passman.ControllerTest;
import com.passman.commons.View;
import com.passman.models.User;
import com.passman.utils.FileUtils;
import javafx.stage.Stage;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import java.io.StreamCorruptedException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class ImportPMANFileTest extends ControllerTest {
    private String path;

    @Override
    public void start(Stage primaryStage) {
        path = FileUtils.getDummyFilePath();

        super.start(primaryStage);
        render(new View("import-pman-file"));
    }

    @Test
    public void shouldNotDeserializeIfUserDidNotPickAFile() throws Exception {
        writeToField("#passwordField", "blablabla");
        clickOn("#confirmButton");

        verify(serializer, times(0)).deserialize("", "blablabla");
        verify(navigator, times(0)).pop();
    }

    @Test
    public void shouldNotDeserializeIfUserDidNotPickAPassword() throws Exception {
        writeToField("#textField", path);
        clickOn("#confirmButton");

        verify(serializer, times(0)).deserialize(eq(path), any());
        verify(navigator, times(0)).pop();
    }

    @Test
    public void shouldNotProceedIfPasswordIsNotCorrect() throws Exception {
        when(serializer.deserialize(path, "bobloblaw")).thenThrow(new StreamCorruptedException());

        writeToField("#textField", path);
        writeToField("#passwordField", "bobloblaw");

        clickOn("#confirmButton");

        verify(serializer, times(1)).deserialize(path, "bobloblaw");
        verify(navigator, times(0)).pop();
    }

    @Test
    public void shouldProceedIfEverythingIsFine() throws Exception {
        when(serializer.deserialize(path, "blablabla")).thenReturn(new User("Bob Lob"));

        writeToField("#textField", FileUtils.getDummyFilePath());
        writeToField("#passwordField", "blablabla");

        clickOn("#confirmButton");
        
        verify(serializer, times(1)).deserialize(path, "blablabla");
        verify(navigator, times(1)).pop();
    }

    private void writeToField(String field, String content) {
        clickOn(field);
        write(content);
    }
}