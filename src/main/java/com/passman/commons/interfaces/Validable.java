package com.passman.commons.interfaces;

public interface Validable {
    /**
     * @param value The value to be validated.
     * @return Null if value is valid or an error message describing the error if it isn't.
     */
    public String validate(String value);
}

