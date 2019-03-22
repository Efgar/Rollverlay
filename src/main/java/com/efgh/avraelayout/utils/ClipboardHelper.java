package com.efgh.avraelayout.utils;

import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;

public class ClipboardHelper {
    public static void copyTextToClipBoard(String valueToCopy) {
        final Clipboard clipboard = Clipboard.getSystemClipboard();
        final ClipboardContent content = new ClipboardContent();
        content.putString(valueToCopy);
        clipboard.setContent(content);
    }
}
