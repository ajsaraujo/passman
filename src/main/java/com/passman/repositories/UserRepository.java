package com.passman.repositories;

import java.sql.SQLException;
import java.sql.Statement;

public class UserRepository extends Repository {
    public void createTable(Statement statement) throws SQLException {
        statement.execute("CREATE TABLE user(" +
                "    id INTEGER PRIMARY KEY NOT NULL,"+
                "    username TEXT NOT NULL,"+
                "    password TEXT NOT NULL," +
                "    salt TEXT NOT NULL," +
                "    key TEXT NOT NULL" +
                ");"
        );
    }
}
