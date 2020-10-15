package com.passman.utils;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class FileUtils {
    public static ImageView loadImageView(String imageName) {
        String path = "assets/" + imageName + ".png";

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

    public static boolean isValid(File file) {
        try {
            file.getCanonicalPath();
            return true;
        } catch (IOException exception) {
            return false;
        }
    }

    public static String getAppFolder() {
        try {
            return new File(".").getCanonicalPath();
        } catch (IOException exception) {
            return null;
        }
    }

    public static String getDummyFilePath() {
        return getAppFolder() + File.separatorChar + "dummy.pman";
    }
}
