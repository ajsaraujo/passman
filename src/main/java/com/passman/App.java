/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.passman;

import com.passman.commons.View;
import com.passman.commons.abstracts.ViewController;
import com.passman.commons.Navigator;
import com.passman.utils.Serializer;
import com.sun.prism.Image;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Stack;

public class App extends Application {
    @Override
    public void start(Stage primaryStage) {
        Navigator navigator = new Navigator(primaryStage, new Stack<>());
        Serializer serializer = new Serializer();

        ViewController.setNavigator(navigator);
        ViewController.setSerializer(serializer);

        navigator.push(new View("login"));
        navigator.render();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
