package com.passman.models;

import org.junit.Test;

import static org.junit.Assert.*;

public class HashTest {
    @Test
    public void shouldConstructAHashCorrectly() {
        String password = "flyingbananasonfire";
        String salt = "tastypeppersalt";

        Hash hash = new Hash(password, salt);

        assertEquals(password, hash.getPassword());
    }
}