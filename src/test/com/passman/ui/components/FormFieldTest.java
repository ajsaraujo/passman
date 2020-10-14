package com.passman.ui.components;

import com.passman.UITest;
import com.passman.commons.ValidationResult;
import javafx.stage.Stage;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;

import static org.junit.Assert.*;

public class FormFieldTest extends UITest {
    private FormField usernameField;
    private FormField passwordField;
    private FormField validableField;

    @Override
    public void start (Stage stage) {
        usernameField = new FormField("Username", false, true);
        passwordField = new FormField("Password", true, true);

        validableField = new FormField("Allan's favorite TV Show", false, false);

        validableField.getTextField().setText("Friends");
        validableField.setValidator(value -> {
            if (value.equals("Friends")) {
                return new ValidationResult( "Allan hates Friends", false);
            }

            return new ValidationResult(true);
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
        notRequiredField.setValidator(value -> new ValidationResult("Invalid field", false));

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

        field.setValidator(value -> value.equals("Banana")
            ? new ValidationResult(true)
            : new ValidationResult(value + " is not Allan's favorite pizza topping.", false)
        );

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