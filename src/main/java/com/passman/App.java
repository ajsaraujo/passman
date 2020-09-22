/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.passman;

import com.passman.enums.View;
import com.passman.utils.FileUtils;
import com.passman.utils.NavigationUtils;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {
    @Override
    public void start(Stage primaryStage) {
        NavigationUtils.init(primaryStage, View.LOGIN);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
