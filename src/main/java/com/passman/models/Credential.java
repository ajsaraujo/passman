package com.passman.models;

public class Credential {
    private final String service;
    private final String username;
    private final String password;

    public Credential(String service, String username, String password) {
        this.service = service;
        this.username = username;
        this.password = password;
    }

    public String getService() {
        return service;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
