package com.passman.commons;

import com.passman.commons.interfaces.ValidableField;
import com.passman.ui.components.FormField;

public class Form {
    ValidableField[] fields;

    public Form(ValidableField...fields) {
        this.fields = fields;
    }

    public boolean validate() {
        boolean isValid = true;

        // The validation shouldn't stop if a invalid field is found,
        // because all fields must be validated so the error messages can be shown.
        for (ValidableField field : fields) {
            isValid = field.validate() && isValid;
        }

        return isValid;
    }
}
