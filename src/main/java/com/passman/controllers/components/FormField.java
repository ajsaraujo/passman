package com.passman.controllers.components;

import com.passman.enums.Component;
import com.passman.utils.FileUtils;
import javafx.scene.layout.AnchorPane;

public class FormField extends AnchorPane {
    public FormField() {
        FileUtils.injectComponentController(Component.FORM_FIELD, this);
    }
}
