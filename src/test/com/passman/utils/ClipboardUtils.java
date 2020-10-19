package com.passman.utils;

import javafx.scene.input.Clipboard;

public class ClipboardUtils {
    public static String getClipboardContent() {
        Clipboard clipboard = Clipboard.getSystemClipboard();
        return clipboard.getString();
    }
}
