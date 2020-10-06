/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.passman;

import com.passman.commons.abstracts.ViewController;
import com.passman.commons.Navigator;
import javafx.application.Application;
import javafx.stage.Stage;

public class App extends Application {
    @Override
    public void start(Stage primaryStage) {
        Navigator navigator = new Navigator(primaryStage, "login");
        navigator.renderView();

        ViewController.setNavigator(navigator);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
