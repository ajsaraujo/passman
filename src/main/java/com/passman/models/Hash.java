package com.passman.models;

public class Hash {
    private final String password;
    private final String salt;

    /**
     *
     * @param hashedPassword a password hashed by a hashing algorithm.
     * @param salt the salt used to hash the password.
     */
    public Hash(String hashedPassword, String salt) {
        this.password = hashedPassword;
        this.salt = salt;
    }

    public String getPassword() {
        return password;
    }

    public String getSalt() {
        return salt;
    }
}
