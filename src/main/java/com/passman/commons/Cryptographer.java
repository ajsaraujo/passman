package com.passman.commons;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Base64;

public class Cryptographer {
    private final Charset UTF8 = StandardCharsets.UTF_8;
    private SecretKeySpec keySpec;
    private byte[] key;

    public Cryptographer(String plainTextKey) {
        try {
            MessageDigest sha = MessageDigest.getInstance("SHA-1");

            byte[] bytes = plainTextKey.getBytes(UTF8);
            byte[] digestedBytes = sha.digest(bytes);

            key = Arrays.copyOf(digestedBytes, 16);
            keySpec = new SecretKeySpec(key, "AES");
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public String encrypt(String plainText) {
        try {
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, keySpec);

            byte[] textAsBytes = plainText.getBytes(UTF8);
            byte[] encryptedBytes = cipher.doFinal(textAsBytes);

            Base64.Encoder encoder = Base64.getEncoder();

            return encoder.encodeToString(encryptedBytes);
        } catch (Exception exception) {
            exception.printStackTrace();
            return null;
        }
    }

    public String decrypt(String encryptedText) {
        return encryptedText;
    }
}
