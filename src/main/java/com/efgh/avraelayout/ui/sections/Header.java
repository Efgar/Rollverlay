package com.efgh.avraelayout.ui.sections;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.scene.Cursor;
import javafx.scene.control.Label;
import javafx.scene.effect.Glow;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;

public class Header extends HBox {
    public Header(Stage primaryStage) {
        getStyleClass().add("hbox");

        final Pane spacer = new Pane();
        setHgrow(spacer, Priority.ALWAYS);
        spacer.setMinSize(5, 1);

        FontAwesomeIconView configIcon = createBarIconButton(FontAwesomeIcon.COG);
        configIcon.getStyleClass().add("config-icon");
        configIcon.setOnMouseClicked(e -> System.out.println("configure stuff"));

        Label title = new Label("Rollverlay");
        title.getStyleClass().add("title-bar");
        title.setEffect(new Glow(10));

        FontAwesomeIconView closeIcon = createBarIconButton(FontAwesomeIcon.TIMES_CIRCLE);
        closeIcon.setOnMouseClicked(e -> System.exit(0));

        FontAwesomeIconView minimizeIcon = createBarIconButton(FontAwesomeIcon.MINUS_CIRCLE);
        minimizeIcon.setOnMouseClicked(e -> primaryStage.setIconified(true));

        getChildren().addAll(configIcon, title, spacer, minimizeIcon, closeIcon);

        setCursor(Cursor.MOVE);
    }

    private FontAwesomeIconView createBarIconButton(FontAwesomeIcon icon) {
        FontAwesomeIconView createdIconButton = new FontAwesomeIconView(icon);
        createdIconButton.setCursor(Cursor.HAND);
        return createdIconButton;
    }
}
