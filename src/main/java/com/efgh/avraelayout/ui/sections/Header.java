package com.efgh.avraelayout.ui.sections;

import com.jfoenix.controls.JFXButton;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.geometry.Insets;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;

public class Header extends HBox {
    public Header(){
        setHeight(5);
        setPadding(new Insets(5, 12, 5, 12));
        setStyle("-fx-background-color: DARKRED");

        final Pane spacer = new Pane();
        setHgrow(spacer, Priority.ALWAYS);
        spacer.setMinSize(5, 1);

        final JFXButton right = new JFXButton("Right");

        FontAwesomeIconView thumbsDownIcon = new FontAwesomeIconView();
        thumbsDownIcon.setStyleClass("thumbs-down-icon");
        thumbsDownIcon.setOnMouseClicked(e -> System.out.println("close"));
        getChildren().addAll(spacer, thumbsDownIcon);

        /*`
        Button buttonCurrent = new Button("Current");
        buttonCurrent.setPrefSize(100, 20);

        Button buttonProjected = new Button("Projected");
        buttonProjected.setPrefSize(100, 20);
        getChildren().addAll(buttonCurrent, buttonProjected);
        */
    }
}
