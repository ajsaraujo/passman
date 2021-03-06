package com.passman.commons;

import com.passman.ui.components.FormField;
import javafx.stage.Stage;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;

import static org.junit.Assert.*;

public class FormTest extends ApplicationTest {
    private FormField validField1;
    private FormField validField2;
    private FormField validField3;
    private FormField invalidField;

    @Override
    public void start(Stage stage) throws Exception {
        validField1 = new FormField("First name", false, false, null, null);
        validField2 = new FormField("Last name", false, false, null, null);
        validField3 = new FormField("Password", true, true, null, null);

        validField3.setValidator(value -> new ValidationResult(true));
        validField3.getTextField().setText("123456abcde");

        invalidField = new FormField("Confirm Password", true, true, null, null);
    }

    @Test
    public void validateShouldReturnTrueIfAllFieldsAreValid() {
        Form form = new Form(validField1, validField2, validField3);

        assertTrue(form.validate());
    }

    @Test
    public void validateShouldReturnFalseIfAnyFieldIsInvalid() {
        Form form = new Form(validField1, validField2, validField3, invalidField);

        assertFalse(form.validate());
    }
}