package com.passman.models;

import org.junit.Test;

import static org.junit.Assert.*;

public class HashTest {
    @Test
    public void shouldConstructAHashCorrectly() {
        byte[] password = new byte[16];
        byte[] salt = new byte[16];

        Hash hash = new Hash(password, salt);

        assertEquals(password, hash.getPassword());
    }

    @Test
    public void ofShouldConstructAValidHash() {
        String password = "flyingbananasonfire";

        Hash hash = Hash.of(password);

        assertNotNull(hash.getPassword());
        assertNotNull(hash.getSalt());
        assertNotEquals(password, hash.getPassword());
    }
    
    @Test
    public void verifyShouldReturnFalseIfPasswordIsNotCorrect() {
        Hash hash = Hash.of("song-for-denise");

        assertFalse(hash.verify("song-for-dennis"));
    }

    @Test
    public void verifyShouldReturnTrueIfPasswordIsCorrect() {
        String password = "dennis-villeneuve";
        Hash hash = Hash.of(password);

        assertTrue(hash.verify(password));
    }
}