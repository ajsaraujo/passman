package com.passman.ui.components;

import com.passman.commons.Component;
import com.passman.commons.Navigator;
import com.passman.commons.View;
import javafx.beans.NamedArg;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.awt.event.ActionEvent;

public class ConfirmOrCancel extends HBox {
    @FXML Button confirmButton;
    private Navigator navigator;
    private String actionName;

    public ConfirmOrCancel(@NamedArg("actionName") String actionName) {
        this.actionName = actionName;

        Component fxmlFile = new Component("confirm-or-cancel");
        fxmlFile.injectController(this);
    }

    @FXML
    public void initialize() {
        confirmButton.setText(actionName);
    }

    @FXML
    public void cancelButtonClicked() {
        navigator.pop();
    };

    public void setOnConfirm(EventHandler onConfirm) {
        confirmButton.setOnAction(onConfirm);
    }

    public void setNavigator(Navigator navigator) {
        this.navigator = navigator;
    }
}
