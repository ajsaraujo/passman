package com.passman.repositories;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public abstract class Repository {
    protected static Connection connection;

    public static void setConnection(Connection connection) {
        Repository.connection = connection;
    }

    public abstract void createTable(Statement statement) throws SQLException;
}
