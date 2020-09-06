package com.passman.enums;

public enum View {
    LOGIN("login");

    private String name;

    View(String name) {
        this.name = name;
    }

    public String getFileName() {
        return "/views/fxml/" + name + ".fxml";
    }
}
