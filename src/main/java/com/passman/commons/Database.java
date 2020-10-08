package com.passman.commons;

import com.passman.repositories.Repository;

import java.sql.*;

public class Database {
    private Connection connection;

    public Database(Connection connection) {
        this.connection = connection;
    }

    public final void initialize(Repository ... repositories) {
        try {
            if (databaseIsNotInitialized()) {
                initializeDatabase(repositories);
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    private boolean databaseIsNotInitialized() throws SQLException {
        try {
            Statement statement = connection.createStatement();
            statement.execute("SELECT * FROM user;");

            return false;
        } catch (SQLException exception) {
            String errorMessage = exception.getMessage();

            if (errorMessage.contains("no such table: user")) {
                return true;
            }

            throw exception;
        }
    }

    private void initializeDatabase(Repository ... repositories) {
        try {
            connection.setAutoCommit(false);

            Statement statement = connection.createStatement();

            for (Repository repository : repositories) {
                repository.createTable(statement);
            }

            connection.commit();
        } catch (SQLException exception) {
            rollback();
        }

        try {
            connection.setAutoCommit(true);
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    private void rollback() {
        if (connection == null) {
            throw new RuntimeException("Could not rollback: connection to the database is null");
        }

        try {
            connection.rollback();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }
}
