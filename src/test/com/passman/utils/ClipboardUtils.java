package com.passman.utils;

import javafx.scene.input.Clipboard;
import javafx.scene.input.DataFormat;

import java.util.Map;

public class ClipboardUtils {
    private static final Clipboard clipboard = Clipboard.getSystemClipboard();

    public static String getClipboardContent() {
        return clipboard.getString();
    }

    public static void copyToClipboard(String content) {
        clipboard.setContent(Map.of(DataFormat.PLAIN_TEXT, content));
    }
}
