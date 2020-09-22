package com.passman.enums;

import java.net.URL;

public enum View {
    LOGIN("login"),
    SIGN_UP("sign-up");

    private final String name;

    View(String name) {
        this.name = name;
    }

    public URL getUrl() {
        return View.class.getResource("/views/" + name + ".fxml");
    }
}
