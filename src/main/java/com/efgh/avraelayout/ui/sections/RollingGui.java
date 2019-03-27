package com.efgh.avraelayout.ui.sections;

import com.efgh.avraelayout.ui.components.ManualModifierTextField;
import com.efgh.avraelayout.ui.components.ManualModifierToogleButton;
import com.efgh.avraelayout.utils.ClipboardHelper;
import com.jfoenix.controls.JFXButton;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;

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

        toggleModifiers.add(new ManualModifierToogleButton("Disadvantage", FontAwesomeIcon.THUMBS_UP));
        toggleModifiers.add(new ManualModifierToogleButton("Disadvantage", FontAwesomeIcon.THUMBS_DOWN));
        manualModifiers.add(new ManualModifierTextField("Manual modifier", "mod"));

        JFXButton resetModsButton = new JFXButton("Reset modifiers");
        resetModsButton.getStyleClass().add("secondary-action");
        resetModsButton.setOnMouseClicked(e -> resetModifiers());

        JFXButton rollButton = new JFXButton("Copy");
        rollButton.getStyleClass().add("main-action");
        rollButton.setOnMouseClicked(e -> ClipboardHelper.copyTextToClipBoard(""));

        getChildren().addAll(toggleModifiers);
        getChildren().addAll(manualModifiers);
        getChildren().addAll(spacer, resetModsButton, rollButton);
    }

    private void resetModifiers(){
        manualModifiers.forEach(ManualModifierTextField::reset);
        toggleModifiers.forEach(ManualModifierToogleButton::reset);
    }
}
