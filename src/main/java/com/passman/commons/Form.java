package com.passman.commons;

import com.passman.ui.components.FormField;

public class Form {
    FormField[] fields;

    public Form(FormField ...fields) {
        this.fields = fields;
    }

    public boolean validate() {
        boolean isValid = true;

        // The validation shouldn't stop if a invalid field is found,
        // because all fields must be validated so the error messages can be shown.
        for (FormField field : fields) {
            isValid = field.validate() && isValid;
        }

        return isValid;
    }
}
