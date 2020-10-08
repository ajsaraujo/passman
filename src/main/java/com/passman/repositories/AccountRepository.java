package com.passman.repositories;

import java.sql.SQLException;
import java.sql.Statement;

public class AccountRepository extends Repository {
    public void createTable(Statement statement) throws SQLException {
        statement.execute("CREATE TABLE account(" +
                "    id INTEGER PRIMARY KEY NOT NULL," +
                "    user_id INTEGER NOT NULL," +
                "    username TEXT NOT NULL," +
                "    password TEXT NOT NULL," +
                "    additional_info TEXT," +
                "    FOREIGN KEY(user_id) REFERENCES user(id)" +
                ");"
        );
    }
}
