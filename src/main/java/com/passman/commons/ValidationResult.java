package com.passman.commons;

public class ValidationResult {
    private String message;
    private boolean isValid;

    public ValidationResult() {};

    public ValidationResult(boolean isValid) {
        this.isValid = isValid;
    }

    public ValidationResult(String message, boolean isValid) {
        this.message = message;
        this.isValid = isValid;
    }

    public String getMessage() {
        return message;
    }

    public boolean isValid() {
        return isValid;
    }
}
