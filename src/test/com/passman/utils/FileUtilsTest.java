package com.passman.utils;

import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class FileUtilsTest {
    private File file;

    @Before
    public void setUp() {
        file = mock(File.class);
    }

    @Test
    public void isValidShouldReturnTrueIfPathIsValid() {
        assertTrue(FileUtils.isValid(file));
    }

    @Test
    public void isValidShouldReturnFalseIfPathIsNotValid() throws IOException {
        when(file.getCanonicalPath()).thenThrow(new IOException());

        assertFalse(FileUtils.isValid(file));
    }
}