package com.passman.utils;

import com.passman.models.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.io.StreamCorruptedException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class SerializerTest {
    private String path;
    private User user;
    private String password;

    @Before
    public void setUp() {
        path = FileUtils.getAppFolder() + File.separator + "test_file.pman";
        user = new User("Josh");
        password = "drAkE";

        new Serializer().serialize(user, password, path);
    }

    @Test
    public void shouldThrowExceptionIfPasswordIsWrong() {
        assertThrows(StreamCorruptedException.class, () -> {
            new Serializer().deserialize(path, "drake");
        });
    }

    @Test
    public void shouldReturnAUserWithCorrectName() throws Exception {
        User obtainedUser = new Serializer().deserialize(path, password);

        assertEquals(user.getName(), obtainedUser.getName());
    }

    @After
    @Ignore
    // This is throwing an exception, it can't delete the file because
    // it's being used by another process. Maybe the stream wasn't closed?
    // Related thread: https://stackoverflow.com/questions/64370560/cant-delete-file-because-its-used-by-another-process-even-though-stream-was-c
    public void tearDown() {
        Path filePath = Paths.get(path);

        try {
            Files.delete(filePath);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }
}