package com.passman.utils;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.io.InputStream;

public class FileUtils {
    public static ImageView loadImageView(String imageName) {
        String path = "assets/" + imageName + ".png";
        System.out.println("Trying to get the imagei in " + path + "...");

        try (InputStream inputStream = FileUtils.class.getClassLoader().getResourceAsStream(path)) {
            if (inputStream == null) {
                throw new RuntimeException("Could not load image, input stream is null.");
            }

            Image image = new Image(inputStream);
            return new ImageView(image);
        } catch (Exception exception) {
            exception.printStackTrace();
            return null;
        }
    }
}
