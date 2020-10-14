package com.passman.utils;

import com.passman.models.User;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.nio.charset.StandardCharsets;

public class SerializingUtils {
    public static void serialize(User user, String password, String filePath) {
        SecretKey key = makeKey(password);

        try {
            Cipher cipher = Cipher.getInstance("Blowfish");
            cipher.init(Cipher.ENCRYPT_MODE, key);

            SealedObject sealedObject = new SealedObject(user, cipher);

            ObjectOutputStream stream = createOutputStream(filePath, cipher);

            stream.writeObject(sealedObject);
            stream.close();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public static User deserialize(String filePath, String password) throws StreamCorruptedException, Exception {
        SecretKey key = makeKey(password);

        Cipher cipher = Cipher.getInstance("Blowfish");
        cipher.init(Cipher.DECRYPT_MODE, key);

        ObjectInputStream stream = createInputStream(filePath, cipher);

        SealedObject sealedObject = (SealedObject) stream.readObject();

        return (User) sealedObject.getObject(cipher);
    }

    private static SecretKey makeKey(String password) {
        return new SecretKeySpec(password.getBytes(StandardCharsets.UTF_8), "Blowfish");
    }

    private static ObjectOutputStream createOutputStream(String filePath, Cipher cipher) throws IOException {
        FileOutputStream fileStream = new FileOutputStream(filePath);
        BufferedOutputStream bufferedStream = new BufferedOutputStream(fileStream);
        CipherOutputStream cipherStream = new CipherOutputStream(bufferedStream, cipher);
        return new ObjectOutputStream(cipherStream);
    }

    private static ObjectInputStream createInputStream(String filePath, Cipher cipher) throws Exception {
        FileInputStream fileStream = new FileInputStream(filePath);
        BufferedInputStream bufferedStream = new BufferedInputStream(fileStream);
        CipherInputStream cipherStream = new CipherInputStream(bufferedStream, cipher);
        return new ObjectInputStream(cipherStream);
    }
}
