package com.passman.utils;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class RandomizerTest {
    @Test
    public void shouldGenerateARandomString() {
        String random1 = Randomizer.generateRandomString();
        String random2 = Randomizer.generateRandomString();

        assertNotNull(random1);
        assertNotNull(random2);

        assertNotEquals(random1, random2);
    }

    @Test
    public void shouldGenerateARandomPasswordWithRequiredCharacters() {
        String randomPassword = new Randomizer().generatePassword();

        assertNotNull(randomPassword);
        assertFalse(randomPassword.isEmpty() || randomPassword.isBlank());

        boolean containsLowerCase = false;
        boolean containsUpperCase = false;
        boolean containsNumber = false;
        boolean containsSymbol = false;

        for (char i = 'a'; i <= 'z' && !(containsUpperCase && containsLowerCase); i++) {
            String lowerCaseChar = String.valueOf(i);
            String upperCaseChar = String.valueOf(i).toUpperCase();

            if (randomPassword.contains(lowerCaseChar)) {
                containsLowerCase = true;
            }

            if (randomPassword.contains(upperCaseChar)) {
                containsUpperCase = true;
            }
        }

        for (int i = 0; i <= 9; i++) {
            if (randomPassword.contains(String.valueOf(i))) {
                containsNumber = true;
                break;
            }
        }

        List<String> symbols = Arrays.asList("!", "@", "#", "$", "%", "&", "*", "(", ")", "_", "-", "+", "-", ".", ",");
        for (String symbol : symbols) {
            if (randomPassword.contains(symbol)) {
                containsSymbol = true;
                break;
            }
        }

        assertTrue(containsLowerCase && containsUpperCase && containsNumber && containsSymbol);
    }
}