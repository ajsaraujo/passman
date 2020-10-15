package com.passman.utils;

import com.passman.models.User;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.nio.charset.StandardCharsets;

public class Serializer {
    public void serialize(User user, String password, String filePath) {
        SecretKey key = makeKey(password);

        try {
            Cipher cipher = Cipher.getInstance("Blowfish");
            cipher.init(Cipher.ENCRYPT_MODE, key);

            SealedObject sealedObject = new SealedObject(user, cipher);

            writeObject(filePath, cipher, sealedObject);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    private void writeObject(String filePath, Cipher cipher, SealedObject sealedObject) {
        try (ObjectOutputStream stream = createOutputStream(filePath, cipher)) {
            stream.writeObject(sealedObject);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    private ObjectOutputStream createOutputStream(String filePath, Cipher cipher) throws Exception {
        FileOutputStream fileStream = new FileOutputStream(filePath);
        BufferedOutputStream bufferedStream = new BufferedOutputStream(fileStream);
        CipherOutputStream cipherStream = new CipherOutputStream(bufferedStream, cipher);
        return new ObjectOutputStream(cipherStream);
    }

    public User deserialize(String filePath, String password) throws StreamCorruptedException {
        SecretKey key = makeKey(password);

        Cipher cipher = createDecryptCypher(key);

        try (ObjectInputStream stream = createInputStream(filePath, cipher)) {
            SealedObject sealedObject = (SealedObject) stream.readObject();
            return (User) sealedObject.getObject(cipher);
        } catch (StreamCorruptedException exception) {
            throw exception;
        } catch (Exception exception) {
            exception.printStackTrace();
            return null;
        }
    }

    private ObjectInputStream createInputStream(String filePath, Cipher cipher) throws Exception {
        FileInputStream fileStream = new FileInputStream(filePath);
        BufferedInputStream bufferedStream = new BufferedInputStream(fileStream);
        CipherInputStream cipherStream = new CipherInputStream(bufferedStream, cipher);
        return new ObjectInputStream(cipherStream);
    }

    private SecretKey makeKey(String password) {
        return new SecretKeySpec(password.getBytes(StandardCharsets.UTF_8), "Blowfish");
    }

    private Cipher createDecryptCypher(SecretKey key) {
        try {
            Cipher cipher = Cipher.getInstance("Blowfish");
            cipher.init(Cipher.DECRYPT_MODE, key);

            return cipher;
        } catch (Exception exception) {
            exception.printStackTrace();
            return null;
        }
    }
}