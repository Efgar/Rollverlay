package com.efgh.avraelayout.ui.sections;

import com.efgh.avraelayout.ui.Modifiers;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.scene.Cursor;
import javafx.scene.control.TextFormatter;
import javafx.scene.effect.Glow;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.paint.Color;
import javafx.util.converter.IntegerStringConverter;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class RollingGui extends HBox {

    private List<JFXTextField> manualModifiers;

    public RollingGui() {
        getStyleClass().add("vbox");

        final Pane spacer = new Pane();
        setHgrow(spacer, Priority.ALWAYS);
        spacer.setMinSize(5, 1);

        FontAwesomeIconView advantageIcon = createModifierIcon(FontAwesomeIcon.THUMBS_UP);
        advantageIcon.setOnMouseClicked(e -> activateButton(advantageIcon, Modifiers.switchAdvantage()));

        FontAwesomeIconView disadvantageIcon = createModifierIcon(FontAwesomeIcon.THUMBS_DOWN);
        disadvantageIcon.setOnMouseClicked(e -> activateButton(disadvantageIcon, Modifiers.switchDisadvantage()));

        manualModifiers = new ArrayList<>();
        manualModifiers.add(createManualModifierField("mod"));

        JFXButton resetModsButton = new JFXButton("Reset modifiers");
        resetModsButton.setStyle("-fx-background-color: BLACK; -fx-text-fill:WHITE; -fx-font-weight:bold;");
        resetModsButton.setOnMouseClicked(e -> resetModifiers());

        JFXButton rollButton = new JFXButton("Copy");
        rollButton.setStyle("-fx-background-color: RED; -fx-text-fill:WHITE; -fx-font-weight:bold;");
        rollButton.setOnMouseClicked(e -> System.out.println("Rolling"));

        getChildren().addAll(advantageIcon, disadvantageIcon);
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
        manualModifiers.forEach(e-> e.setText(null));

    }

    private FontAwesomeIconView createModifierIcon(FontAwesomeIcon icon) {
        FontAwesomeIconView createdIconButton = new FontAwesomeIconView(icon);
        createdIconButton.setCursor(Cursor.HAND);
        return createdIconButton;
    }

    private JFXTextField createManualModifierField(String modPlaceHolder){
        Pattern validInteger = Pattern.compile("-?(\\d{0,2})");
        TextFormatter<Integer> integerFormatter = new TextFormatter<>(new IntegerStringConverter(), null,
                change -> {
                    String newText = change.getControlNewText();
                    if (validInteger.matcher(newText).matches()) {
                        return change;
                    }
                    return null;
                });

        JFXTextField manualModifierField = new JFXTextField();
        manualModifierField.setPromptText(modPlaceHolder);
        manualModifierField.setTextFormatter(integerFormatter);

        return manualModifierField;
    }
}
