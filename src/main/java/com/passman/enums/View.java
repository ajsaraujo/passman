package com.passman.enums;

import java.net.URL;

public enum View {
    LOGIN("login");

    private String name;

    View(String name) {
        this.name = name;
    }

    public URL getUrl() {
        return View.class.getResource("/views/" + name + ".fxml");
    }
}
