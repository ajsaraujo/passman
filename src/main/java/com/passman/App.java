/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.passman;

import com.passman.commons.Database;
import com.passman.commons.View;
import com.passman.commons.abstracts.ViewController;
import com.passman.commons.Navigator;
import com.passman.repositories.AccountRepository;
import com.passman.repositories.Repository;
import com.passman.repositories.UserRepository;
import javafx.application.Application;
import javafx.stage.Stage;

import javax.xml.crypto.Data;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Stack;

public class App extends Application {
    @Override
    public void start(Stage primaryStage) {
        initializeDatabase();
        initializeView(primaryStage);
    }

    private void initializeDatabase() {
        try {
            Connection connection = DriverManager.getConnection("jdbc:sqlite:.db.sqlite");

            Repository.setConnection(connection);

            Database database = new Database(connection);
            database.initialize(
                    new UserRepository(),
                    new AccountRepository()
            );

        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }
    private void initializeView(Stage primaryStage) {
        Navigator navigator = new Navigator(primaryStage, new Stack<>());

        ViewController.setNavigator(navigator);

        navigator.push(new View("login"));
        navigator.render();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
