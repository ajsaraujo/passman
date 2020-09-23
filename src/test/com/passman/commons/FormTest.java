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
        validField1 = new FormField("First name", false, false);
        validField2 = new FormField("Last name", false, false);
        validField3 = new FormField("Password", true, true);

        validField3.setValidator(value -> { return null; });
        validField3.getTextField().setText("123456abcde");

        invalidField = new FormField("Confirm Password", true, true);
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