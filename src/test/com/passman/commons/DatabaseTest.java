package com.passman.commons;

import com.passman.repositories.Repository;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import static org.mockito.Mockito.*;

public class DatabaseTest {
    private Connection mockConnection;
    private Statement mockStatement;
    private Repository[] mockRepositories;
    private Database database;

    @Before
    public void setUp() throws SQLException {
        mockConnection = mock(Connection.class);
        mockStatement = mock(Statement.class);
        mockRepositories = new Repository[] {
                mock(Repository.class), mock(MockRepository.class)
        };

        when(mockConnection.createStatement()).thenReturn(mockStatement);

        database = new Database(mockConnection);
    }

    @Test
    public void shouldNotInitDatabaseWhenNotNeeded() throws SQLException {
        database.initialize(mockRepositories);

        for (Repository repository : mockRepositories) {
            verify(repository, times(0)).createTable(mockStatement);
        }
    }

    @Test
    public void shouldInitializeDatabaseIfNeeded() throws SQLException {
        SQLException tableNotFoundException = new SQLException("no such table: user");
        when(mockStatement.execute("SELECT * FROM user;")).thenThrow(tableNotFoundException);

        database.initialize(mockRepositories);

        for (Repository repository : mockRepositories) {
            verify(repository, times(1)).createTable(mockStatement);
        }
    }

    @Test
    public void shouldCorrectlyHandleTheTransaction() throws SQLException {
        SQLException tableNotFoundException = new SQLException("no such table: user");
        when(mockStatement.execute("SELECT * FROM user;")).thenThrow(tableNotFoundException);

        database.initialize(mockRepositories);

        verify(mockConnection, times(1)).setAutoCommit(false);
        verify(mockConnection, times(1)).setAutoCommit(true);
        verify(mockConnection, times(1)).commit();
        verify(mockConnection, times(0)).rollback();
    }

    @Test
    public void shouldRollbackIfSomethingGoesWrong() throws SQLException {
        SQLException tableNotFoundException = new SQLException("no such table: user");
        when(mockStatement.execute("SELECT * FROM user;")).thenThrow(tableNotFoundException);

        SQLException exception = new SQLException("Table already exists");
        doThrow(exception).when(mockRepositories[0]).createTable(mockStatement);

        database.initialize(mockRepositories);

        verify(mockConnection, times(1)).setAutoCommit(false);
        verify(mockConnection, times(1)).setAutoCommit(true);
        verify(mockConnection, times(1)).rollback();
        verify(mockConnection, times(0)).commit();
    }

    private static class MockRepository extends Repository {
        public void createTable(Statement statement) throws SQLException {};
    };
}