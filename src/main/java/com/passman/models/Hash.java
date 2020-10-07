package com.passman.models;

import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.lang.reflect.Array;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.security.spec.KeySpec;
import java.util.Arrays;

public class Hash {
    private final byte[] password;
    private final byte[] salt;

    public Hash(byte[] hashedPassword, byte[] salt) {
        this.password = hashedPassword;
        this.salt = salt;
    }

    public static Hash of(String plainTextPassword) {
        SecureRandom random = new SecureRandom();

        byte[] salt = new byte[16];

        random.nextBytes(salt);

        byte[] hash = hash(plainTextPassword, salt);
        assert hash != null: "Hash method failed.";

        return new Hash(hash, salt);
    }

    private static byte[] hash(String plainTextPassword, byte[] salt) {
        final int HASH_STRENGTH = 65536;
        final int KEY_LENGTH = 128;

        try {
            KeySpec spec = new PBEKeySpec(plainTextPassword.toCharArray(), salt, HASH_STRENGTH, KEY_LENGTH);
            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");

            return factory.generateSecret(spec).getEncoded();
        } catch (Exception exception) {
            exception.printStackTrace();
            return null;
        }
    }

    public boolean verify(String plainTextPassword) {
        byte[] obtainedHash = hash(plainTextPassword, salt);
        return Arrays.equals(password, obtainedHash);
    }

    public byte[] getPassword() {
        return password;
    }

    public byte[] getSalt() {
        return salt;
    }
}
