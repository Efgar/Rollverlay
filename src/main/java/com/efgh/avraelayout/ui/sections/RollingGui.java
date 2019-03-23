package com.efgh.avraelayout.ui.sections;

import com.efgh.avraelayout.Modifiers;
import com.efgh.avraelayout.ui.components.ManualModifierTextField;
import com.efgh.avraelayout.ui.components.ManualModifierToogleButton;
import com.jfoenix.controls.JFXButton;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.scene.Cursor;
import javafx.scene.effect.Glow;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;

public class RollingGui extends HBox {

    private List<ManualModifierTextField> manualModifiers = new ArrayList<>();
    private List<ManualModifierToogleButton> toggleModifiers = new ArrayList<>();

    public RollingGui() {
        getStyleClass().add("rolling-bar");

        final Pane spacer = new Pane();
        setHgrow(spacer, Priority.ALWAYS);
        spacer.setMinSize(5, 1);

        FontAwesomeIconView advantageIcon = createModifierIcon(FontAwesomeIcon.THUMBS_UP);
        advantageIcon.setOnMouseClicked(e -> activateButton(advantageIcon, Modifiers.switchAdvantage()));

        FontAwesomeIconView disadvantageIcon = createModifierIcon(FontAwesomeIcon.THUMBS_DOWN);
        disadvantageIcon.setOnMouseClicked(e -> activateButton(disadvantageIcon, Modifiers.switchDisadvantage()));

        toggleModifiers.add(new ManualModifierToogleButton("Disadvantage", FontAwesomeIcon.THUMBS_UP));
        toggleModifiers.add(new ManualModifierToogleButton("Disadvantage", FontAwesomeIcon.THUMBS_DOWN));
        manualModifiers.add(new ManualModifierTextField("Manual modifier", "mod"));

        JFXButton resetModsButton = new JFXButton("Reset modifiers");
        resetModsButton.getStyleClass().add("secondary-action");
        resetModsButton.setOnMouseClicked(e -> resetModifiers());

        JFXButton rollButton = new JFXButton("Copy");
        rollButton.getStyleClass().add("main-action");
        rollButton.setOnMouseClicked(e -> System.out.println("Rolling"));

        getChildren().addAll(toggleModifiers);
        getChildren().addAll(manualModifiers);
        getChildren().addAll(spacer, resetModsButton, rollButton);
    }

    private boolean activateButton(FontAwesomeIconView button, Boolean variable) {
        if (variable) {
            button.setFill(Color.WHITE);
            button.setEffect(new Glow(2.0));
        } else {
            button.setFill(Color.BLACK);
            button.setEffect(null);
        }
        return variable;
    }

    private void resetModifiers(){
        manualModifiers.forEach(ManualModifierTextField::reset);
        toggleModifiers.forEach(ManualModifierToogleButton::reset);
    }

    private FontAwesomeIconView createModifierIcon(FontAwesomeIcon icon) {
        FontAwesomeIconView createdIconButton = new FontAwesomeIconView(icon);
        createdIconButton.setCursor(Cursor.HAND);
        return createdIconButton;
    }
}
