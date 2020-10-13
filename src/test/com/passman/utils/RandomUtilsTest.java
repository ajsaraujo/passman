package com.passman.utils;

import org.junit.Test;

import static org.junit.Assert.*;

public class RandomUtilsTest {
    @Test
    public void shouldGenerateARandomString() {
        String random1 = RandomUtils.generateRandomString();
        String random2 = RandomUtils.generateRandomString();

        assertNotNull(random1);
        assertNotNull(random2);

        assertNotEquals(random1, random2);
    }
}