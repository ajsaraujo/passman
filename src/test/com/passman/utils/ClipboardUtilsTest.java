package com.passman.utils;

import com.passman.UITest;
import javafx.application.Platform;
import org.junit.Test;

import static org.junit.Assert.*;

public class ClipboardUtilsTest extends UITest {
    @Test
    public void shouldCopyAndGetContentCorrectly() {
        // We need to run later since the Clipboard API runs on the FX Thread.
        Platform.runLater(() -> {
            String content = "Bananas on Pajamas";

            ClipboardUtils.copyToClipboard(content);

            assertEquals(content, ClipboardUtils.getClipboardContent());
        });
    }
}