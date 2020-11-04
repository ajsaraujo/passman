package com.passman.utils;

import javafx.scene.input.Clipboard;
import javafx.scene.input.DataFormat;

import java.util.Map;

public class ClipboardManager {
    private static final Clipboard clipboard = javafx.scene.input.Clipboard.getSystemClipboard();

    public String getContent() {
        return clipboard.getString();
    }

    public void copy(String content) {
        clipboard.setContent(Map.of(DataFormat.PLAIN_TEXT, content));
    }
}
