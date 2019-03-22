package com.efgh.avraelayout.ui.tabs;

import com.jfoenix.controls.JFXButton;
import javafx.scene.control.Tab;

public class Attributes extends Tab {
    public Attributes(){
        setText("Attributes");
        JFXButton btn = new JFXButton();
        btn.setButtonType(JFXButton.ButtonType.RAISED);
        //btn.setStyle("-fx-background-color: red;");
        btn.setText("Say 'Hello World'");
        setContent(btn);
    }
}
