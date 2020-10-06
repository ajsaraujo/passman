package com.passman.ui.views;

import com.passman.commons.View;
import javafx.stage.Stage;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import java.util.Map;

import static org.junit.Assert.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class SignUpTest extends ControllerTest {
    @Override
    public void start(Stage primaryStage) {
        super.start(primaryStage);
        render(new View("sign-up"));
    }

    @BeforeEach
    public void clearForm() {
        View view = new View("sign-up");
        primaryStage.setScene(view.loadScene());
    }

    @Test
    public void shouldPopValidatorWhenCancelIsClicked() {
        clickOn("#cancelButton");
        verify(navigator, times(1)).pop();
    }

    @Test
    public void shouldNotPopIfFormIsEmpty() {
        clickOn("#confirmButton");
        verify(navigator, times(0)).pop();
    }

    @Test
    public void shouldNotPopIfPasswordsDontMatch() {
        fillForm("mrbanana", "bananaismypassword", "bananaisnotmypassword");
        verify(navigator, times(0)).pop();
    }

    @Test
    public void shouldNotPopIfPasswordsDontMatchMinimalLength() {
        fillForm("banana", "baa", "ban");
        verify(navigator, times(0)).pop();
    }

    @Test
    public void shouldPopIfEverythingIsAlright() {
        fillForm("banana", "banana", "banana");
        verify(navigator, times(1)).pop();
    }

    private void fillForm(String usernameValue, String passwordValue, String confirmPasswordValue) {
        Map<String, String> fields = Map.of(
                "#usernameField", usernameValue,
                "#passwordField", passwordValue,
                "#confirmPasswordField", confirmPasswordValue
        );

        for (Map.Entry<String, String> entry : fields.entrySet()) {
            clickOn(entry.getKey());
            write(entry.getValue());
        }

        clickOn("#confirmButton");
    }
}