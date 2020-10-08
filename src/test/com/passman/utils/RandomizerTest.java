package com.passman.utils;

import org.junit.Test;

import static org.junit.Assert.*;

public class RandomizerTest {
    @Test
    public void shouldGenerateARandomString() {
        String random1 = Randomizer.generateRandomString();
        String random2 = Randomizer.generateRandomString();

        assertNotEquals(random1, random2);
        assertNotNull(random1);
        assertNotNull(random2);
    }
}