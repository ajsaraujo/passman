package com.passman.utils;

import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.Random;

public class RandomUtils {
    public static String generateRandomString() {
        byte[] bytes = new byte[8];

        Random randomizer = new SecureRandom();
        randomizer.nextBytes(bytes);

        return new String(bytes, StandardCharsets.UTF_8);
    }
}
