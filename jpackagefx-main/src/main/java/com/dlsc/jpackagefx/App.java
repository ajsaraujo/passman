/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dlsc.jpackagefx;

import java.util.Locale;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import jfxtras.styles.jmetro.JMetro;
import jfxtras.styles.jmetro.JMetroStyleClass;
import jfxtras.styles.jmetro.Style;

public class App extends Application {
    
    private final static boolean USE_JMETRO = true;

    @Override
    public void start(Stage primaryStage) {
        Label defaultLocaleLabel = new Label(Locale.getDefault().getDisplayLanguage());
        Label label1 = new Label("Hello");
        Label label2 = new Label("World");
        VBox box = new VBox(20, defaultLocaleLabel, label1, label2);
        box.setAlignment(Pos.CENTER);
        box.setPadding(new Insets(20));
        Scene scene = new Scene(box);
        
        if (USE_JMETRO) {
            // This is just to show how dependencies to external libraries
            // (in this case JMetro) are handled.
            
            JMetro jMetro = new JMetro(Style.DARK); // Or Style.LIGHT
            jMetro.setScene(scene);
            
            box.getStyleClass().add(JMetroStyleClass.BACKGROUND);
        }
        
        primaryStage.setScene(scene);
        primaryStage.setWidth(300);
        primaryStage.setHeight(300);
        primaryStage.centerOnScreen();
        primaryStage.setTitle("JPackageFXScript");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
