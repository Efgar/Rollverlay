package com.efgh.avraelayout.ui.sections.footer;

import com.efgh.avraelayout.Rollverlay;
import com.efgh.avraelayout.utils.ClipboardHelper;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXSnackbar;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.text.Text;
import javafx.util.Duration;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class RollingGui extends HBox {

    private List<ManualModifierTextField> manualModifiers = new ArrayList<>();
    private List<ManualModifierToggleButton> toggleModifiers = new ArrayList<>();

    public RollingGui() {
        getStyleClass().add("rolling-bar");

        final Pane spacer = new Pane();
        setHgrow(spacer, Priority.ALWAYS);
        spacer.setMinSize(5, 1);

        toggleModifiers.add(new ManualModifierToggleButton("Disadvantage", "adv", FontAwesomeIcon.THUMBS_UP));
        toggleModifiers.add(new ManualModifierToggleButton("Disadvantage", "dis", FontAwesomeIcon.THUMBS_DOWN));
        manualModifiers.add(new ManualModifierTextField("Manual modifier", "mod"));

        JFXButton resetModsButton = new JFXButton("Reset modifiers");
        resetModsButton.getStyleClass().add("secondary-action");
        resetModsButton.setOnMouseClicked(e -> resetModifiers());

        JFXButton rollButton = new JFXButton("Copy");
        rollButton.getStyleClass().add("main-action");
        rollButton.setOnMouseClicked(e -> copyRoll());

        getChildren().addAll(toggleModifiers);
        getChildren().addAll(manualModifiers);
        getChildren().addAll(spacer, resetModsButton, rollButton);
    }

    private void copyRoll() {
        JFXSnackbar snackbar = new JFXSnackbar(this);
        String rollExpression = Rollverlay.getRollExpresion();
        if (StringUtils.isNotEmpty(rollExpression)) {
            rollExpression = String.join(" ", rollExpression, getManualModifiers(), getToggleModifiers()).trim();
            System.out.println(rollExpression);
            ClipboardHelper.copyTextToClipBoard(rollExpression);
            snackbar.enqueue(new JFXSnackbar.SnackbarEvent(new Text(rollExpression + " copied to clipboard."), Duration.seconds(1), null));
        }
        snackbar.enqueue(new JFXSnackbar.SnackbarEvent(new Text("No valid expression to copy."), Duration.seconds(1), null));
    }

    private String getManualModifiers() {
        return manualModifiers.stream().map(ManualModifierTextField::getText).filter(StringUtils::isNotEmpty).collect(Collectors.joining(" "));
    }

    private String getToggleModifiers() {
        return toggleModifiers.stream().map(ManualModifierToggleButton::getModifierExpression).filter(StringUtils::isNotEmpty).collect(Collectors.joining(" "));
    }

    private void resetModifiers(){
        manualModifiers.forEach(ManualModifierTextField::reset);
        toggleModifiers.forEach(ManualModifierToggleButton::reset);
    }
}