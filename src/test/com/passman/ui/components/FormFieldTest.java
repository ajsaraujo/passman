package com.passman.ui.components;

import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.stage.Stage;
import org.junit.After;
import org.junit.Test;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit.ApplicationTest;

import static org.junit.Assert.*;

public class FormFieldTest extends ApplicationTest {
    private FormField usernameField;
    private FormField passwordField;
    private FormField validableField;

    @Override
    public void start (Stage stage) throws Exception {
        usernameField = new FormField("Username", false, true);
        passwordField = new FormField("Password", true, true);

        validableField = new FormField("Allan's favorite TV Show", false, false);

        validableField.getTextField().setText("Friends");
        validableField.setValidator(value -> {
            if (value.equals("Friends")) {
                return "Allan hates Friends";
            }

            return null;
        });
    }

    @Test
    public void setsLabelAndPromptText() {
        assertEquals(usernameField.getLabel().getText(), "Username");
    }

    @Test
    public void hidesPasswordFieldWhenObscureTextIsFalse() {
        assertFalse(usernameField.getPasswordField().isVisible());
        assertNotSame(usernameField.getTextField(), usernameField.getPasswordField());
    }

    @Test
    public void showsPasswordFieldWhenObscureTextIsTrue() {
        assertTrue(passwordField.getPasswordField().isVisible());
        assertSame(passwordField.getPasswordField(), passwordField.getTextField());
    }

    @Test
    public void validateReturnsTrueIfFieldIsEmptyAndNotRequired() {
        FormField notRequiredField = new FormField("Gender", false, false);
        notRequiredField.setValidator(value -> { return "Invalid field"; });

        assertTrue(notRequiredField.validate());
    }

    @Test
    public void validateReturnsFalseIfFieldIsEmptyAndRequired() {
        FormField requiredField = new FormField("Name", false, true);
        requiredField.setValidator(value -> { return null; });

        assertFalse(requiredField.validate());
    }

    @Test
    public void validateReturnsFalseIfValidationFailed() {
        validableField.getTextField().setText("Friends");

        assertFalse(validableField.validate());
    }

    @Test
    public void validateReturnsTrueIfValidationSucceeded() {
        validableField.getTextField().setText("Fantástico");

        assertTrue(validableField.validate());
    }

    @Test
    public void changesErrorLabelTextAccordingToValidation() {
        FormField field = new FormField("Allan's favorite pizza topping", false, true);

        field.setValidator(value -> value.equals("Banana") ? null : value + " is not Allan's favorite pizza topping.");

        String firstLabelValue = field.getErrorLabel().getText();

        field.getTextField().setText("Chicken");
        field.validate();

        String labelValueAfterFailedValidation = field.getErrorLabel().getText();

        field.getTextField().setText("Banana");
        field.validate();

        String labelValueAfterSuccessfulValidation = field.getErrorLabel().getText();

        assertEquals(firstLabelValue, "");
        assertEquals(labelValueAfterFailedValidation, "Chicken is not Allan's favorite pizza topping.");
        assertNull(labelValueAfterSuccessfulValidation);
    }
}