package com.efgh.avraelayout.utils;

import com.efgh.avraelayout.Rollverlay;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import org.apache.commons.lang3.StringUtils;

public class ClipboardHelper {
    public static void copyTextToClipBoard(String valueToCopy) {
        if (StringUtils.isNotEmpty(valueToCopy)) {
            final Clipboard clipboard = Clipboard.getSystemClipboard();
            final ClipboardContent content = new ClipboardContent();
            content.putString(valueToCopy);
            clipboard.setContent(content);
            Rollverlay.showSnackBar("Roll expression copied to clipboard.", false);
        } else {
            Rollverlay.showSnackBar("No valid expression to copy.", true);
        }
    }
}
