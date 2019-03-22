package com.efgh.avraelayout.ui.tabs;

import com.efgh.avraelayout.utils.ClipboardHelper;
import com.jfoenix.controls.JFXButton;
import javafx.scene.control.Tab;

public class DiceRoller extends Tab {

    public DiceRoller(){
        setText("Dice Roller");

        JFXButton copyClipboard = new JFXButton();
        copyClipboard.setButtonType(JFXButton.ButtonType.RAISED);
        //copyClipboard.setStyle("-fx-background-color: cyan;");
        copyClipboard.setText("Copy");
        copyClipboard.setOnMouseClicked(me -> ClipboardHelper.copyTextToClipBoard("This could be awesome!"));
        setContent(copyClipboard);
    }
}
