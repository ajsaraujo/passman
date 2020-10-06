package com.passman.commons;

import com.passman.commons.abstracts.FXMLFile;

public class View extends FXMLFile {
    public View(String name) {
        this.path = View.class.getResource("/views/" + name + ".fxml");
    }
}
