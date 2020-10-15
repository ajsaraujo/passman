package com.passman.utils;

import com.passman.models.User;
import com.passman.utils.FileUtils;
import com.passman.utils.Serializer;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.StreamCorruptedException;

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
}