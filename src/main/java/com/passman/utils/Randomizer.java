package com.passman.utils;

import java.security.SecureRandom;

public class Randomizer {
    public static String generateRandomString() {
        byte[] bytes = new byte[16];

        SecureRandom random = new SecureRandom();
        random.nextBytes(bytes);

        return new String(bytes, Constants.getUTF8Charset());
    }
}
