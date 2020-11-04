package com.passman.utils;

import java.lang.reflect.Array;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Randomizer {
    public String generatePassword() {
        byte[] bytes = new byte[16];

        Random randomizer = new SecureRandom();
        randomizer.nextBytes(bytes);

        String password = new String(bytes, StandardCharsets.UTF_8);

        return password + requiredCharacters();
    }

    // Returns a String with all type of characters that services require you to have in your password.
    private String requiredCharacters() {
        List<String> lowerCase = new ArrayList<>();
        List<String> upperCase = new ArrayList<>();
        List<String> numbers = new ArrayList<>();
        List<String> symbols = Arrays.asList("!", "@", "#", "$", "%", "&", "*", "(", ")", "_", "-", "+", "-", ".", ",");

        for (char i = 'a'; i <= 'z'; i++) {
            lowerCase.add(String.valueOf(i));
            upperCase.add(String.valueOf(i).toUpperCase());
        }

        for (int i = 0; i <= 9; i++) {
            numbers.add(String.valueOf(i));
        }

        return getRandomFrom(lowerCase) + getRandomFrom(numbers) + getRandomFrom(symbols);
    }

    private String getRandomFrom(List<String> list) {
        int index = new SecureRandom().nextInt(list.size());
        return list.get(index);
    }

    public static String generateRandomString() {
        byte[] bytes = new byte[8];

        Random randomizer = new SecureRandom();
        randomizer.nextBytes(bytes);

        return new String(bytes, StandardCharsets.UTF_8);
    }
}
