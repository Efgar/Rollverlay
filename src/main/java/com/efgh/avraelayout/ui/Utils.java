package com.efgh.avraelayout.ui;

import javafx.scene.control.ScrollPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class Utils {

    public static ScrollPane buildBaseScrollablePane(FlowPane containedPanel) {
        containedPanel.getStyleClass().add("flowPane");
        final ScrollPane scrollPane = new ScrollPane();
        scrollPane.getStyleClass().add("hbox");
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        scrollPane.setContent(containedPanel);
        VBox.setVgrow(scrollPane, Priority.ALWAYS);
        return scrollPane;
    }
}
