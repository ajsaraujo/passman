package com.passman.commons;

import org.junit.Test;

import static org.junit.Assert.*;

public class CryptographerTest {
    @Test
    public void encryptShouldEncryptThePassword() {
        Cryptographer cryptographer = new Cryptographer("dirtylittlesecret");
        assertNotEquals("banana", cryptographer.encrypt("banana"));
    }

    @Test
    public void decryptShouldRecoverThePassword() {
        Cryptographer cryptographer = new Cryptographer("oursecret");
        String encryptedPassword = cryptographer.encrypt("applesandoranges");
        String decryptedPassword = cryptographer.decrypt(encryptedPassword);

        assertEquals("applesandoranges", decryptedPassword);
    }
}