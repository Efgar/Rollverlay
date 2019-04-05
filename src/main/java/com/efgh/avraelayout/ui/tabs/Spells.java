package com.efgh.avraelayout.ui.tabs;

import javafx.scene.control.Tab;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

public class Spells extends Tab {

    public Spells() {
        setText("Spells");

        HBox expressionButtons = new HBox();
        expressionButtons.getStyleClass().add("hbox");
        expressionButtons.setPrefHeight(Double.MAX_VALUE);

        expressionButtons.getChildren().add(new Text("Coming soon..."));
        setContent(expressionButtons);
    }
}
