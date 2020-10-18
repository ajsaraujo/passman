package com.passman.ui.components;

import com.passman.UITest;
import com.passman.commons.ValidationResult;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class FormFieldTest extends UITest {
    private FormField usernameField;
    private FormField passwordField;
    private FormField validableField;
    private FormField noActionFormField;
    private FormField actionFormField;

    @Override
    public void start (Stage stage) {
        usernameField = new FormField("Username", false, true, null, null);
        passwordField = new FormField("Password", true, true, null, null);

        validableField = new FormField("Allan's favorite TV Show", false, false, null, null);

        validableField.getTextField().setText("Friends");
        validableField.setValidator(value -> {
            if (value.equals("Friends")) {
                return new ValidationResult( "Allan hates Friends", false);
            }

            return new ValidationResult(true);
        });

        noActionFormField = new FormField("Telephone", false, false, null, null);
        actionFormField = new FormField("Password", true, false, "copy", "Copy to clipboard");

        stage.setScene(new Scene(actionFormField));
        stage.show();
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
        FormField notRequiredField = new FormField("Gender", false, false, null, null);
        notRequiredField.setValidator(value -> new ValidationResult("Invalid field", false));

        assertTrue(notRequiredField.validate());
    }

    @Test
    public void validateReturnsFalseIfFieldIsEmptyAndRequired() {
        FormField requiredField = new FormField("Name", false, true, null, null);
        requiredField.setValidator(value -> null);

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
        FormField field = new FormField("Allan's favorite pizza topping", false, true, null, null);

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

    @Test
    public void showErrorMessageShouldMakeErrorLabelVisible() {
        FormField field = new FormField("Password:", false, false, null, null);

        field.showErrorMessage("Invalid password.");

        Label label = field.getErrorLabel();

        assertEquals(label.getText(), "Invalid password.");
        assertTrue(label.isVisible());
    }

    @Test
    public void hasActionShouldHaveTheCorrectValue() {
        assertTrue(actionFormField.hasAction());
        assertFalse(noActionFormField.hasAction());
    }

    @Test
    public void buttonVisibilityShouldBeCoherentWithFormType() {
        assertTrue(actionFormField.getActionButton().isVisible());
        assertFalse(noActionFormField.getActionButton().isVisible());
    }

    @Test
    public void buttonShouldHaveAnImageView() {
        assertTrue(actionFormField.getActionButton().getChildrenUnmodifiable().get(0) instanceof ImageView);
    }

    @Test
    public void buttonShouldExecuteExpectedBehaviour() {
        EventHandler<ActionEvent> mockHandler = mock(EventHandler.class);
        actionFormField.setOnAction(mockHandler);

        clickOn("#actionButton");

        verify(mockHandler, times(1)).handle(any());
    }
}