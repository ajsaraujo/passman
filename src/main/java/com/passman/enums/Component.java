package com.passman.enums;

import java.net.URL;

public enum Component {
    FORM_FIELD("form-field");

    private String name;

    Component(String name) {
        this.name = name;
    }

    public URL getUrl () {
        return Component.class.getResource("/components/" + name + ".fxml");
    }
}
