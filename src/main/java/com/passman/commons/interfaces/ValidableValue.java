package com.passman.commons.interfaces;

import com.passman.commons.ValidationResult;

public interface ValidableValue {
    /**
     * @param value The value to be validated.
     * @return Null if value is valid or an error message describing the error if it isn't.
     */
    public ValidationResult validate(String value);
}

